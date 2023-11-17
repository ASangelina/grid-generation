package br.udesc.result;

import java.util.HashMap;
import java.util.Map;

public class Day {
        private String dayOfWeek;
        private Map<String, Lesson> lessons;


        public Day() {
            this.lessons = new HashMap<>();
        }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Map<String, Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Map<String, Lesson> lessons) {
        this.lessons = lessons;
    }
}


