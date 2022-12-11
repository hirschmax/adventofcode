package de.ite.adventofcode.day10;

import de.ite.adventofcode.InputUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
class CathodeRayTubeCalculatorTest {

    @Test
    void solveFirstTask() {
        List<String> input = InputUtils.readInput("day10_program_testdata.txt");
        CathodeRayTubeCalculator cathodeRayTubeCalculator = new CathodeRayTubeCalculator(input);
        int result = cathodeRayTubeCalculator.solveFirstTask();
        assertThat(result).isEqualTo(13_140);
    }

    @Test
    void solveSecondTask() {
        List<String> input = InputUtils.readInput("day10_program_testdata.txt");
        CathodeRayTubeCalculator cathodeRayTubeCalculator = new CathodeRayTubeCalculator(input);
        String result = cathodeRayTubeCalculator.solveSecondTask();
        assertThat(result).isEqualTo("""
                ##..##..##..##..##..##..##..##..##..##..
                ###...###...###...###...###...###...###.
                ####....####....####....####....####....
                #####.....#####.....#####.....#####.....
                ######......######......######......####
                #######.......#######.......#######.....
                """);
    }
}