package de.ite.adventofcode.day12;

public enum Direction {

    NONE("."),
    UP("^"),
    RIGHT(">"),
    DOWN("v"),
    LEFT("<");

    final String sign;
    Direction(String sign) {
        this.sign = sign;
    }
}
