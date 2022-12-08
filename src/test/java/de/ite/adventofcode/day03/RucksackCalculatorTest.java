package de.ite.adventofcode.day03;

import de.ite.adventofcode.InputUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RucksackCalculatorTest {

    @Test
    void shouldSolveFirstTaskWithResult157() {
        List<String> input = InputUtils.readInput("day03_rucksack_content_testdata.txt");
        RucksackCalculator rucksackCalculator = new RucksackCalculator(input);
        int sum = rucksackCalculator.solveFirstTask();
        assertThat(sum).isEqualTo(157);
    }

    @Test
    void shouldSolveSecondTaskWithResult70() {
        List<String> input = InputUtils.readInput("day03_rucksack_content_testdata.txt");
        RucksackCalculator rucksackCalculator = new RucksackCalculator(input);
        int sum = rucksackCalculator.solveSecondTask();
        assertThat(sum).isEqualTo(70);
    }


}