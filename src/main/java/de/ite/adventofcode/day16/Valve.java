package de.ite.adventofcode.day16;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Valve {

    static Map<String, Valve> valves = new HashMap<>();

    static Valve getValve(String name) {
        return valves.get(name);
    }

    private final String name;
    private final int flowRate;
    private final Map<String, Integer> neighborsDistanceMap = new HashMap<>();
    private boolean open;
    private int closedAtTimeRemaining;

    public Valve(String name, int flowRate) {
        this.name = name;
        this.flowRate = flowRate;
        valves.put(name, this);
    }

    public List<String> getNeighbors() {
        return neighborsDistanceMap.keySet().stream().toList();
    }

    public void addNeighbor(Valve valve, int distance) {
        neighborsDistanceMap.put(valve.getName(), distance);
    }

    public int getDistanceTo(Valve valve) {
        return neighborsDistanceMap.get(valve.getName());
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public int getClosedAtTimeRemaining() {
        return closedAtTimeRemaining;
    }

    public void setOpenedAtTimeRemaining(int closedAtTimeRemaining) {
        this.closedAtTimeRemaining = closedAtTimeRemaining;
    }

    public String getName() {
        return name;
    }

    public int getFlowRate() {
        return flowRate;
    }

    @Override
    public String toString() {
        return getName();
    }
}
