package de.ite.adventofcode.day05;

import de.ite.adventofcode.InputUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SupplyStackCalculatorTest {

    @Test
    void shouldSolveFirstTaskWithResultCMZ() {
        List<String> input = InputUtils.readInput("day05_supply_stacks_testdata.txt");
        SupplyStackCalculator supplyStackCalculator = new SupplyStackCalculator(input);
        String result = supplyStackCalculator.solveFirstTask();
        assertThat(result).isEqualTo("CMZ");
    }

    @Test
    void shouldSolveSecondTaskWithResultMCD() {
        List<String> input = InputUtils.readInput("day05_supply_stacks_testdata.txt");
        SupplyStackCalculator supplyStackCalculator = new SupplyStackCalculator(input);
        String result = supplyStackCalculator.solveSecondTask();
        assertThat(result).isEqualTo("MCD");
    }
}