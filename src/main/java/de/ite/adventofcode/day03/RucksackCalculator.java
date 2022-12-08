package de.ite.adventofcode.day03;

import de.ite.adventofcode.InputUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RucksackCalculator {

    public static void main(String[] args) {
        List<String> inputLines = InputUtils.readInput("day03_rucksack_content.txt");
        RucksackCalculator rucksackCalculator = new RucksackCalculator(inputLines);
        log.info("What is the sum of the priorities of those item types?");
        log.info("Task 01: {}", rucksackCalculator.getSumOfPrioritiesForItemsThatArePutToBothCompartments());
        log.info("Task 02: {}", rucksackCalculator.getSumOfPrioritiesThreeElvesGroups());
    }

    private final List<String> inputLines;

    public RucksackCalculator(List<String> inputLines) {
        this.inputLines = inputLines;
    }

    public int getSumOfPrioritiesForItemsThatArePutToBothCompartments() {
        return inputLines.stream().map(Rucksack::new).mapToInt(Rucksack::getPriorityOfItemThatAppearsInBothCompartments).sum();
    }

    public int getSumOfPrioritiesThreeElvesGroups() {
        List<ThreeElvesGroupRucksacks> groups = new ArrayList<>();
        for(int i = 0; i < inputLines.size() - 2; i += 3) {
            groups.add(new ThreeElvesGroupRucksacks(inputLines.subList(i, i + 3)));
        }
        return groups.stream().mapToInt(ThreeElvesGroupRucksacks::getPriorityOfGroupBadge).sum();
    }
}
