package de.ite.adventofcode.day12;

import de.ite.adventofcode.CalculatorTest;

class HillClimbingPathCalculatorTest extends CalculatorTest<Integer, Integer, HillClimbingPathCalculator> {

    public HillClimbingPathCalculatorTest() {
        super("day12_heightmap_testdata.txt", 31, 29);
    }

    @Override
    public Class<HillClimbingPathCalculator> getTestClassName() {
        return HillClimbingPathCalculator.class;
    }
}