package de.ite.adventofcode.day14;

import de.ite.adventofcode.CalculatorTest;

class FallingSandCalculatorTest extends CalculatorTest<Integer, Integer, FallingSandCalculator> {

    public FallingSandCalculatorTest() {
        super("day14_rock_paths_testdata.txt", 24, 93);
    }

    @Override
    public Class<FallingSandCalculator> getTestClassName() {
        return FallingSandCalculator.class;
    }
}