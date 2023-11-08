package br.udesc.entities;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class Transaction {

    @JSONField(name = "professors")
    private List<Professor> professorList;

    public Transaction() {
    }

    public Transaction(List<Professor> professorList) {
        this.professorList = professorList;
    }

    public List<Professor> getProfessorList() {
        return professorList;
    }

    public void setProfessorList(List<Professor> professorList) {
        this.professorList = professorList;
    }
}
