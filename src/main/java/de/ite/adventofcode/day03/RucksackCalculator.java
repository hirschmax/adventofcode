package de.ite.adventofcode.day03;

import de.ite.adventofcode.Calculator;
import de.ite.adventofcode.InputUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RucksackCalculator extends Calculator<Integer> {

    public static void main(String[] args) {
        List<String> inputLines = InputUtils.readInput("day03_rucksack_content.txt");
        RucksackCalculator rucksackCalculator = new RucksackCalculator(inputLines);
        log.info("What is the sum of the priorities of those item types?");
        log.info("Task 01: {}", rucksackCalculator.solveFirstTask());
        log.info("Task 02: {}", rucksackCalculator.solveSecondTask());
    }


    public RucksackCalculator(List<String> inputLines) {
        super(inputLines);
    }

    @Override
    public Integer solveFirstTask() {
        return getSumOfPrioritiesForItemsThatArePutToBothCompartments();
    }

    @Override
    public Integer solveSecondTask() {
        return getSumOfPrioritiesThreeElvesGroups();
    }

    private int getSumOfPrioritiesForItemsThatArePutToBothCompartments() {
        return inputLines.stream().map(Rucksack::new).mapToInt(Rucksack::getPriorityOfItemThatAppearsInBothCompartments).sum();
    }

    private int getSumOfPrioritiesThreeElvesGroups() {
        List<ThreeElvesGroupRucksacks> groups = new ArrayList<>();
        for(int i = 0; i < inputLines.size() - 2; i += 3) {
            groups.add(new ThreeElvesGroupRucksacks(inputLines.subList(i, i + 3)));
        }
        return groups.stream().mapToInt(ThreeElvesGroupRucksacks::getPriorityOfGroupBadge).sum();
    }
}
