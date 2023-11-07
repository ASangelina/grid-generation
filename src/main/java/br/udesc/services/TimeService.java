package br.udesc.services;

import br.udesc.entities.Professor;
import br.udesc.entities.Transaction;
import br.udesc.entities.Weekdays;

import java.util.List;

public class TimeService {

    String[][] scheduleMatrix;//matriz para solucao

    public Professor mostRestrictedProfessor() {
//        Optional<Professor> professorRestrictionMost = profs.stream()
//                .max(Comparator.comparingInt(this::countMandatoryRestrictionsForProfessor)
//                .thenComparingInt(this::countDisciplineForProfessor));
//
//        return professorRestrictionMost.orElse(null);
        return null;
    }

    private int countMandatoryRestrictionsForProfessor(Professor professor) {
        List<Weekdays> mandatoryDays = professor.getMandatoryDays();
        if (mandatoryDays != null) {
            return mandatoryDays.size();
        }
        return 0;
    }

    private int countDisciplineForProfessor(Professor professor) {
        List<String> disciplines = professor.getDisciplines();
        if (disciplines != null) {
            return disciplines.size();
        }
        return 0;
    }

    public void buildSchedule(Transaction transaction) {
        //construcao da grade.

        //professor com mais quantidade de disciplinas

        //disciplina que tem mais carga horária
    }
}
