package br.udesc.services;

import br.udesc.entities.Discipline;
import br.udesc.entities.Professor;
import br.udesc.entities.Transaction;
import br.udesc.result.*;

import java.util.*;

public class GridGeneratorService {
    private String lessonToTime(int lesson) {
        switch (lesson) {
            case 0:
                return "Primeiro horário";
            case 1:
                return "Segundo horário";
            default:
                return "Horário inválido";
        }
    }
    private String dayToDayOfWeek(int day) {
        switch (day) {
            case 0:
                return "SEGUNDA";
            case 1:
                return "TERÇA";
            case 2:
                return "QUARTA";
            case 3:
                return "QUINTA";
            case 4:
                return "SEXTA";
            case 5:
                return "SÁBADO";
            default:
                return "Dia inválido";
        }
    }

    public Professor selectProfessor(List<Professor> professorList) {
        int maxCredits = 0;
        Professor professorWithMaxCredits = null;

        for (Professor professor : professorList) {
            int credits = professor.getDisciplines().stream().mapToInt(Discipline::getCredits).sum();

            if (credits > maxCredits) {
                maxCredits = credits;
                professorWithMaxCredits = professor;
            }
        }
        return professorWithMaxCredits;

    }

    public int extractFase(String input) {
        char firstDigit = input.replaceAll("\\D", "").charAt(0);
        return Character.getNumericValue(firstDigit);
    }

    private Integer getMostCompletePhase(Map<Integer, Discipline[][]> grades) {
        Integer mostCompletePhase = null;
        int maxCredits = 0;

        for (Map.Entry<Integer, Discipline[][]> entry : grades.entrySet()) {
            int totalCreditsInPhase = 0;
            for (Discipline[] daySchedule : entry.getValue()) {
                for (Discipline d : daySchedule) {
                    if (d != null) {
                        totalCreditsInPhase += d.getCredits();
                    }
                }
            }
            if (totalCreditsInPhase > maxCredits) {
                mostCompletePhase = entry.getKey();
                maxCredits = totalCreditsInPhase;
            }
        }

        return mostCompletePhase;
    }



    private void allocateDisciplines(Professor professor, Map<Integer, Discipline[][]> grades) {

        Integer mostCompletePhase = getMostCompletePhase(grades);

        List<Discipline> mostCompletePhaseDisciplines = new ArrayList<>();
        List<Discipline> otherDisciplines = new ArrayList<>();

        for (Discipline discipline : professor.getDisciplines()) {
            int fase = extractFase(discipline.getDisciplineCode());
            if (fase == mostCompletePhase) {
                mostCompletePhaseDisciplines.add(discipline);
            } else {
                otherDisciplines.add(discipline);
            }
        }

        // Allocate disciplines in the most complete phase first
        for (Discipline discipline : mostCompletePhaseDisciplines) {
            allocateDiscipline(discipline, grades);
        }

        // Then allocate the remaining disciplines
        for (Discipline discipline : otherDisciplines) {
            allocateDiscipline(discipline, grades);
        }
    }

    private void allocateDiscipline(Discipline discipline, Map<Integer, Discipline[][]> grades) {
        int fase = extractFase(discipline.getDisciplineCode());
        if (!grades.containsKey(fase)) {
            grades.put(fase, new Discipline[2][6]);
        }
        Discipline[][] phaseSchedule = grades.get(fase);



        boolean allocated = false;
        int remainingCredits = discipline.getCredits();
        for (int day = 0; day < 6; day++) {
            for (int lesson = 0; lesson < 2; lesson++) {
                if (phaseSchedule[lesson][day] == null) {
                    boolean professorAvailable = isProfessorAvailable(discipline, grades, fase, lesson, day);
                    if (professorAvailable) {
                        int availablePositions = day == 5 ? 1 : 2; // Se for sábado, aloca apenas na primeira posição
                        for (int i = 0; i < availablePositions; i++) {
                            if (lesson + i < 2) {
                                phaseSchedule[lesson + i][day] = discipline;
                                remainingCredits -= 2;
                                if (remainingCredits == 0) {
                                    allocated = true;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (allocated) {
                    break;
                }
            }
            if (allocated) {
                break;
            }
        }
    }

    private boolean isProfessorAvailable(Discipline discipline, Map<Integer, Discipline[][]> grades, int fase, int lesson, int day) {
        for (int otherFase : grades.keySet()) {
            if (otherFase != fase) {
                Discipline[][] otherPhaseSchedule = grades.get(otherFase);
                Discipline otherDiscipline = otherPhaseSchedule[lesson][day];
                if (otherDiscipline != null && otherDiscipline.getProfessorCode().equals(discipline.getProfessorCode())) {
                    return false;
                }
            }
        }
        return true;
    }

    public ResultGrid buildSchedule(Transaction transaction) {
        Map<Integer, Discipline[][]> grades = new HashMap<>();

        List<Professor> professorList = transaction.getProfessorList();

        Professor professorInicial = selectProfessor(professorList);
        List<Discipline> disciplineList = professorInicial.getDisciplines().stream()
                .sorted(Comparator.comparingInt(Discipline::getCredits).reversed()).toList();
        for (Discipline discipline : disciplineList) {
            allocateDiscipline(discipline, grades);

        }

        for (Professor professor : professorList) {
            if (professor != professorInicial) {
                allocateDisciplines(professor, grades);
            }
        }


        ArrayList<ProfessorSchedule> professorSchedules = new ArrayList<>();

        for (Map.Entry<Integer, Discipline[][]> entry : grades.entrySet()) {
            Discipline[][] phaseSchedule = entry.getValue();
            for (Professor professor : professorList) {
                Map<String, Day> professorMap = new HashMap<>();
                for(Discipline disciplineProfessor:professor.getDisciplines()){
                    for (int lesson = 0; lesson < 2; lesson++) {
                        for (int day = 0; day < 6; day++) {
                            Discipline discipline = phaseSchedule[lesson][day];
                            if (discipline != null && discipline.getProfessorCode().equals(disciplineProfessor.getProfessorCode())) {
                                Lesson lessonObj = new Lesson();
                                lessonObj.setTime(lessonToTime(lesson));
                                lessonObj.setDiscipline(discipline.getDisciplineCode());

                                Day dayObj = new Day();
                                dayObj.setDayOfWeek(dayToDayOfWeek(day));
                                dayObj.getLessons().put(lessonObj.getTime(), lessonObj);

                                professorMap.put(dayObj.getDayOfWeek(), dayObj);
                            }
                        }

                    }
                }
                ProfessorSchedule professorSchedule = new ProfessorSchedule();
                professorSchedule.setProfessorName(professor.getName());
                professorSchedule.setSchedule(professorMap);
                professorSchedules.add(professorSchedule);
            }
        }

        List<DisciplineSchedule> disciplineSchedules = new ArrayList<>();

        for (Map.Entry<Integer, Discipline[][]> entry : grades.entrySet()) {
            String phase = entry.getKey().toString();
            Discipline[][] phaseSchedule = entry.getValue();
            Map<String, Map<String, List<Map<String, String>>>> disciplineMap = new HashMap<>();

            for (int lesson = 0; lesson < 2; lesson++) {
                for (int day = 0; day < 6; day++) {
                    Discipline disciplineInSchedule = phaseSchedule[lesson][day];
                    if (disciplineInSchedule != null) {
                        String dayOfWeek = dayToDayOfWeek(day);
                        String disciplineCode = disciplineInSchedule.getDisciplineCode();
                        String time = lessonToTime(lesson);

                        disciplineMap.computeIfAbsent(dayOfWeek, k -> new HashMap<>())
                                .computeIfAbsent(disciplineCode, k -> new ArrayList<>())
                                .add(Map.of("time", time));
                    }
                }
            }

            DisciplineSchedule disciplineSchedule = new DisciplineSchedule();
            disciplineSchedule.setFase(phase);
            disciplineSchedule.setSchedule(disciplineMap);
            disciplineSchedules.add(disciplineSchedule);
        }
        ResultGrid resultGrid = new ResultGrid();
        resultGrid.setDisciplineSchedules(disciplineSchedules);
        resultGrid.setProfessorSchedules(professorSchedules);

        return resultGrid;

    }

}

