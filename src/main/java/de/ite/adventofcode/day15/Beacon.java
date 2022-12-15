package de.ite.adventofcode.day15;

public record Beacon(Position position) implements Cell {

    @Override
    public char getCode() {
        return 'B';
    }
}
