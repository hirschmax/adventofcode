package de.ite.adventofcode.day10;

public abstract class Command {
    private final int cycles;
    private final int value;

    protected Command(int cycles, int value) {
        this.cycles = cycles;
        this.value = value;
    }

    public int getCycles() {
        return cycles;
    }

    public int getValue() {
        return value;
    }
}
