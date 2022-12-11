package de.ite.adventofcode.day11;

import java.math.BigInteger;

public class Item {
    private BigInteger worryLevel;

    public Item(BigInteger worryLevel) {
        this.worryLevel = worryLevel;
    }

    public void setWorryLevel(BigInteger worryLevel) {
        this.worryLevel = worryLevel;
    }

    public BigInteger getWorryLevel() {
        return worryLevel;
    }

    public boolean isWorryLevelDivisibleBy(int divisor) {
        return worryLevel.mod(BigInteger.valueOf(divisor)).equals(BigInteger.ZERO);
    }

}
