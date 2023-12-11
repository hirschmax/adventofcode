package de.ite.adventofcode.day16;

import de.ite.adventofcode.CalculatorTest;
import de.ite.adventofcode.InputUtils;

import static org.junit.jupiter.api.Assertions.*;

class ValvePressureReleaseCalculatorTest extends CalculatorTest<Integer, Integer, ValvePressureReleaseCalculator> {

    public ValvePressureReleaseCalculatorTest() {
        super("day16_valves_testdata.txt", 1651, -1);
    }

    @Override
    public Class<ValvePressureReleaseCalculator> getTestClassName() {
        return ValvePressureReleaseCalculator.class;
    }
}