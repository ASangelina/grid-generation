package br.udesc.entities;

import br.udesc.entities.exceptions.InvalidArgumentException;
import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;

public enum Weekday {

    MONDAY("SEGUNDA"),
    TUESDAY("TERÇA"),
    WEDNESDAY("QUARTA"),
    THURSDAY("QUINTA"),
    FRIDAY("SEXTA"),
    SATURDAY("SÁBADO");

    private final String ptWeekDay;

    Weekday(String ptWeekDay) {
        this.ptWeekDay = ptWeekDay;
    }

    @JSONField
    public String inPortuguese() {
        return ptWeekDay;
    }

    @JSONCreator
    public static Weekday from(String ptWeekDay) {
        for (Weekday weekDay : values()) {
            if (weekDay.inPortuguese().equals(ptWeekDay))
                return weekDay;
        }
        throw new InvalidArgumentException("Invalid week day: " + ptWeekDay);
    }
}
