package br.udesc.services;

import br.udesc.entities.Discipline;
import br.udesc.entities.Professor;
import br.udesc.entities.Transaction;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GridGeneratorSimple {
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



    private void allocateDisciplines(Professor professor, Map<Integer, Discipline[][]> grades) {
        List<Discipline> disciplineList = professor.getDisciplines().stream()
                .sorted(Comparator.comparingInt(Discipline::getCredits).reversed())
                .collect(Collectors.toList());

        for (Discipline discipline : disciplineList) {
            int fase = extractFase(discipline.getDisciplineCode());
            if (!grades.containsKey(fase)) {
                grades.put(fase, new Discipline[2][6]);
            }
            Discipline[][] phaseSchedule = grades.get(fase);

            int totalCreditsInPhase = getTotalCreditsInPhase(phaseSchedule);

            boolean allocated = false;
            int remainingCredits = discipline.getCredits();

            for (int day = 0; day < 6; day++) {
                for (int lesson = 0; lesson < 2; lesson++) {
                    if (phaseSchedule[lesson][day] == null) {
                        boolean professorAvailable = isProfessorAvailable(discipline, grades, fase, lesson, day);
                        if (professorAvailable) {
                            int availablePositions = Math.min(remainingCredits / 2, 2);
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
    }

    private int getTotalCreditsInPhase(Discipline[][] phaseSchedule) {
        int totalCreditsInPhase = 0;
        for (Discipline[] daySchedule : phaseSchedule) {
            for (Discipline d : daySchedule) {
                if (d != null) {
                    totalCreditsInPhase += d.getCredits();
                }
            }
        }
        return totalCreditsInPhase;
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




    public void buildSchedule(Transaction transaction) {
        Map<Integer, Discipline[][]> grades = new HashMap<>();
        Map<Integer, Integer> somaTotalCreditos = new HashMap<>();
        List<Professor> professorList = transaction.getProfessorList();

        Professor professorInicial = selectProfessor(professorList);
        allocateDisciplines(professorInicial, grades);

        for (Professor professor : professorList) {
            if (professor != professorInicial) {
                allocateDisciplines(professor, grades);
            }
        }


        for (Map.Entry<Integer, Discipline[][]> entry : grades.entrySet()) {
            int fase = entry.getKey();
            Discipline[][] phaseSchedule = entry.getValue();
            System.out.println("Fase " + fase + ":");
            for (int lesson = 0; lesson < 2; lesson++) {
                for (int day = 0; day < 6; day++) {
                    Discipline discipline = phaseSchedule[lesson][day];
                    if (discipline != null) {
                        System.out.println("Dia " + (day + 1) + ", Aula " + (lesson + 1) + ": " + discipline.getDisciplineCode() + ", Professor " + ": " + discipline.getProfessorCode() + ", Créditos " + ": " + discipline.getCredits());
                    }
                }
            }
        }
    }
}
