package de.ite.adventofcode.day11;

import de.ite.adventofcode.InputUtils;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class MonkeyInTheMiddleCalculatorTest {

    @Test
    void solveFirstTask() {
        List<String> input = InputUtils.readInput("day11_notes_testdata.txt");
        MonkeyInTheMiddleCalculator monkeyInTheMiddleCalculator = new MonkeyInTheMiddleCalculator(input);
        BigInteger result = monkeyInTheMiddleCalculator.solveFirstTask();
        assertThat(result).isEqualTo(BigInteger.valueOf(10_605L));
    }


    @Test
    void solveSecondTask() {
        List<String> input = InputUtils.readInput("day11_notes_testdata.txt");
        MonkeyInTheMiddleCalculator monkeyInTheMiddleCalculator = new MonkeyInTheMiddleCalculator(input);
        BigInteger result = monkeyInTheMiddleCalculator.solveSecondTask();
        assertThat(result).isEqualTo(BigInteger.valueOf(2_713_310_158L));
    }
}