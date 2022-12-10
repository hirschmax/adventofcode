package de.ite.adventofcode.day08;

import de.ite.adventofcode.Calculator;
import de.ite.adventofcode.InputUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class TreeTopTreeHouseCalculator extends Calculator<Integer> {

    public static void main(String[] args) {
        List<String> inputLines = InputUtils.readInput("day08_tree_height_map.txt");
        TreeTopTreeHouseCalculator treeTopTreeHouseCalculator = new TreeTopTreeHouseCalculator(inputLines);
        log.info("Consider your map; how many trees are visible from outside the grid?");
        log.info("Task 01: {}", treeTopTreeHouseCalculator.solveFirstTask());
        log.info("Consider each tree on your map. What is the highest scenic score possible for any tree?");
        log.info("Task 02: {}", treeTopTreeHouseCalculator.solveSecondTask());
    }
    public TreeTopTreeHouseCalculator(List<String> inputLines) {
        super(inputLines);
    }

    @Override
    public Integer solveFirstTask() {
        Forest forest = new Forest(inputLines);
        return forest.getVisibleTrees().size();
    }

    @Override
    public Integer solveSecondTask() {
        Forest forest = new Forest(inputLines);
        return forest.getHighestScenicScore();
    }

}
