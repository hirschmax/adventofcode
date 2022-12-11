package de.ite.adventofcode.day11;

public enum Operator {
    ADD("+"), MULTIPLY("*");

    final String sign;

    Operator(String sign) {
        this.sign = sign;
    }

    static Operator getForSign(String sign) {
        for (Operator value : values()) {
            if(sign.contains(value.sign)) {
                return value;
            }
        }
        throw new IllegalArgumentException(sign);
    }
}
