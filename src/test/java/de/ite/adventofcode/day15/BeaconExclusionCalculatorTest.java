package de.ite.adventofcode.day15;

import de.ite.adventofcode.CalculatorTest;
import de.ite.adventofcode.InputUtils;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BeaconExclusionCalculatorTest extends CalculatorTest<Integer, BigInteger, BeaconExclusionCalculator> {

    public BeaconExclusionCalculatorTest() {
        super("day15_sensors_beacons_test.txt", 26, BigInteger.valueOf(56_000_011L));
    }

    @Test
    @Override
    protected void solveFirstTask() {
        String input = InputUtils.readInputAsString(firstInput);
        BeaconExclusionCalculator calculator = new BeaconExclusionCalculator(input);
        calculator.setRowToCheck(10);
        calculator.setPrint(true);
        int result = calculator.solveFirstTask();
        assertThat(result).isEqualTo(firstResult);
    }

    @Test
    @Override
    protected void solveSecondTask() {
        String input = InputUtils.readInputAsString(secondInput);
        BeaconExclusionCalculator calculator = new BeaconExclusionCalculator(input);
        calculator.setRowToCheck(10);
        calculator.setPrint(true);
        calculator.setSearchSpace(0, 20);
        BigInteger result = calculator.solveSecondTask();
        assertThat(result).isEqualTo(secondResult);
    }

    @Override
    public Class<BeaconExclusionCalculator> getTestClassName() {
        return BeaconExclusionCalculator.class;
    }
}