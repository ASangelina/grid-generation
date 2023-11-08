package br.udesc.services;

import br.udesc.entities.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TimeService {

    List<Professor> profs;
    List<Discipline> disciplines;
    String[][] scheduleMatrix;//matriz para solucao

    public TimeService(List<Professor> profs, List<Discipline> disciplines) {
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
        List<Discipline> disciplines = professor.getDisciplines();
        if (disciplines != null) {
            return disciplines.size();
        }
        return 0;
    }

    public Optional<Discipline> disciplineMaxTogether(String codeProfessor) {
        List<Discipline> disciplinesProfessor = disciplines.stream()
                .filter(discipline -> discipline.getProfessorCode().equals(codeProfessor))
                .toList();

        if (disciplinesProfessor.isEmpty()) {
            return Optional.empty();
        }

        Optional<Discipline> disciplineMostWorkHours = disciplinesProfessor.stream()
                .max(Comparator.comparingInt(Discipline::getWorkHours));

        return disciplineMostWorkHours.filter(discipline -> discipline.getClassRestriction() == ClassRestriction.TOGETHER);
    }

    //retorna a lista dos professores restritos
    public List<Professor> findProfessorsRestriction() {
        return profs.stream()
                .filter(professor -> professor.getMandatoryDays().size() > 0)
                .collect(Collectors.toList());
    }

    // metodo para calcular se existe disponibilidade de sobrar ou o professor não possui disponibilidade de sobrar de horário.
    public int countCreditsAvailability(Professor professor) {
        int daysWork = 6 - professor.getMandatoryDays().size();
        int maxCreditsDay = 4;

        List<Discipline> disciplinesProfessor = professor.getDisciplines();

        Map<String, Integer> mapDisciplines = disciplines.stream()
                .collect(Collectors.toMap(Discipline::getDisciplineCode, Discipline::getCredits));

        int creditsTotal = disciplinesProfessor.stream()
                .mapToInt(mapDisciplines::get)
                .sum();

        return daysWork * maxCreditsDay - creditsTotal;
    }

    public Professor professorMinAvailability(List<Professor> professoress) {

        Map<Professor, Integer> resultados = professoress.stream()
                .collect(Collectors.toMap(
                        professor -> professor,
                        this::countCreditsAvailability
                ));

        Map.Entry<Professor, Integer> professorMinAvailability = resultados.entrySet().stream()
                .min(Comparator.comparing(Map.Entry::getValue))
                .orElse(null);

        if (professorMinAvailability != null) {
            Professor professorResult = professorMinAvailability.getKey();
            return professorResult;

        }
        return null;
    }

    private boolean checkMaxDailyClass(Discipline discipline) {
        int max = 4;
        if(discipline.getCredits() == max){
            return true;
        }else{
            return false;
        }
    }

    private boolean checkMaxDailyClassTogether(Discipline discipline) {
        int max = 4;
        if(discipline.getCredits() == max && discipline.getClassRestriction() == ClassRestriction.TOGETHER){
            return true;
        }else{
            return false;
        }

    }

    private boolean checkMinDailyClass(Discipline discipline) {
        int min = 2;
        if(discipline.getCredits() == min && discipline.getClassRestriction() == ClassRestriction.NONE && discipline.getTimeRestriction() != TimeRestriction.NONE){
            return true;
        }else{
            return false;
        }

    }

    private boolean checkMinDailyClassNotRestrict(Discipline discipline) {
        int min = 2;
        if(discipline.getCredits() == min && discipline.getClassRestriction() == ClassRestriction.NONE && discipline.getTimeRestriction() == TimeRestriction.NONE){
            return true;
        }else{
            return false;
        }

    }

    public int extractFase(String input) {
        char firstDigit = input.replaceAll("\\D", "").charAt(0);
        return Character.getNumericValue(firstDigit);
    }

    public Discipline findDiscipline() {
        List<Professor> professors = findProfessorsRestriction();
        Professor professor = professorMinAvailability(professors);
        List<Discipline> disciplinasDoProfessor = disciplines.stream()
                .filter(disciplina -> disciplina.getProfessorCode().equals(professor.getCode()))
                .toList();

        Optional<Discipline> result = disciplinasDoProfessor.stream()
                .filter(this::checkMaxDailyClassTogether)
                .findFirst();

        if (result.isEmpty()) {
            result = disciplinasDoProfessor.stream()
                    .filter(this::checkMinDailyClass)
                    .findFirst();
        }

        if (result.isEmpty()) {
            result = disciplinasDoProfessor.stream()
                    .filter(this::checkMinDailyClassNotRestrict)
                    .findFirst();
        }

        if (result.isEmpty()) {
            return null;
        }

        return result.get();
    }
}
