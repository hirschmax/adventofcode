package de.ite.adventofcode.day04;

import de.ite.adventofcode.InputUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SectionAssignmentCalculatorTest {

    @Test
    void shouldSolveFirstTaskWithResult2() {
        List<String> input = InputUtils.readInput("day04_section_assignments_testdata.txt");
        SectionAssignmentCalculator sectionAssignmentCalculator = new SectionAssignmentCalculator(input);
        int result = sectionAssignmentCalculator.solveFirstTask();
        assertThat(result).isEqualTo(2);
    }

    @Test
    void solveSecondTaskWithResult4() {
        List<String> input = InputUtils.readInput("day04_section_assignments_testdata.txt");
        SectionAssignmentCalculator sectionAssignmentCalculator = new SectionAssignmentCalculator(input);
        int result = sectionAssignmentCalculator.solveSecondTask();
        assertThat(result).isEqualTo(4);
    }
}