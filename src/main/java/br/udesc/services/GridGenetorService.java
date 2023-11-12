package br.udesc.services;

import br.udesc.entities.Discipline;
import br.udesc.entities.Professor;
import br.udesc.entities.Transaction;

import java.util.*;
import java.util.stream.Collectors;


public class GridGenetorService {

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

    //TODO: proximo passo preencher a phaseSchedule mais completa.
    public void buildSchedule(Transaction transaction) {
        //public Map<Integer, Discipline[][]> buildSchedule(Transaction transaction) {
        Map<Integer, Discipline[][]> grades = new HashMap<>();

        //Map para ter a soma total de creditos;
        Map<Integer, Integer> somaTotalCreditos = new HashMap<>();

        List<Professor> professorList = transaction.getProfessorList();
        for (Professor professor : professorList) {
            for (Discipline discipline : professor.getDisciplines()) {
                int fase = extractFase(discipline.getDisciplineCode());
                if (!somaTotalCreditos.containsKey(fase)) {
                    somaTotalCreditos.put(fase, discipline.getCredits());
                } else {
                    int totalCredits = somaTotalCreditos.get(fase) + discipline.getCredits();
                    somaTotalCreditos.put(fase, totalCredits);
                }
            }


        }

        // com base na lista de professores vindo da transaction, seleciona o professor inicial, isso só para o inicial.
        Professor professorInicial = selectProfessor(professorList);
        //ordenar a lista de disciplinas do professor
        List<Discipline> disciplineList = professorInicial.getDisciplines().stream()
                .sorted(Comparator.comparingInt(Discipline::getCredits).reversed())
                .collect(Collectors.toList());


        for (Discipline discipline : disciplineList) {
            int fase = extractFase(discipline.getDisciplineCode());
            if (!grades.containsKey(fase)) {
                grades.put(fase, new Discipline[2][6]);
            }
            Discipline[][] phaseSchedule = grades.get(fase);

            //usar para acabar o algoritmo, tem necessidade?
            int totalCreditsInPhase = 0;
            for (Discipline[] daySchedule : phaseSchedule) {
                for (Discipline d : daySchedule) {
                    if (d != null) {
                        totalCreditsInPhase += d.getCredits();
                    }
                }
            }


            boolean allocated = false;

            int remainingCredits = discipline.getCredits();

            for (int day = 0; day < 6; day++) {
                for (int lesson = 0; lesson < 2; lesson++) {
                    if (phaseSchedule[lesson][day] == null) {
                        boolean professorAvailable = true;
                        for (int otherFase : grades.keySet()) {
                            if (otherFase != fase) {
                                Discipline[][] otherPhaseSchedule = grades.get(otherFase);
                                Discipline otherDiscipline = otherPhaseSchedule[lesson][day];
                                if (otherDiscipline != null && otherDiscipline.getProfessorCode().equals(discipline.getProfessorCode())) {
                                    professorAvailable = false;
                                    break;
                                }
                            }
                        }
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

            for (int faseteste : grades.keySet()) {
                System.out.println("Fase " + faseteste + ":");
                Discipline[][] phaseScheduleteste = grades.get(faseteste);
                for (int dayteste = 0; dayteste < 6; dayteste++) {
                    for (int lessonteste = 0; lessonteste < 2; lessonteste++) {
                        Discipline disciplineteste = phaseScheduleteste[lessonteste][dayteste];
                        if (disciplineteste != null) {
                            System.out.println("Dia " + dayteste + ", Horário " + lessonteste + ": " + disciplineteste.getDisciplineCode());
                        }
                    }
                }
                System.out.println();
            }



            //return grades;
        }
    }

}

