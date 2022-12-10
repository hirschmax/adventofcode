package de.ite.adventofcode.day07;

import de.ite.adventofcode.InputUtils;
import de.ite.adventofcode.day05.SupplyStackCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DirectorySizeCalculatorTest {

    @Test
    void solveFirstTask() {
        List<String> input = InputUtils.readInput("day07_filesystem_testdata.txt");
        DirectorySizeCalculator directorySizeCalculator = new DirectorySizeCalculator(input);
        int result = directorySizeCalculator.solveFirstTask();
        assertThat(result).isEqualTo(95437);
    }

    @Test
    void solveSecondTask() {
        List<String> input = InputUtils.readInput("day07_filesystem_testdata.txt");
        DirectorySizeCalculator directorySizeCalculator = new DirectorySizeCalculator(input);
        int result = directorySizeCalculator.solveSecondTask();
        assertThat(result).isEqualTo(24933642);
    }
}