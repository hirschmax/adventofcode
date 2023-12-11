package de.ite.adventofcode.day01;

import de.ite.adventofcode.CalculatorTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CalibrationValuesCalculatorTest extends CalculatorTest<Integer, Integer, CalibrationValuesCalculator> {

    public CalibrationValuesCalculatorTest() {
        super("day01_testdata_1.txt", "day01_testdata_2.txt", 142, 281);
    }

    @Test
    void testSpecificLine() {
        CalibrationValuesCalculator calculator = new CalibrationValuesCalculator("");
        int pcg91vqrfpxxzzzoneightzt = calculator.getFirstDigitFromSideIncludingWritten("pcg91vqrfpxxzzzoneightzt", CalibrationValuesCalculator.Side.RIGHT);
        assertThat(pcg91vqrfpxxzzzoneightzt).isEqualTo(8);
    }

    @Override
    public Class<CalibrationValuesCalculator> getTestClassName() {
        return CalibrationValuesCalculator.class;
    }
}