package br.udesc.result;

import java.util.Map;

public class ProfessorSchedule {
        private String professorName;
        private Map<String, Day> schedule;

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public Map<String, Day> getSchedule() {
        return schedule;
    }

    public void setSchedule(Map<String, Day> schedule) {
        this.schedule = schedule;
    }


}
