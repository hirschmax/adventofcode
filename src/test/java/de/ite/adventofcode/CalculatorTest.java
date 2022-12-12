package de.ite.adventofcode;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public abstract class CalculatorTest<F, S, C extends Calculator<F,S>> {
    private final String firstInput;
    private final String secondInput;
    private final F firstResult;
    private final S secondResult;

    public CalculatorTest(String firstInput, String secondInput, F firstResult, S secondResult) {
        this.firstInput = firstInput;
        this.secondInput = secondInput;
        this.firstResult = firstResult;
        this.secondResult = secondResult;
    }

    public CalculatorTest(String input, F firstResult, S secondResult) {
        this(input, input, firstResult, secondResult);
    }

    @SneakyThrows
    @Test
    void solveFirstTask() {
        String input = InputUtils.readInputAsString(firstInput);
        C calculator = getObject(getTestClassName(), input);
        F actualResult = calculator.solveFirstTask();
        assertThat(actualResult).isEqualTo(firstResult);
    }

    @SneakyThrows
    @Test
    void solveSecondTask() {
        String input = InputUtils.readInputAsString(secondInput);
        C calculator = getObject(getTestClassName(), input);
        S actualResult = calculator.solveSecondTask();
        assertThat(actualResult).isEqualTo(secondResult);
    }

    @SneakyThrows
    public <T> T getObject(Class<T> type, String input){
        Object o = Class.forName(getTestClassName().getName()).getConstructor(String.class).newInstance(input);
        if (type.isInstance(o)){
            return type.cast(o);
        } else {
            return null;
        }
    }

    public abstract Class<C> getTestClassName();
}
