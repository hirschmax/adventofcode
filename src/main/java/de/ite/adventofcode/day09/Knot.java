package de.ite.adventofcode.day09;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Knot {
    private final Set<Position> positionsVisited = new HashSet<>();
    private int x;
    private int y;

    private final Knot successor;

    public Knot(Knot successor) {
        this.successor = successor;
        x = 0;
        y = 0;
        addCurrentPositionToVisited();
    }

    public Set<Position> getPositionsVisited() {
        return positionsVisited;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move(List<Motion> motion) {
        motion.forEach(this::move);
        addCurrentPositionToVisited();
    }

    private void move(Motion motion) {
        switch (motion) {
            case UP -> y++;
            case RIGHT -> x++;
            case DOWN -> y--;
            case LEFT -> x--;
        }
    }

    private void addCurrentPositionToVisited() {
        positionsVisited.add(new Position(x, y));
    }

    public Knot getSuccessor() {
        return successor;
    }

    public boolean hasSuccessor() {
        return successor != null;
    }
}
