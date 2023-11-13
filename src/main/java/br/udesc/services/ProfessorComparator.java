package br.udesc.services;

import br.udesc.entities.Discipline;
import br.udesc.entities.Professor;

import java.util.Comparator;

public class ProfessorComparator implements Comparator<Professor> {
    @Override
    public int compare(Professor professor1, Professor professor2) {
        // Comparar a quantidade de dias que podem trabalhar em relação à quantidade de créditos
        int creditsComparison = Integer.compare(getTotalCredits(professor1), getTotalCredits(professor2));
        if (creditsComparison != 0) {
            return creditsComparison;
        }

        // Comparar as restrições de classe
        int classRestrictionComparison = Integer.compare(getMaxClassRestriction(professor1), getMaxClassRestriction(professor2));
        if (classRestrictionComparison != 0) {
            return classRestrictionComparison;
        }

        // Comparar as restrições de tempo
        int timeRestrictionComparison = Integer.compare(getMaxTimeRestriction(professor1), getMaxTimeRestriction(professor2));
        if (timeRestrictionComparison != 0) {
            return timeRestrictionComparison;
        }

        // Se todas as comparações forem iguais, manter a ordem original
        return 0;
    }

    private int getTotalCredits(Professor professor) {
        int totalCredits = 0;
        for (Discipline disciplina : professor.getDisciplines()) {
            totalCredits += disciplina.getCredits();
        }
        return totalCredits;
    }

    private int getMaxClassRestriction(Professor professor) {
        int maxClassRestriction = 0;
        for (Discipline disciplina : professor.getDisciplines()) {
            maxClassRestriction = Math.max(maxClassRestriction, disciplina.getClassRestriction().ordinal());
        }
        return maxClassRestriction;
    }

    private int getMaxTimeRestriction(Professor professor) {
        int maxTimeRestriction = 0;
        for (Discipline disciplina : professor.getDisciplines()) {
            maxTimeRestriction = Math.max(maxTimeRestriction, disciplina.getTimeRestriction().ordinal());
        }
        return maxTimeRestriction;
    }
}
