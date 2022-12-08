package de.ite.adventofcode.day02;

public enum Result {
    WIN(6, 'Z'),
    DRAW(3, 'Y'),
    LOSE(0, 'X');

    public final int points;
    public final char sign;

    Result(int points, char sign) {
        this.points = points;
        this.sign = sign;
    }

    public static Result getForSign(char sign) {
        for (Result value : values()) {
            if(value.sign == sign) {
                return value;
            }
        }
        throw new IllegalArgumentException(Character.toString(sign));
    }

}
