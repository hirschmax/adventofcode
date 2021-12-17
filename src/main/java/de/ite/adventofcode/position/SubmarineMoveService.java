package de.ite.adventofcode.position;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class SubmarineMoveService {


    class Submarine {
        @Getter
        private int depth;
        @Getter
        private int horizontal;

        public void moveSubmarine(Command command) {
            switch (command.direction) {
                case UP:
                    depth -= command.value;
                    break;
                case DOWN:
                    depth += command.value;
                    break;
                case FORWARD:
                    horizontal += command.value;
            }
        }
    }

    public int computeProductOfHorizontalAndVerticalPositionAfterMovesFor(List<String> commands) {
        Submarine submarine = new Submarine();

        commands.stream()
                .map(this::parseCommand)
                .forEach(submarine::moveSubmarine);

        return submarine.getDepth() * submarine.getHorizontal();
    }

    private Command parseCommand(String command) {
        String[] segments = command.split(" ");
        int value = Integer.parseInt(segments[1]);
        return new Command(parseDirection(segments[0]), value);
    }

    private Direction parseDirection(String command) {
        String toCompare = command.toLowerCase();
        if ("forward".equals(toCompare)) {
            return Direction.FORWARD;
        } else if ("up".equals(toCompare)) {
            return Direction.UP;
        } else {
            return Direction.DOWN;
        }
    }

    @AllArgsConstructor
    class Command {
        Direction direction;
        int value;
    }

    enum Direction {
        FORWARD,
        UP,
        DOWN
    }
}
