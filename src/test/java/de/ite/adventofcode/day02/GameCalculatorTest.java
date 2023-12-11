package de.ite.adventofcode.day02;

import de.ite.adventofcode.CalculatorTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class GameCalculatorTest extends CalculatorTest<Integer, BigInteger, GameCalculator> {

    public GameCalculatorTest() {
        super("day02_testdata.txt", 8, BigInteger.valueOf(2286));
    }

    @Override
    public Class<GameCalculator> getTestClassName() {
        return GameCalculator.class;
    }
}