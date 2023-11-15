package br.udesc.entities;

import br.udesc.entities.exceptions.InvalidArgumentException;
import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;

public enum ClassRestriction {

    NONE("NENHUMA"),
    TOGETHER( "JUNTAS"),
    SEPARATE("SEPARADAS");

    private final String classRestriction;

    ClassRestriction(String classRestriction) {
        this.classRestriction = classRestriction;
    }

    @JSONField
    public String getClassRestriction() {
        return classRestriction;
    }

    @JSONCreator
    public static ClassRestriction from(String value) {
        for (ClassRestriction classRestriction : values()) {
            if (classRestriction.getClassRestriction().equals(value))
                return classRestriction;
        }
        throw new InvalidArgumentException("Invalid class restriction: " + value);
    }
}
