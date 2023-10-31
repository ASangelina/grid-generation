package br.udesc.entities;

public enum TimeRestriction {

    NONE(0),
    START(1),
    END(2);

    private final int value;

    TimeRestriction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
