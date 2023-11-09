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
    // precisa fazer a solucaoInicial com o professor escolhido.

    // precisa de uma função para verificar qual o melhor horário.

    //TODO: verificar se a soma total de créditos está acabada para terminar concluir a fase.

    public Map<Integer, Discipline[][]> buildSchedule(Transaction transaction) {
        Map<Integer, Discipline[][]> grades = new HashMap<>();

        // com base na lista de professores vindo da transaction, seleciona o professor inicial.
        List<Professor> professorList = transaction.getProfessorList();
        Professor professorInicial = selectProfessor(professorList);
        //ordenar a lista de disciplinas do professor
        List<Discipline> disciplineList = professorInicial.getDisciplines().stream()
                .sorted(Comparator.comparingInt(Discipline::getCredits).reversed())
                .collect(Collectors.toList());

        //selecao do dia
        for (Discipline discipline : disciplineList) {
            int fase = extractFase(discipline.getDisciplineCode());

            }



        return grades;
    }
}

