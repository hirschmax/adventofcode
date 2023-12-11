package de.ite.adventofcode.day01;

import de.ite.adventofcode.Calculator;
import de.ite.adventofcode.InputUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
public class CalibrationValuesCalculator extends Calculator<Integer, Integer> {

    public static void main(String... args) {
        CalibrationValuesCalculator calculator = new CalibrationValuesCalculator(InputUtils.readInputAsString("day01.txt"));
        log.info("Result for day 1 task 1: {}", calculator.solveFirstTask());
        log.info("Result for day 1 task 2: {}", calculator.solveSecondTask());
    }

    public CalibrationValuesCalculator(String input) {
        super(input.lines().toList());
    }

    @Override
    public Integer solveFirstTask() {
        List<Integer> calibrationValues = new ArrayList<>();
        for (String inputLine : inputLines) {
            int left = getFirstDigitFromSide(inputLine, Side.LEFT);
            int right = getFirstDigitFromSide(inputLine, Side.RIGHT);
            calibrationValues.add(left * 10 + right);
        }
        return calibrationValues.stream().mapToInt(i -> i).sum();
    }

    @Override
    public Integer solveSecondTask() {
        List<Integer> calibrationValues = new ArrayList<>();
        for (String inputLine : inputLines) {
            int left = getFirstDigitFromSideIncludingWritten(inputLine, Side.LEFT);
            int right = getFirstDigitFromSideIncludingWritten(inputLine, Side.RIGHT);
            calibrationValues.add(left * 10 + right);
        }
        return calibrationValues.stream().mapToInt(i -> i).sum();
    }

    public int getFirstDigitFromSide(String inputLine, Side side) {
        final int length = inputLine.length();
        final int inc = side == Side.LEFT ? 1 : -1;
        final int start = side == Side.LEFT ? 0 : length - 1;
        char ch = inputLine.charAt(start);

        int k = start;
        while(!Character.isDigit(ch)) {
            k = k + inc;
            if(k >= length || k < 0) {
                throw new NoSuchElementException();
            }
            ch = inputLine.charAt(k);
        }
        return ch - '0';
    }

    public int getFirstDigitFromSideIncludingWritten(String inputLine, Side side) {
        final int length = inputLine.length();
        final int inc = side == Side.LEFT ? 1 : -1;
        final int start = side == Side.LEFT ? 0 : length - 1;
        char ch = inputLine.charAt(start);

        int k = start;
        while(!Character.isDigit(ch)) {
            int writtenNumber = getWrittenNumber(inputLine, k);
            if(writtenNumber > -1) {
                return writtenNumber;
            }

            k = k + inc;
            if(k >= length || k < 0) {
                throw new NoSuchElementException();
            }
            ch = inputLine.charAt(k);
        }
        return ch - '0';
    }

    private int getWrittenNumber(String line, int start) {
        String substring = line.substring(start);
        for (StringNumber value : StringNumber.values()) {
            String number = value.name().toLowerCase();
            if(substring.startsWith(number)) {
                return value.numberValue;
            }
        }
        return -1;
    }

    enum Side {
        LEFT, RIGHT
    }

    enum StringNumber {
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9);

        final int numberValue;

        StringNumber(int numberValue) {
            this.numberValue = numberValue;
        }
    }

}
