package de.ite.adventofcode.day11;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Monkey {
    public final int id;
    private final List<Item> items;
    private final Function<Item, BigInteger> operationFunction;
    private final Function<Item, Integer> decisionFunction;
    private final UnaryOperator<BigInteger> reliefAfterInspectionFunction;

    private BigInteger inspectedItems = BigInteger.ZERO;

    Monkey(int id, List<Item> startItems, Function<Item, BigInteger> operationFunction, Function<Item, Integer> decisionFunction, UnaryOperator<BigInteger> reliefAfterInspectionFunction) {
        this.id = id;
        this.items = new ArrayList<>(startItems);
        this.operationFunction = operationFunction;
        this.decisionFunction = decisionFunction;
        this.reliefAfterInspectionFunction = reliefAfterInspectionFunction;
    }

    public void inspectAndThrowItems(Map<Integer, Monkey> idToMonkeyMap) {
        for(Item item : new ArrayList<>(items)) {
            inspectItem(item);

            int targetId = decisionFunction.apply(item);
            Monkey targetMonkey = idToMonkeyMap.get(targetId);
            throwItemToMonkey(item, targetMonkey);
        }
    }

    private void inspectItem(Item item) {
        inspectedItems = inspectedItems.add(BigInteger.ONE);
        BigInteger worryLevelAfterInspection = operationFunction.apply(item);
        BigInteger worryLevelAfterRelief = reliefAfterInspectionFunction.apply(worryLevelAfterInspection);
        item.setWorryLevel(worryLevelAfterRelief);
    }

    private void throwItemToMonkey(Item item, Monkey targetMonkey) {
        targetMonkey.items.add(item);
        items.remove(item);
    }

    public BigInteger getInspectedItems() {
        return inspectedItems;
    }
}
