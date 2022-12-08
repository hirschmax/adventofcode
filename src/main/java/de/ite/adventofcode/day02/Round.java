package de.ite.adventofcode.day02;

import static de.ite.adventofcode.day02.Result.*;

public class Round {

    private final HandShape yourChoice;
    private final Result result;

    public Round(HandShape opponentsChoice, HandShape yourChoice) {
        this.yourChoice = yourChoice;
        this.result = computeResult(opponentsChoice, yourChoice);
    }

    public Round(HandShape opponentsChoice, Result expectedResult) {
        this.result = expectedResult;
        this.yourChoice = computeYourChoiceToAchieveExpectedResult(opponentsChoice, expectedResult);
    }

    public int calculateScore() {
        int selectedShapeScore = yourChoice.score;
        int roundOutcomeScore = result.points;
        return selectedShapeScore + roundOutcomeScore;
    }

    private HandShape computeYourChoiceToAchieveExpectedResult(HandShape opponents, Result expectedResult) {
        if(expectedResult == DRAW) {
            return opponents;
        }
        if(expectedResult == WIN) {
            if(opponents == HandShape.ROCK) {
                return HandShape.PAPER;
            }
            if(opponents == HandShape.PAPER) {
                return HandShape.SCISSORS;
            }
            if(opponents == HandShape.SCISSORS) {
                return HandShape.ROCK;
            }
        }
        if(expectedResult == LOSE) {
            if(opponents == HandShape.ROCK) {
                return HandShape.SCISSORS;
            }
            if(opponents == HandShape.PAPER) {
                return HandShape.ROCK;
            }
            if(opponents == HandShape.SCISSORS) {
                return HandShape.PAPER;
            }
        }
        return null;
    }

    private Result computeResult(HandShape opponents, HandShape yours) {
        if(yours == opponents) {
            return DRAW;
        }
        if(yours == HandShape.ROCK) {
            return opponents == HandShape.SCISSORS ? WIN : LOSE;
        }
        if(yours == HandShape.SCISSORS) {
            return opponents == HandShape.PAPER ? WIN : LOSE;
        }
        if(yours == HandShape.PAPER) {
            return opponents == HandShape.ROCK ? WIN : LOSE;
        }
        return null;
    }


}
