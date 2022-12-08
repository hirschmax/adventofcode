package de.ite.adventofcode.day02;

import de.ite.adventofcode.InputUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RockPaperScissorsTest {

    @Test
    void shouldReturnTotalScoreOf15() {
        List<String> input = InputUtils.readInput("day02_encrypted_strategy_guide_testdata.txt");
        RockPaperScissors rockPaperScissors = new RockPaperScissors(input);
        int totalScore = rockPaperScissors.calculateForBothHandShapes();
        assertThat(totalScore).isEqualTo(15);
    }

    @Test
    void shouldReturnTotalScoreOf12() {
        List<String> input = InputUtils.readInput("day02_encrypted_strategy_guide_testdata.txt");
        RockPaperScissors rockPaperScissors = new RockPaperScissors(input);
        int totalScore = rockPaperScissors.calculateForStrategyGuide();
        assertThat(totalScore).isEqualTo(12);
    }

}