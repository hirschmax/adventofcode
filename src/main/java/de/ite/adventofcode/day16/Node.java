package de.ite.adventofcode.day16;

public class Node<T> {
    private final T content;
    private boolean visited = false;
    private int distance = Integer.MAX_VALUE;
    public Node(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return content.toString() + " " + distance;
    }
}