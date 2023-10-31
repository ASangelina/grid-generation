package br.udesc.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Professor {

    @JsonProperty(value = "codigo_professor")
    private String code;

    @JsonProperty(value = "nome_do_professor")
    private String name;

    @JsonProperty(value = "disciplinas_codico")
    private List<String> disciplines;

    @JsonProperty(value = "dias_preferenciais")
    private List<Weekdays> preferenceDays;

    @JsonProperty(value = "dias_obrigatorio")
    private List<Weekdays> mandatoryDays;

    public Professor(String code, String name, List<String> disciplines, List<Weekdays> preferenceDays, List<Weekdays> mandatoryDays) {
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

    public List<String> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<String> disciplines) {
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
