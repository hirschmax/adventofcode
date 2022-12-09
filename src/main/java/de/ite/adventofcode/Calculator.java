package de.ite.adventofcode;

import java.util.List;

public abstract class Calculator<T> {

    protected final List<String> inputLines;

    protected Calculator(List<String> inputLines) {
        this.inputLines = inputLines;
    }

    public abstract T solveFirstTask();
    public abstract T solveSecondTask();

}
