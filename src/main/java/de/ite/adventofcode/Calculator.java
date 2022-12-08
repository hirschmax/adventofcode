package de.ite.adventofcode;

import java.util.List;

public abstract class Calculator {

    protected final List<String> inputLines;

    protected Calculator(List<String> inputLines) {
        this.inputLines = inputLines;
    }

    public abstract int solveFirstTask();
    public abstract int solveSecondTask();

}
