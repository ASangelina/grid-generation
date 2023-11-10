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

    public Map<Integer, Discipline[][]> buildSchedule(Transaction transaction) {
        Map<Integer, Discipline[][]> grades = new HashMap<>();

        //Map para ter a soma total de creditos;
        Map<Integer,Integer> somaTotalCreditos = new HashMap<>();

        List<Professor> professorList = transaction.getProfessorList();
        for (Professor professor : professorList){
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

            int totalCreditsInPhase = 0;
            for (Discipline[] daySchedule : phaseSchedule) {
                for (Discipline d : daySchedule) {
                    if (d != null) {
                        totalCreditsInPhase += d.getCredits();
                    }
                }
            }

            if (totalCreditsInPhase == somaTotalCreditos.get(fase)) {
                // A fase está completa, pule para a próxima disciplina
                continue;
            }

            //TODO: ajustar para alocar até a quantidade de creditos dividido/2, se for 2:1 uma posicao, 4:2 duas posicoes. e assim por diante, até a disciplina seja alocada.
            boolean allocated = false;
            for (int day = 0; day < 6; day++) {
                for (int lesson = 0; lesson < 2; lesson++) {
                    if (phaseSchedule[lesson][day] == null) {
                        boolean canAllocate = true;
                        for (int otherFase : grades.keySet()) {
                            if (otherFase != fase && phaseSchedule[lesson][day] != null) {
                                canAllocate = false;
                                break;
                            }
                        }
                        if (canAllocate) {
                            phaseSchedule[lesson][day] = discipline;
                            allocated = true;
                            break;
                        }
                    }
                }
                if (allocated) {
                    break;
                }
            }



            }



        return grades;
    }
}

