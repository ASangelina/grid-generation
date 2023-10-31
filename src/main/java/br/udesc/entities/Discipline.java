package br.udesc.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Discipline {

    @JsonProperty(value = "codigo_disciplina")
    private String disciplineCode;

    @JsonProperty(value = "descricao")
    private String description;

    @JsonProperty(value = "codigo_professor")
    private String professorCode;

    @JsonProperty(value = "carga_horaria")
    private int workHours;

    @JsonProperty(value = "credito")
    private int credits;

    @JsonProperty(value = "restricao_aula")
    private ClassRestriction classRestriction;

    @JsonProperty(value = "restricao_tempo")
    private TimeRestriction timeRestriction;

    public Discipline(String disciplineCode, String description, String professorCode, int workHours, int credits,
                      ClassRestriction classRestriction, TimeRestriction timeRestriction) {
        this.disciplineCode = disciplineCode;
        this.description = description;
        this.professorCode = professorCode;
        this.workHours = workHours;
        this.credits = credits;
        this.classRestriction = classRestriction;
        this.timeRestriction = timeRestriction;
    }

    public String getDisciplineCode() {
        return disciplineCode;
    }

    public void setDisciplineCode(String disciplineCode) {
        this.disciplineCode = disciplineCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfessorCode() {
        return professorCode;
    }

    public void setProfessorCode(String professorCode) {
        this.professorCode = professorCode;
    }

    public int getWorkHours() {
        return workHours;
    }

    public void setWorkHours(int workHours) {
        this.workHours = workHours;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public ClassRestriction getClassRestriction() {
        return classRestriction;
    }

    public void setClassRestriction(ClassRestriction classRestriction) {
        this.classRestriction = classRestriction;
    }

    public TimeRestriction getTimeRestriction() {
        return timeRestriction;
    }

    public void setTimeRestriction(TimeRestriction timeRestriction) {
        this.timeRestriction = timeRestriction;
    }
}
