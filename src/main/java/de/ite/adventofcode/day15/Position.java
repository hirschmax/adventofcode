package de.ite.adventofcode.day15;

public record Position(int x, int y) {
    public int getDistanceTo(Position position) {
        return Math.abs(x - position.x) + Math.abs(y - position.y);
    }

}
