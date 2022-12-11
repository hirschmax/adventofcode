package de.ite.adventofcode.day09;

import de.ite.adventofcode.InputUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RopeMotionCalculatorTest {

    @Test
    void solveFirstTask() {
        List<String> input = InputUtils.readInput("day09_rope_motions_testdata_1.txt");
        RopeMotionCalculator ropeMotionsCalculator = new RopeMotionCalculator(input);
        int result = ropeMotionsCalculator.solveFirstTask();
        assertThat(result).isEqualTo(13);
    }

    @Test
    void solveSecondTask() {
        List<String> input = InputUtils.readInput("day09_rope_motions_testdata_1.txt");
        RopeMotionCalculator ropeMotionsCalculator = new RopeMotionCalculator(input);
        int result = ropeMotionsCalculator.solveSecondTask();
        assertThat(result).isEqualTo(1);
    }

    @Test
    void solveSecondTaskForLargerExample() {
        List<String> input = InputUtils.readInput("day09_rope_motions_testdata_2.txt");
        RopeMotionCalculator ropeMotionsCalculator = new RopeMotionCalculator(input);
        int result = ropeMotionsCalculator.solveSecondTask();
        assertThat(result).isEqualTo(36);
    }
}