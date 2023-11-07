package br.udesc.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Transaction {

    @JsonProperty(value = "professors")
    private List<Professor> professorList;

    @JsonProperty(value = "disciplines")
    private List<Discipline> disciplineList;

    public Transaction() {
    }

    public Transaction(List<Professor> professorList, List<Discipline> disciplineList) {
        this.professorList = professorList;
        this.disciplineList = disciplineList;
    }

    public List<Professor> getProfessorList() {
        return professorList;
    }

    public void setProfessorList(List<Professor> professorList) {
        this.professorList = professorList;
    }

    public List<Discipline> getDisciplineList() {
        return disciplineList;
    }

    public void setDisciplineList(List<Discipline> disciplineList) {
        this.disciplineList = disciplineList;
    }
}
