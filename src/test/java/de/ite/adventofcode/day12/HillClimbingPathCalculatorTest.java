package de.ite.adventofcode.day12;

import de.ite.adventofcode.InputUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class HillClimbingPathCalculatorTest {

    @Test
    void solveFirstTask() {
        List<String> input = InputUtils.readInput("day12_heightmap_testdata.txt");
        HillClimbingPathCalculator hillClimbingPathCalculator = new HillClimbingPathCalculator(input);
        int steps = hillClimbingPathCalculator.solveFirstTask();
        assertThat(steps).isEqualTo(31);
    }

    @Test
    void solveSecondTask() {
        List<String> input = InputUtils.readInput("day12_heightmap_testdata.txt");
        HillClimbingPathCalculator hillClimbingPathCalculator = new HillClimbingPathCalculator(input);
        int steps = hillClimbingPathCalculator.solveSecondTask();
        assertThat(steps).isEqualTo(29);
    }

}