package de.ite.adventofcode.day15;


public class Sensor implements Cell {
    private final Position position;

    private final int distanceToClosestBeacon;

    public Sensor(Position position, Beacon closestBeacon) {
        this.position = position;
        this.distanceToClosestBeacon = position.getDistanceTo(closestBeacon.position());
    }

    public boolean coversPosition(Position otherPosition) {
        return position.getDistanceTo(otherPosition) <= distanceToClosestBeacon;
    }

    public int getDistanceToClosestBeacon() {
        return distanceToClosestBeacon;
    }

    @Override
    public Position position() {
        return position;
    }

    @Override
    public char getCode() {
        return 'S';
    }
}
