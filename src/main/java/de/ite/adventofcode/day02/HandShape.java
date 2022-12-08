package de.ite.adventofcode.day02;

public enum HandShape {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    public final int score;
    HandShape(int score) {
        this.score = score;
    }

    public static HandShape getShapeForOpponentChoice(String opponentChoice) {
        char choice = opponentChoice.toUpperCase().trim().charAt(0);
        return switch (choice) {
            case 'A' -> ROCK;
            case 'B' -> PAPER;
            case 'C' -> SCISSORS;
            default -> throw new IllegalArgumentException(opponentChoice);
        };
    }

    public static HandShape getShapeForYourChoice(String yourChoice) {
        char choice = yourChoice.toUpperCase().trim().charAt(0);
        return switch (choice) {
            case 'X' -> ROCK;
            case 'Y' -> PAPER;
            case 'Z' -> SCISSORS;
            default -> throw new IllegalArgumentException(yourChoice);
        };
    }
}
