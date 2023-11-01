package br.udesc.services;

import br.udesc.entities.Professor;
import br.udesc.entities.Weekdays;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class TimeService {


    List<Professor> profs;

    public TimeService(List<Professor> profs) {
        this.profs = profs;
    }


    public Professor mostRestrictedProfessor() {
        Optional<Professor> professorRestrictionMost = profs.stream()
                .max(Comparator.comparingInt(this::countMandatoryRestrictionsForProfessor));

        return professorRestrictionMost.orElse(null);
    }

    private int countMandatoryRestrictionsForProfessor(Professor professor) {
        List<Weekdays> mandatoryDays = professor.getMandatoryDays();
        if (mandatoryDays != null) {
            return mandatoryDays.size();
        }
        return 0;
    }
    }


