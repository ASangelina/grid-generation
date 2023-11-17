package br.udesc.result;

import java.util.List;

public class ResultGrid {
    private List<DisciplineSchedule> disciplineSchedules;
    private List<ProfessorSchedule> professorSchedules;


    public List<DisciplineSchedule> getDisciplineSchedules() {
        return disciplineSchedules;
    }

    public void setDisciplineSchedules(List<DisciplineSchedule> disciplineSchedules) {
        this.disciplineSchedules = disciplineSchedules;
    }

    public List<ProfessorSchedule> getProfessorSchedules() {
        return professorSchedules;
    }

    public void setProfessorSchedules(List<ProfessorSchedule> professorSchedules) {
        this.professorSchedules = professorSchedules;
    }


}
