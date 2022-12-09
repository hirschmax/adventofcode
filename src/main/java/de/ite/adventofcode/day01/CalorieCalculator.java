package de.ite.adventofcode.day01;

import de.ite.adventofcode.Calculator;
import de.ite.adventofcode.InputUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class CalorieCalculator extends Calculator<Integer> {

    public static void main(String[] args) {
        List<String> inputLines = InputUtils.readInput("day01_calories.txt");
        CalorieCalculator calorieComputer = new CalorieCalculator(inputLines);
        log.info("Number of calories which the elf carrying the most calories is carrying: {}", calorieComputer.getCaloriesCarriedByElfWithMostCalories());
        log.info("Sum of calories which the three elves carrying the most calories are carrying: {}", calorieComputer.getCaloriesCarriedByThreeElvesWithMostCalories());
    }

    private final List<Elf> elves = new ArrayList<>();

    public CalorieCalculator(List<String> input) {
        super(input);
        init(input);
    }

    @Override
    public Integer solveFirstTask() {
        return getCaloriesCarriedByElfWithMostCalories();
    }

    @Override
    public Integer solveSecondTask() {
        return getCaloriesCarriedByThreeElvesWithMostCalories();
    }

    private void init(List<String> input) {
        Elf currentElf = new Elf();
        elves.add(currentElf);

        for (String line : input) {
            if(line.isBlank()) {
                currentElf = new Elf();
                elves.add(currentElf);
                continue;
            }
            currentElf.getItems().add(Integer.parseInt(line));
        }
        elves.sort(Comparator.comparing(Elf::getCaloriesCarried).reversed());
    }

    private int getCaloriesCarriedByElfWithMostCalories() {
        return elves.get(0).getCaloriesCarried();
    }

    private int getCaloriesCarriedByThreeElvesWithMostCalories() {
        return elves.subList(0, 3).stream().mapToInt(Elf::getCaloriesCarried).sum();
    }
}
