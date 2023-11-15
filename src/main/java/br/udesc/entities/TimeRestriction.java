package br.udesc.entities;

import br.udesc.entities.exceptions.InvalidArgumentException;
import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;

public enum TimeRestriction {

    NONE("NENHUMA"),
    START("INICIO"),
    END("FIM");

    private final String timeRestriction;

    TimeRestriction(String timeRestriction) {
        this.timeRestriction = timeRestriction;
    }

    @JSONField
    public String getTimeRestriction() {
        return timeRestriction;
    }

    @JSONCreator
    public TimeRestriction from(String value) {
        for (TimeRestriction restriction : values()) {
            if (restriction.getTimeRestriction().equals(value))
                return restriction;
        }
        throw new InvalidArgumentException("Invalid time restriction: " + value);
    }
}
