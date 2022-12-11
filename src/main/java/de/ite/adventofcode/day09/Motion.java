package de.ite.adventofcode.day09;

public enum Motion {
    UP("U"), RIGHT("R"), DOWN("D"), LEFT("L");

    final String code;

    Motion(String code) {
        this.code = code;
    }

    static Motion getForCode(String code) {
        for (Motion value : values()) {
            if(value.code.equals(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException(code);
    }
}
