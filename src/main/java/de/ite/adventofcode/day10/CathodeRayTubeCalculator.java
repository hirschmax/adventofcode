package de.ite.adventofcode.day10;

import de.ite.adventofcode.Calculator;
import de.ite.adventofcode.InputUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public class CathodeRayTubeCalculator extends Calculator<Integer, String> {

    public static void main(String[] args) {
        List<String> inputLines = InputUtils.readInput("day10_program.txt");
        CathodeRayTubeCalculator cathodeRayTubeCalculator = new CathodeRayTubeCalculator(inputLines);
        log.info("Find the signal strength during the 20th, 60th, 100th, 140th, 180th, and 220th cycles. What is the sum of these six signal strengths?");
        log.info("Task 01: {}", cathodeRayTubeCalculator.solveFirstTask());
        log.info("Render the image given by your program. What eight capital letters appear on your CRT?");
        log.info("Task 02: \n{}", cathodeRayTubeCalculator.solveSecondTask().replace(".", " "));
    }


    private static final int CRT_WIDTH = 40;
    private static final int CRT_HEIGHT = 6;
    private static final String LIT_PIXEL = "#";
    private static final String DARK_PIXEL = ".";

    public CathodeRayTubeCalculator(List<String> inputLines) {
        super(inputLines);
    }

    @Override
    public Integer solveFirstTask() {
        Program program = new Program(inputLines);
        program.execute();
        List<Integer> interestingCycles = List.of(20, 60, 100, 140, 180, 220);
        return program.getSignalStrengthForCycles(interestingCycles).values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public String solveSecondTask() {
        Program program = new Program(inputLines);
        program.execute();
        return renderImage(program);
    }

    private String renderImage(Program program) {
        Map<Integer, Integer> cycleToRegisterValueMap = program.getCycleToRegisterValueMap();

        StringBuilder result = new StringBuilder();
        for(int row = 0; row < CRT_HEIGHT; row++) {
            StringBuilder currentRow = new StringBuilder();
            for(int drawnPixel = 0; drawnPixel < CRT_WIDTH; drawnPixel++) {
                int cycle = 1 + drawnPixel + row* CRT_WIDTH;
                int spritePosition = cycleToRegisterValueMap.get(cycle);
                boolean isPixelLit = Math.abs(drawnPixel - spritePosition) <= 1;
                currentRow.append(isPixelLit ? LIT_PIXEL : DARK_PIXEL);
            }
            result.append(currentRow).append("\n");
        }
        return result.toString();
    }

}
