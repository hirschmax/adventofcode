package de.ite.adventofcode.day12;

import de.ite.adventofcode.Calculator;
import de.ite.adventofcode.InputUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class HillClimbingPathCalculator extends Calculator<Integer, Integer> {

    public static void main(String[] args) {
        String input = InputUtils.readInputAsString("day12_heightmap.txt");
        HillClimbingPathCalculator hillClimbingPathCalculator = new HillClimbingPathCalculator(input);
        log.info("What is the fewest steps required to move from your current position to the location that should get the best signal?");
        log.info("Task 01: {}", hillClimbingPathCalculator.solveFirstTask());
        log.info("What is the fewest steps required to move starting from any square with elevation a to the location that should get the best signal?");
        log.info("Task 02: {}", hillClimbingPathCalculator.solveSecondTask());
    }
    public HillClimbingPathCalculator(String input) {
        super(input.lines().toList());
    }

    @Override
    public Integer solveFirstTask() {
        ElevationMap elevationMap = ElevationMapReader.readElevationMap(inputLines);
        int distance = elevationMap.calculateFewestStepsRequiredForStartAt(new Position(0, 0));
        log.info("Starting position: (0,0)");
        elevationMap.print();
        return distance;
    }

    @Override
    public Integer solveSecondTask() {
        ElevationMap elevationMap = ElevationMapReader.readElevationMap(inputLines);
        int distance = elevationMap.calculateFewestStepsRequiredForStartingAtAnySquareWithElevationZero();
        elevationMap.print();
        return distance;
    }

}
