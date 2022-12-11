package de.ite.adventofcode.day11;

import de.ite.adventofcode.Calculator;
import de.ite.adventofcode.InputUtils;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;

@Slf4j
public class MonkeyInTheMiddleCalculator extends Calculator<BigInteger, BigInteger> {

    public static void main(String[] args) {
        List<String> inputLines = InputUtils.readInput("day11_notes.txt");
        MonkeyInTheMiddleCalculator startOfPackageMarkerCalculator = new MonkeyInTheMiddleCalculator(inputLines);
        log.info("Figure out which monkeys to chase by counting how many items they inspect over 20 rounds. What is the level of monkey business after 20 rounds of stuff-slinging simian shenanigans?");
        log.info("Task 01: {}", startOfPackageMarkerCalculator.solveFirstTask());
        log.info("Worry levels are no longer divided by three after each item is inspected; you'll need to find another way to keep your worry levels manageable. Starting again from the initial state in your puzzle input, what is the level of monkey business after 10000 rounds?");
        log.info("Task 02: {}", startOfPackageMarkerCalculator.solveSecondTask());
    }

    private static final String OLD = "old";
    private static final EnumMap<Operator, BiFunction<BigInteger, BigInteger, BigInteger>> operatorFunctionMap = new EnumMap<>(Map.of(
            Operator.ADD, BigInteger::add,
            Operator.MULTIPLY, BigInteger::multiply
    ));

    public MonkeyInTheMiddleCalculator(List<String> inputLines) {
        super(inputLines);
    }

    @Override
    public BigInteger solveFirstTask() {
        int rounds = 20;
        BigInteger maxWorryLevel = calculateMaxWorryLevel();
        UnaryOperator<BigInteger> reliefAfterInspectionFunction = i -> i.divide(BigInteger.valueOf(3L).mod(maxWorryLevel));
        List<Monkey> monkeyList = initMonkeys(reliefAfterInspectionFunction);
        return calculateLevelOfMonkeyBusiness(rounds, monkeyList);
    }

    @Override
    public BigInteger solveSecondTask() {
        int rounds = 10_000;
        BigInteger maxWorryLevel = calculateMaxWorryLevel();
        UnaryOperator<BigInteger> reliefAfterInspectionFunction = i -> i.mod(maxWorryLevel);
        List<Monkey> monkeyList = initMonkeys(reliefAfterInspectionFunction);
        return calculateLevelOfMonkeyBusiness(rounds, monkeyList);
    }

    private BigInteger calculateLevelOfMonkeyBusiness(int rounds, List<Monkey> monkeyList) {
        Map<Integer, Monkey> idToMonkeyMap = new HashMap<>();
        monkeyList.forEach(monkey -> idToMonkeyMap.put(monkey.id, monkey));

        for(int round = 1; round <= rounds; round++) {
            for (Monkey monkey : monkeyList) {
                monkey.inspectAndThrowItems(idToMonkeyMap);
            }
        }

        List<Monkey> busiestMonkeys = monkeyList.stream()
                .sorted(Comparator.comparing(Monkey::getInspectedItems).reversed())
                .toList()
                .subList(0, 2);

        return busiestMonkeys.stream().map(Monkey::getInspectedItems).reduce(BigInteger.ONE, BigInteger::multiply);
    }

    private BigInteger calculateMaxWorryLevel() {
        List<BigInteger> divisors = new ArrayList<>();
        int nMonkeys = (inputLines.size() + 1) / 7;
        for(int id = 0; id < nMonkeys; id++) {
            int divisor = Integer.parseInt(inputLines.get(3 + id * 7).replace("  Test: divisible by ", ""));
            divisors.add(BigInteger.valueOf(divisor));
        }
        return divisors.stream().reduce(BigInteger.ONE, BigInteger::multiply);
    }

    private List<Monkey> initMonkeys(UnaryOperator<BigInteger> reliefAfterInspectionFunction) {
        List<Monkey> monkeyList = new ArrayList<>();

        int nMonkeys = (inputLines.size() + 1) / 7;
        for(int id = 0; id < nMonkeys; id++) {
            Monkey monkey = new MonkeyBuilder()
                    .withId(id)
                    .withStartItems(readStartingItemsForMonkeyWithId(id))
                    .withOperationFunction(readOperationFunctionForMonkeyWithId(id))
                    .withDecisionFunction(readDecisionFunctionForMonkeyWithId(id))
                    .withReliefAfterInspectionFunction(reliefAfterInspectionFunction)
                    .build();
            monkeyList.add(monkey);
        }

        return monkeyList;
    }

    private Function<Item, Integer> readDecisionFunctionForMonkeyWithId(int id) {
        int divisor = Integer.parseInt(inputLines.get(3 + id * 7).replace("  Test: divisible by ", ""));
        int monkeyIdForDivisible = Integer.parseInt(inputLines.get(4 + id * 7).replace("    If true: throw to monkey ", ""));
        int monkeyIdForNotDivisible = Integer.parseInt(inputLines.get(5 + id * 7).replace("    If false: throw to monkey ", ""));
        return item -> item.isWorryLevelDivisibleBy(divisor) ? monkeyIdForDivisible : monkeyIdForNotDivisible;
    }

    private Function<Item, BigInteger> readOperationFunctionForMonkeyWithId(int id) {
        String operationString = inputLines.get(2 + id * 7).replace("  Operation: new = ", "");
        Operator operator = Operator.getForSign(operationString);

        String[] split = operationString.replace(operator.sign, "#").split("#");
        String first = split[0].trim();
        String second = split[1].trim();

        return item -> {
            BigInteger firstOperand = first.equals(OLD) ? item.getWorryLevel() : new BigInteger(first);
            BigInteger secondOperand = second.equals(OLD) ? item.getWorryLevel() : new BigInteger(second);
            return operatorFunctionMap.getOrDefault(operator, (a, b) -> BigInteger.ZERO).apply(firstOperand, secondOperand);
        };
    }

    private List<Item> readStartingItemsForMonkeyWithId(int id) {
        return Arrays.stream(inputLines.get(1 + id * 7)
                        .replace("  Starting items: ", "")
                        .split(", "))
                .map(BigInteger::new)
                .map(Item::new)
                .toList();
    }

}
