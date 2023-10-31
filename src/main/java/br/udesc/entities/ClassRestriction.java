package br.udesc.entities;

public enum ClassRestriction {

    NONE(0),
    TOGETHER(1),
    SEPARATE(2);

    private final int value;

    ClassRestriction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
