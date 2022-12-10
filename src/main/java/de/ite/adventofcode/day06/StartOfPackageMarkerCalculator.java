package de.ite.adventofcode.day06;

import de.ite.adventofcode.Calculator;
import de.ite.adventofcode.InputUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class StartOfPackageMarkerCalculator extends Calculator<Integer> {

    public static void main(String[] args) {
        List<String> inputLines = InputUtils.readInput("day06_signal.txt");
        StartOfPackageMarkerCalculator startOfPackageMarkerCalculator = new StartOfPackageMarkerCalculator(inputLines);
        log.info("How many characters need to be processed before the first start-of-packet marker is detected?");
        log.info("Task 01: {}", startOfPackageMarkerCalculator.solveFirstTask());
        log.info("Task 02: {}", startOfPackageMarkerCalculator.solveSecondTask());
    }
    public StartOfPackageMarkerCalculator(List<String> inputLines) {
        super(inputLines);
    }

    @Override
    public Integer solveFirstTask() {
        String signal = inputLines.stream().findFirst().orElseThrow(IndexOutOfBoundsException::new);
        return findMarkerForSignal(Marker.START_OF_PAKET, signal);
    }


    @Override
    public Integer solveSecondTask() {
        String signal = inputLines.stream().findFirst().orElseThrow(IndexOutOfBoundsException::new);
        return findMarkerForSignal(Marker.START_OF_MESSAGE, signal);
    }


    private int findMarkerForSignal(Marker marker, String signal) {
        int numberOfDistinctCharacters = marker.numberOfDistinctCharacters;
        for(int i = 0; (i + numberOfDistinctCharacters - 1) < signal.length(); i++) {
            boolean hasNoRepetition = signal.substring(i, i + numberOfDistinctCharacters).chars().distinct().count() == numberOfDistinctCharacters;
            if(hasNoRepetition) {
                return i + numberOfDistinctCharacters;
            }
        }
        return -1;
    }

    enum Marker {
        START_OF_PAKET(4),
        START_OF_MESSAGE(14);

        final int numberOfDistinctCharacters;
        Marker(int numberOfDistinctCharacters) {
            this.numberOfDistinctCharacters = numberOfDistinctCharacters;
        }
    }

}
