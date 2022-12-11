package de.ite.adventofcode.day05;

import de.ite.adventofcode.Calculator;
import de.ite.adventofcode.InputUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class SupplyStackCalculator extends Calculator<String, String> {

    public static void main(String[] args) {
        List<String> inputLines = InputUtils.readInput("day05_supply_stacks.txt");
        SupplyStackCalculator supplyStackCalculator = new SupplyStackCalculator(inputLines);
        log.info("After the rearrangement procedure completes, what crate ends up on top of each stack?");
        log.info("Task 01: {}", supplyStackCalculator.solveFirstTask());
        log.info("Task 02: {}", supplyStackCalculator.solveSecondTask());
    }

    private Stack<String> supplyStackLines;
    private List<String> moveActionLines;

    protected SupplyStackCalculator(List<String> inputLines) {
        super(inputLines);
    }

    @Override
    public String solveFirstTask() {
        initFirstTask(inputLines);
        Map<Integer, Stack<Character>> stacks = readStacks(supplyStackLines);
        List<Move> moves = readMoves(moveActionLines);

        for (Move move : moves) {
            for(int i = 0; i < move.amount(); i++) {
                Character item = stacks.get(move.from()).pop();
                stacks.get(move.to()).push(item);
            }
        }

        return getResultString(stacks);
    }

    @Override
    public String solveSecondTask() {
        initFirstTask(inputLines);
        Map<Integer, Stack<Character>> stacks = readStacks(supplyStackLines);
        List<Move> moves = readMoves(moveActionLines);

        for (Move move : moves) {
            Stack<Character> stack = new Stack<>();
            for(int i = 0; i < move.amount(); i++) {
                Character item = stacks.get(move.from()).pop();
                stack.push(item);
            }
            while(!stack.isEmpty()) {
                stacks.get(move.to()).push(stack.pop());
            }
        }

        return getResultString(stacks);
    }

    private void initFirstTask(List<String> inputLines) {
        supplyStackLines = new Stack<>();
        moveActionLines = new ArrayList<>();

        for (String inputLine : inputLines) {
            if(inputLine.isBlank()) {
                continue;
            }
            if(inputLine.startsWith("move")) {
                moveActionLines.add(inputLine);
            } else {
                supplyStackLines.push(inputLine);
            }
        }
    }

    private List<Move> readMoves(List<String> moveActionLines) {
        // move 1 from 2 to 1
        List<Move> moves = new ArrayList<>();
        for (String moveActionLine : moveActionLines) {
            String[] split = moveActionLine.replaceAll("[a-z]", "").trim().split(" {2}");
            int amount = Integer.parseInt(split[0]);
            int from = Integer.parseInt(split[1]);
            int to = Integer.parseInt(split[2]);
            moves.add(new Move(amount, from, to));
        }
        return moves;
    }

    private Map<Integer, Stack<Character>> readStacks(Stack<String> supplyStackLines) {
        Map<Integer, Stack<Character>> stacks = new HashMap<>();
        Map<Integer, Integer> columnToStackNumberMap = new HashMap<>();
        // 1   2   3
        String stackNumbers = supplyStackLines.pop();
        for(int i = 1; i < stackNumbers.length() - 1; i+=4) {
            int stackNumber = Integer.parseInt(stackNumbers.substring(i, i + 1));
            stacks.putIfAbsent(stackNumber, new Stack<>());
            columnToStackNumberMap.put(i, stackNumber);
        }

        while (!supplyStackLines.isEmpty()) {
            String cratesLine = supplyStackLines.pop();
            for (Map.Entry<Integer, Integer> integerIntegerEntry : columnToStackNumberMap.entrySet()) {
                int column = integerIntegerEntry.getKey();
                int stackNumber = integerIntegerEntry.getValue();
                char character = cratesLine.charAt(column);
                if(character >= 'A' && character <= 'Z') {
                    stacks.get(stackNumber).push(character);
                }
            }
        }
        return stacks;
    }

    private String getResultString(Map<Integer, Stack<Character>> stacks) {
        char[] result = new char[stacks.keySet().size()];
        for (Map.Entry<Integer, Stack<Character>> integerStackEntry : stacks.entrySet()) {
            Integer stackIndex = integerStackEntry.getKey();
            Stack<Character> stack = integerStackEntry.getValue();
            result[stackIndex - 1] = stack.peek();
        }

        return new String(result);
    }

}
