package br.udesc.services;

import br.udesc.entities.Discipline;
import br.udesc.entities.Professor;
import br.udesc.entities.Weekdays;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class TimeService {


    List<Professor> profs;
    List<Discipline> disciplines;
    String[][] scheduleMatrix;//matriz para solucao

    public TimeService(List<Professor> profs,List<Discipline> disciplines) {
        this.profs = profs;
        this.disciplines = disciplines;
        this.scheduleMatrix = new String[6][24];
    }


    public Professor mostRestrictedProfessor() {
        Optional<Professor> professorRestrictionMost = profs.stream()
                .max(Comparator.comparingInt(this::countMandatoryRestrictionsForProfessor)
                        .thenComparingInt(this::countDisciplineForProfessor));

        return professorRestrictionMost.orElse(null);
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
    public void buildSchedule() {
        //construcao da grade.
    }
    }


