package br.udesc.result;

import java.util.List;
import java.util.Map;

public class DisciplineSchedule {
    private String phase;
    private Map<String, Map<String, List<Map<String, String>>>> schedule;


    public String getFase() {
        return phase;
    }

    public void setFase(String phase) {
        this.phase = phase;
    }

    public Map<String, Map<String, List<Map<String, String>>>> getSchedule() {
        return schedule;
    }

    public void setSchedule(Map<String, Map<String, List<Map<String, String>>>> schedule) {
        this.schedule = schedule;
    }
}
