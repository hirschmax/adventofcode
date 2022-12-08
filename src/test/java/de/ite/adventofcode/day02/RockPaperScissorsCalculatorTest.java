package de.ite.adventofcode.day02;

import de.ite.adventofcode.InputUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RockPaperScissorsCalculatorTest {

    @Test
    void shouldSolveFirstTaskWithResult15() {
        List<String> input = InputUtils.readInput("day02_encrypted_strategy_guide_testdata.txt");
        RockPaperScissorsCalculator rockPaperScissorsCalculator = new RockPaperScissorsCalculator(input);
        int totalScore = rockPaperScissorsCalculator.solveFirstTask();
        assertThat(totalScore).isEqualTo(15);
    }

    @Test
    void shouldSolveSecondTaskWithResult12() {
        List<String> input = InputUtils.readInput("day02_encrypted_strategy_guide_testdata.txt");
        RockPaperScissorsCalculator rockPaperScissorsCalculator = new RockPaperScissorsCalculator(input);
        int totalScore = rockPaperScissorsCalculator.solveSecondTask();
        assertThat(totalScore).isEqualTo(12);
    }

}