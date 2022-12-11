package de.ite.adventofcode.day11;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class MonkeyBuilder {
    private int id;
    private List<Item> startItems = new ArrayList<>();
    private Function<Item, Integer> decisionFunction;
    private Function<Item, BigInteger> operationFunction;
    private UnaryOperator<BigInteger> reliefAfterInspectionFunction;

    MonkeyBuilder withId(int id) {
        this.id = id;
        return this;
    }

    MonkeyBuilder withStartItems(List<Item> startItems) {
        this.startItems = startItems;
        return this;
    }

    MonkeyBuilder withDecisionFunction(Function<Item, Integer> decisionFunction) {
        this.decisionFunction = decisionFunction;
        return this;
    }

    MonkeyBuilder withOperationFunction(Function<Item, BigInteger> operationFunction) {
        this.operationFunction = operationFunction;
        return this;
    }

    MonkeyBuilder withReliefAfterInspectionFunction(UnaryOperator<BigInteger> reliefAfterInspectionFunction) {
        this.reliefAfterInspectionFunction = reliefAfterInspectionFunction;
        return this;
    }

    Monkey build() {
        return new Monkey(id, startItems, operationFunction, decisionFunction, reliefAfterInspectionFunction);
    }
}
