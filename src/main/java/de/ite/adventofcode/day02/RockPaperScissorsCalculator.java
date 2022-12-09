package de.ite.adventofcode.day02;

import de.ite.adventofcode.Calculator;
import de.ite.adventofcode.InputUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RockPaperScissorsCalculator extends Calculator<Integer> {

    public static void main(String[] args) {
        List<String> inputLines = InputUtils.readInput("day02_encrypted_strategy_guide.txt");
        RockPaperScissorsCalculator rockPaperScissorsCalculator = new RockPaperScissorsCalculator(inputLines);
        log.info("What would your total score be if everything goes exactly according to your strategy guide?");
        log.info("Assuming input includes both hand shapes: {}", rockPaperScissorsCalculator.calculateForBothHandShapes());
        log.info("Real Strategy guide: {}", rockPaperScissorsCalculator.calculateForStrategyGuide());
    }


    public RockPaperScissorsCalculator(List<String> inputLines) {
        super(inputLines);
    }

    @Override
    public Integer solveFirstTask() {
        return calculateForBothHandShapes();
    }

    @Override
    public Integer solveSecondTask() {
        return calculateForStrategyGuide();
    }

    private int calculateForBothHandShapes() {
        List<Round> bothHandShapesRounds = new ArrayList<>();
        for (String inputLine : inputLines) {
            String[] split = inputLine.split(" ");
            HandShape opponents = HandShape.getShapeForOpponentChoice(split[0]);
            HandShape yours = HandShape.getShapeForYourChoice(split[1]);
            bothHandShapesRounds.add(new Round(opponents, yours));
        }
        return bothHandShapesRounds.stream().map(Round::calculateScore).mapToInt(Integer::intValue).sum();
    }

    private int calculateForStrategyGuide() {
        List<Round> strategyGuideRounds = new ArrayList<>();
        for (String inputLine : inputLines) {
            String[] split = inputLine.split(" ");
            HandShape opponents = HandShape.getShapeForOpponentChoice(split[0]);
            Result expectedResult = Result.getForSign(split[1].charAt(0));
            strategyGuideRounds.add(new Round(opponents, expectedResult));
        }
        return strategyGuideRounds.stream().map(Round::calculateScore).mapToInt(Integer::intValue).sum();
    }

}
