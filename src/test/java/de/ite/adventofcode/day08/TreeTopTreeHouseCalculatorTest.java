package de.ite.adventofcode.day08;

import de.ite.adventofcode.InputUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TreeTopTreeHouseCalculatorTest {

    @Test
    void solveFirstTask() {
        List<String> input = InputUtils.readInput("day08_tree_height_map_testdata.txt");
        TreeTopTreeHouseCalculator treeTopTreeHouseCalculator = new TreeTopTreeHouseCalculator(input);
        int result = treeTopTreeHouseCalculator.solveFirstTask();
        assertThat(result).isEqualTo(21);
    }

    @Test
    void solveSecondTask() {
        List<String> input = InputUtils.readInput("day08_tree_height_map_testdata.txt");
        TreeTopTreeHouseCalculator treeTopTreeHouseCalculator = new TreeTopTreeHouseCalculator(input);
        int result = treeTopTreeHouseCalculator.solveSecondTask();
        assertThat(result).isEqualTo(8);
    }
}