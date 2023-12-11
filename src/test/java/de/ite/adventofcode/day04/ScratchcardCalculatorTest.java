package de.ite.adventofcode.day04;

import de.ite.adventofcode.CalculatorTest;

import java.math.BigInteger;

class ScratchcardCalculatorTest extends CalculatorTest<BigInteger, BigInteger, ScratchcardCalculator> {

    public ScratchcardCalculatorTest() {
        super("day04_testdata.txt", BigInteger.valueOf(13), BigInteger.valueOf(30));
    }

    @Override
    public Class<ScratchcardCalculator> getTestClassName() {
        return ScratchcardCalculator.class;
    }
}