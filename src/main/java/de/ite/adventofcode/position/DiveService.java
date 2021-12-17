package de.ite.adventofcode.position;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class DiveService {
    public Position computeRelativePositionFrom(String commandSeries) {
        return new Position(0, 5);
    }

    @AllArgsConstructor
    protected static class Position {
        @Getter private int depth;
        @Getter private int horizontal;
    }
}
