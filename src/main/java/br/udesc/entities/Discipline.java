package br.udesc.entities;

public class Discipline {

    private String disciplineCode;
    private String description;
    private String professorCode;
    private int workHours;
    private int credits;
    private String classRestriction;
    private String timeRestriction;

    public Discipline(String disciplineCode, String description, String professorCode, int workHours, int credits,
                      String classRestriction, String timeRestriction) {
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

    public String getClassRestriction() {
        return classRestriction;
    }

    public void setClassRestriction(String classRestriction) {
        this.classRestriction = classRestriction;
    }

    public String getTimeRestriction() {
        return timeRestriction;
    }

    public void setTimeRestriction(String timeRestriction) {
        this.timeRestriction = timeRestriction;
    }
}
