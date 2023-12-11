package de.ite.adventofcode.day03;

import de.ite.adventofcode.Calculator;
import de.ite.adventofcode.InputUtils;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;


@Slf4j
public class EngineSchematicCalculator extends Calculator<Integer, BigInteger> {

    public static void main(String[] args) {
        String input = InputUtils.readInputAsString("day03.txt");
        EngineSchematicCalculator calculator = new EngineSchematicCalculator(input);
        log.info("Result for day 3 task 1: {}", calculator.solveFirstTask());
        log.info("Result for day 3 task 2: {}", calculator.solveSecondTask());
    }

    public EngineSchematicCalculator(String input) {
        super(input.lines().toList());
    }

    @Override
    public Integer solveFirstTask() {
        Engine engine = new Engine(inputLines);
        return engine.getSchematicNumbers()
                .stream()
                .filter(schematicNumber -> schematicNumber.isAdjacentToSymbol(engine.getSchematic()))
                .mapToInt(Engine.SchematicNumber::numberValue)
                .sum();
    }

    @Override
    public BigInteger solveSecondTask() {
        Engine engine = new Engine(inputLines);
        return engine.getStars()
                .stream()
                .map(star -> star.getGearRatio(engine.getPositionToSchematicNumberMap()))
                .reduce(BigInteger.ZERO, BigInteger::add);
    }


}
