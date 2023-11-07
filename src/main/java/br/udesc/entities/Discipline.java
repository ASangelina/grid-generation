package br.udesc.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Discipline {

    @JsonProperty(value = "discipline_code")
    private String disciplineCode;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "professor_code")
    private String professorCode;

    @JsonProperty(value = "work_hours")
    private int workHours;

    @JsonProperty(value = "credits")
    private int credits;

    @JsonProperty(value = "class_restriction")
    private ClassRestriction classRestriction;

    @JsonProperty(value = "time_restriction")
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
