package br.udesc.entities;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class Professor {

    @JSONField(name = "code")
    private String code;

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "disciplines")
    private List<Discipline> disciplines;

    @JSONField(name = "preference_days")
    private List<Weekday> preferenceDays;

    @JSONField(name = "mandatory_days")
    private List<Weekday> mandatoryDays;

    public Professor(String code, String name, List<Discipline> disciplines, List<Weekday> preferenceDays, List<Weekday> mandatoryDays) {
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

    public List<Weekday> getPreferenceDays() {
        return preferenceDays;
    }

    public void setPreferenceDays(List<Weekday> preferenceDays) {
        this.preferenceDays = preferenceDays;
    }

    public List<Weekday> getMandatoryDays() {
        return mandatoryDays;
    }

    public void setMandatoryDays(List<Weekday> mandatoryDays) {
        this.mandatoryDays = mandatoryDays;
    }
}
