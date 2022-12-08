package de.ite.adventofcode.day01;

import de.ite.adventofcode.InputUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CalorieComputerTest {

    @Test
    void shouldReturn24_000ForCaloriesCarriedByElfWithMostCalories() {
        List<String> input = InputUtils.readInput("day01_calories_testdata.txt");
        CalorieComputer calorieComputer = new CalorieComputer(input);
        int caloriesCarriedByElfWithMostCalories = calorieComputer.getCaloriesCarriedByElfWithMostCalories();
        assertThat(caloriesCarriedByElfWithMostCalories).isEqualTo(24_000);
    }

    @Test
    void shouldReturn45_000ForCaloriesCarriedByThreeElvesWithMostCalories() {
        List<String> input = InputUtils.readInput("day01_calories_testdata.txt");
        CalorieComputer calorieComputer = new CalorieComputer(input);
        int caloriesCarriedByElfWithMostCalories = calorieComputer.getCaloriesCarriedByThreeElvesWithMostCalories();
        assertThat(caloriesCarriedByElfWithMostCalories).isEqualTo(45_000);
    }
}