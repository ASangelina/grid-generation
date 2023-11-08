package br.udesc.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Professor {

    @JsonProperty(value = "code")
    private String code;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "disciplines")
    private List<Discipline> disciplines;

    @JsonProperty(value = "preference_days")
    private List<Weekdays> preferenceDays;

    @JsonProperty(value = "mandatory_days")
    private List<Weekdays> mandatoryDays;

    public Professor(String code, String name, List<Discipline> disciplines, List<Weekdays> preferenceDays, List<Weekdays> mandatoryDays) {
        this.code = code;
        this.name = name;
        this.disciplines = disciplines;
        this.preferenceDays = preferenceDays;
        this.mandatoryDays = mandatoryDays;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    public List<Weekdays> getPreferenceDays() {
        return preferenceDays;
    }

    public void setPreferenceDays(List<Weekdays> preferenceDays) {
        this.preferenceDays = preferenceDays;
    }

    public List<Weekdays> getMandatoryDays() {
        return mandatoryDays;
    }

    public void setMandatoryDays(List<Weekdays> mandatoryDays) {
        this.mandatoryDays = mandatoryDays;
    }
}
