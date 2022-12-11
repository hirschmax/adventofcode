package de.ite.adventofcode.day09;

import de.ite.adventofcode.Calculator;
import de.ite.adventofcode.InputUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

@Slf4j
public class RopeMotionCalculator extends Calculator<Integer, Integer> {

    public static void main(String[] args) {
        List<String> inputLines = InputUtils.readInput("day09_rope_motions.txt");
        RopeMotionCalculator ropeMotionCalculator = new RopeMotionCalculator(inputLines);
        log.info("Simulate your complete hypothetical series of motions. How many positions does the tail of the rope visit at least once?");
        log.info("Task 01: {}", ropeMotionCalculator.solveFirstTask());
        log.info("Simulate your complete series of motions on a larger rope with ten knots. How many positions does the tail of the rope visit at least once?");
        log.info("Task 02: {}", ropeMotionCalculator.solveSecondTask());
    }
    public RopeMotionCalculator(List<String> inputLines) {
        super(inputLines);
    }
    private List<Motion> initSeriesOfMotions() {
        List<Motion> motions = new LinkedList<>();
        for (String inputLine : inputLines) {
            String[] split = inputLine.split(" ");
            Motion motion = Motion.getForCode(split[0]);
            int times = Integer.parseInt(split[1]);
            IntStream.range(0, times).forEach(i -> motions.add(motion));
        }
        return motions;
    }

    @Override
    public Integer solveFirstTask() {
        List<Motion> motions = initSeriesOfMotions();
        Rope rope = new Rope(2);

        for (Motion motion : motions) {
            rope.move(motion);
        }

        Set<Position> positionsVisited = rope.getTail().getPositionsVisited();
        printVisitedPositions(positionsVisited);

        return positionsVisited.size();
    }

    @Override
    public Integer solveSecondTask() {
        List<Motion> motions = initSeriesOfMotions();
        Rope rope = new Rope(10);

        for (Motion motion : motions) {
            rope.move(motion);
        }

        Set<Position> positionsVisited = rope.getTail().getPositionsVisited();

        printVisitedPositions(positionsVisited);

        return positionsVisited.size();
    }

    private void printVisitedPositions(Set<Position> positionsVisited) {
        int minX = positionsVisited.stream().map(Position::x).mapToInt(Integer::intValue).min().orElse(0);
        int minY = positionsVisited.stream().map(Position::y).mapToInt(Integer::intValue).min().orElse(0);
        int maxX = positionsVisited.stream().map(Position::x).mapToInt(Integer::intValue).max().orElse(0);
        int maxY = positionsVisited.stream().map(Position::y).mapToInt(Integer::intValue).max().orElse(0);

        for(int j = maxY; j >= minY; j--) {
            StringBuilder line = new StringBuilder();
            for(int i = minX; i <= maxX; i++) {
                if(i == 0 && j == 0) {
                    line.append("s");
                } else {
                    line.append(positionsVisited.contains(new Position(i, j)) ? "#" : ".");
                }
            }
            log.info("{}", line);
        }
    }
}
