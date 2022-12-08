package de.ite.adventofcode.day03;

import de.ite.adventofcode.InputUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RucksackCalculatorTest {

    @Test
    void shouldReturnSumOf157() {
        List<String> input = InputUtils.readInput("day03_rucksack_content_testdata.txt");
        RucksackCalculator rucksackCalculator = new RucksackCalculator(input);
        int sum = rucksackCalculator.getSumOfPrioritiesForItemsThatArePutToBothCompartments();
        assertThat(sum).isEqualTo(157);
    }

    @Test
    void shouldReturnSumOf70() {
        List<String> input = InputUtils.readInput("day03_rucksack_content_testdata.txt");
        RucksackCalculator rucksackCalculator = new RucksackCalculator(input);
        int sum = rucksackCalculator.getSumOfPrioritiesThreeElvesGroups();
        assertThat(sum).isEqualTo(70);
    }


}