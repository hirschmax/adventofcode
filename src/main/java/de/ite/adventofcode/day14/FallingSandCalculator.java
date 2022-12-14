package de.ite.adventofcode.day14;

import de.ite.adventofcode.Calculator;
import de.ite.adventofcode.InputUtils;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class FallingSandCalculator extends Calculator<Integer, Integer> {

    public static void main(String[] args) {
        String input = InputUtils.readInputAsString("day14_rock_paths.txt");
        FallingSandCalculator fallingSandCalculator = new FallingSandCalculator(input);
        log.info("Using your scan, simulate the falling sand. How many units of sand come to rest before sand starts flowing into the abyss below?");
        log.info("Task 01: {}", fallingSandCalculator.solveFirstTask());
        log.info("Using your scan, simulate the falling sand until the source of the sand becomes blocked. How many units of sand come to rest?");
        log.info("Task 02: {}", fallingSandCalculator.solveSecondTask());
    }

    public FallingSandCalculator(String input) {
        super(input.lines().toList());
    }

    @Override
    public Integer solveFirstTask() {
        Cave cave = new Cave(inputLines);
        cave.print();

        boolean isRunning = true;
        while(isRunning) {
            isRunning = cave.simulatePouringSand();
        }

        cave.print();
        return cave.getSandUnitsAtRest();
    }

    @Override
    public Integer solveSecondTask() {
        Cave cave = new Cave(inputLines, true);
        cave.print();

        boolean isRunning = true;
        while(isRunning) {
            isRunning = cave.simulatePouringSand();
        }
        cave.print();
        return cave.getSandUnitsAtRest();
    }

}
