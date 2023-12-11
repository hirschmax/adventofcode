package de.ite.adventofcode.day03;

import de.ite.adventofcode.CalculatorTest;

import java.math.BigInteger;

class EngineCalculatorTest extends CalculatorTest<Integer, BigInteger, EngineSchematicCalculator> {

    public EngineCalculatorTest() {
        super("day03_testdata.txt", 4361, BigInteger.valueOf(467835));
    }

    @Override
    public Class<EngineSchematicCalculator> getTestClassName() {
        return EngineSchematicCalculator.class;
    }
}