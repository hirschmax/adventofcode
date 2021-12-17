package de.ite.adventofcode.position;


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
        return -1;
    }

}
