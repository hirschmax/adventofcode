package de.ite.adventofcode.day01;

import de.ite.adventofcode.InputUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CalorieComputerTest {

    @Test
    void shouldSolveFirstTaskWithResult24000() {
        List<String> input = InputUtils.readInput("day01_calories_testdata.txt");
        CalorieCalculator calorieComputer = new CalorieCalculator(input);
        int caloriesCarriedByElfWithMostCalories = calorieComputer.solveFirstTask();
        assertThat(caloriesCarriedByElfWithMostCalories).isEqualTo(24_000);
    }

    @Test
    void shouldSolveSecondTaskWithResult45000() {
        List<String> input = InputUtils.readInput("day01_calories_testdata.txt");
        CalorieCalculator calorieComputer = new CalorieCalculator(input);
        int caloriesCarriedByElfWithMostCalories = calorieComputer.solveSecondTask();
        assertThat(caloriesCarriedByElfWithMostCalories).isEqualTo(45_000);
    }
}