package de.ite.adventofcode.day12;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Coordinate {
    @Getter
    private final Position position;
    @Getter
    private final int height;

    private final List<Coordinate> neighbors = new ArrayList<>();

    @Getter
    @Setter
    private boolean isTarget = false;
    @Getter
    @Setter
    private int distance = Integer.MAX_VALUE;
    @Getter
    @Setter
    private boolean visited = false;
    @Getter
    @Setter
    private Direction direction = Direction.NONE;
    @Getter
    @Setter
    private Coordinate predecessor = null;

    public Coordinate(Position position, int height) {
        this.position = position;
        this.height = height;
    }

    public void addNeighbor(Coordinate adjacent) {
        neighbors.add(adjacent);
    }

    public List<Coordinate> getNeighbors() {
        return Collections.unmodifiableList(neighbors);
    }

}
