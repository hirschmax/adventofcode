package de.ite.adventofcode.day15;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.IntStream;

@Slf4j
public class BeaconSensorMap {

    private final Map<Position, Cell> cellMap = new HashMap<>();
    private final List<Sensor> sensors = new ArrayList<>();

    public BeaconSensorMap(List<String> inputLines) {
        init(inputLines);
    }

    public int countPositionsWhereBeaconCanNotBePresentForRow(int y) {
        int count = 0;
        for (int x : getHorizontalRange()) {
            Position position = new Position(x, y);
            for (Sensor sensor : sensors) {
                if(sensor.coversPosition(position) && (cellMap.get(position) == null || cellMap.get(position) instanceof Sensor)) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    public BigInteger getTuningFrequencyOfDistressBeacon(int searchMin, int searchMax) {
        Position position = findDistressBeacon(searchMin, searchMax);
        if(position == null) {
            return BigInteger.ZERO;
        }
        return BigInteger.valueOf(4_000_000L).multiply(BigInteger.valueOf(position.x())).add(BigInteger.valueOf(position.y()));
    }

    public Position findDistressBeacon(int searchMin, int searchMax) {
        Set<Integer> yIntersectionsForA = new HashSet<>();
        Set<Integer> yIntersectionsForB = new HashSet<>();

        /*
         * For each sensor, the covered area is determined by the four outer lines:
         *   1) y =  x + r + 1  + sy - sx (top left)
         *   2) y =  x - r - 1  + sy - sx (bottom right)
         *   3) y = -x + r + 1  + sy + sx (top right)
         *   4) y = -x - r - 1  + sy + sx (bottom left)
         * where r is the distance from sensor to closest beacon.
         *
         * For the lines with slope  1, we get:
         *   1) y = x + a  => a =   r + 1  + sy - sx
         *   2) y = x + a  => a = - r - 1  + sy - sx
         * For the lines with slope -1, we get:
         *   3) y = -x + b => b =   r + 1  + sy + sx
         *   4) y = -x + b => b = - r - 1  + sy + sx
         *
         * Since there is a single point missing, its position can be found one intersection point of those lines.
         */
        for (Sensor sensor : sensors) {
            yIntersectionsForA.add(sensor.position().y() - sensor.position().x() + sensor.getDistanceToClosestBeacon() + 1);
            yIntersectionsForA.add(sensor.position().y() - sensor.position().x() - sensor.getDistanceToClosestBeacon() - 1);
            yIntersectionsForB.add(sensor.position().x() + sensor.position().y() + sensor.getDistanceToClosestBeacon() + 1);
            yIntersectionsForB.add(sensor.position().x() + sensor.position().y() - sensor.getDistanceToClosestBeacon() - 1);
        }
        /*
         * So we collect all coefficients a and b, iterate over each combination of those and calculate the intersection:
         *   x + a = -x + b =>  x = (b - a) / 2
         *   y - a =  b - y =>  y = (b + a) / 2
         */
        for (int a : yIntersectionsForA) {
            for (int b : yIntersectionsForB) {
                Position intersectionPoint = new Position((b - a) / 2, (b + a) / 2);
                if (Math.min(intersectionPoint.x(), intersectionPoint.y()) >= searchMin && Math.max(intersectionPoint.x(), intersectionPoint.y()) <= searchMax) {
                    // check if none of the sensors covers the current intersection point
                    boolean notCovered = sensors.stream().allMatch(sensor -> intersectionPoint.getDistanceTo(sensor.position()) > sensor.getDistanceToClosestBeacon());
                    if (notCovered) {
                        return intersectionPoint;
                    }
                }
            }
        }

        return null;
    }

    public void print() {
        for (int y : getVerticalRange()) {
            StringBuilder sb = new StringBuilder();
            for (int x : getHorizontalRange()) {
                sb.append(findCell(x, y).map(Cell::getCode).orElse('.'));
            }
            log.info(sb.toString());
        }
    }

    private void init(List<String> inputLines) {
        for (String inputLine : inputLines) {
            String[] split = inputLine.split(":");
            Position sensorPosition = readPosition(split[0]);
            Position closestBeaconPosition = readPosition(split[1]);
            Beacon closestBeacon = new Beacon(closestBeaconPosition);
            cellMap.putIfAbsent(closestBeaconPosition, closestBeacon);
            Sensor sensor = new Sensor(sensorPosition, closestBeacon);
            cellMap.putIfAbsent(sensorPosition, sensor);
            sensors.add(sensor);
        }
    }

    private Position readPosition(String input) {
        String replace = input.substring(input.indexOf("=") + 1);
        String[] split = replace.split(", y=");
        int x = Integer.parseInt(split[0]);
        int y = Integer.parseInt(split[1]);
        return new Position(x, y);
    }

    private Optional<Cell> findCell(int x, int y) {
        return Optional.ofNullable(cellMap.get(new Position(x ,y)));
    }

    private int[] getHorizontalRange() {
        int min = sensors.stream().mapToInt(sensor -> sensor.position().x() - sensor.getDistanceToClosestBeacon()).min().orElse(0);
        int max = sensors.stream().mapToInt(sensor -> sensor.position().x() + sensor.getDistanceToClosestBeacon()).max().orElse(0);
        return IntStream.rangeClosed(min, max).toArray();
    }

    private int[] getVerticalRange() {
        int min = sensors.stream().mapToInt(sensor -> sensor.position().y() - sensor.getDistanceToClosestBeacon()).min().orElse(0);
        int max = sensors.stream().mapToInt(sensor -> sensor.position().y() + sensor.getDistanceToClosestBeacon()).max().orElse(0);
        return IntStream.rangeClosed(min, max).toArray();
    }
}
