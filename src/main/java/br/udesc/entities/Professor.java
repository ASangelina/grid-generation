package br.udesc.entities;

import java.util.List;

public class Professor {

    private int code;
    private String name;
    private List<String> disciplines;
    private List<String> restrictedDays;

    public Professor(int code, String name, List<String> disciplines, List<String> restrictedDays) {
        this.code = code;
        this.name = name;
        this.disciplines = disciplines;
        this.restrictedDays = restrictedDays;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<String> disciplines) {
        this.disciplines = disciplines;
    }

    public List<String> getRestrictedDays() {
        return restrictedDays;
    }

    public void setRestrictedDays(List<String> restrictedDays) {
        this.restrictedDays = restrictedDays;
    }
}
