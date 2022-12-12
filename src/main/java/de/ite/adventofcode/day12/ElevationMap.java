package de.ite.adventofcode.day12;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class ElevationMap {
    private final List<Coordinate> coordinateList;
    private final Coordinate[][] coordinates;
    private final Coordinate target;

    public ElevationMap(List<Coordinate> coordinateList, Coordinate[][] coordinates, Coordinate target) {
        this.coordinateList = coordinateList;
        this.coordinates = coordinates;
        this.target = target;
    }

    public int calculateFewestStepsRequiredForStartingAtAnySquareWithElevationZero() {
        List<Coordinate> startingCoordinates = coordinateList.stream().filter(coordinate -> coordinate.getHeight() == 0).toList();
        Map<Integer, List<Position>> resultMap = new HashMap<>();

        for (Coordinate coordinate : startingCoordinates) {
            int result = calculateFewestStepsRequiredForStartAt(coordinate.getPosition());
            resultMap.putIfAbsent(result, new ArrayList<>());
            resultMap.get(result).add(coordinate.getPosition());
        }

        int min = resultMap.keySet().stream().mapToInt(Integer::intValue).min().orElse(Integer.MAX_VALUE);
        String bestStartingPositions = resultMap.get(min).stream().map(position -> String.format("(%s,%s)", position.x(), position.y())).collect(Collectors.joining(", "));
        log.info("Best starting position: [{}]", bestStartingPositions);

        calculateFewestStepsRequiredForStartAt(resultMap.get(min).get(0));
        return min;
    }

    public int calculateFewestStepsRequiredForStartAt(Position startingPosition) {
        Coordinate start = coordinates[startingPosition.x()][startingPosition.y()];
        initDijkstra(start);

        PriorityQueue<Coordinate> queue = new PriorityQueue<>(Comparator.comparing(Coordinate::getDistance));
        queue.add(start);

        while(!queue.isEmpty()) {
            Coordinate currentNode = queue.poll();
            currentNode.setVisited(true);
            for (Coordinate neighbor : currentNode.getNeighbors()) {
                if(!neighbor.isVisited()) {
                    int distance = currentNode.getDistance() + 1;
                    if(distance < neighbor.getDistance()) {
                        neighbor.setDistance(distance);
                        neighbor.setPredecessor(currentNode);
                        queue.offer(neighbor);
                    }
                }
            }
        }

        Coordinate currentNode = target;
        while(currentNode.getPredecessor() != null) {
            Coordinate predecessor = currentNode.getPredecessor();
            predecessor.setDirection(getDirection(predecessor, currentNode));
            currentNode = predecessor;
        }

        return target.getDistance();
    }

    public void print() {
        for(int y = 0; y < coordinates[0].length; y++) {
            StringBuilder line = new StringBuilder();
            for(Coordinate[] currentColumnAt : coordinates) {
                Coordinate coordinate = currentColumnAt[y];
                if (coordinate.isTarget()) {
                    line.append("E");
                } else {
                    line.append(coordinate.getDirection().sign);
                }
            }
            log.info(line.toString());
        }
    }

    private void initDijkstra(Coordinate start) {
        for (Coordinate coordinate : coordinateList) {
            coordinate.setVisited(false);
            coordinate.setDistance(Integer.MAX_VALUE);
            coordinate.setPredecessor(null);
            coordinate.setDirection(Direction.NONE);
        }
        start.setDistance(0);
    }

    private Direction getDirection(Coordinate currentNode, Coordinate neighbor) {
        if(currentNode.getPosition().x() < neighbor.getPosition().x()) {
            return Direction.RIGHT;
        }
        if(currentNode.getPosition().x() > neighbor.getPosition().x()) {
            return Direction.LEFT;
        }
        if(currentNode.getPosition().y() < neighbor.getPosition().y()) {
            return Direction.DOWN;
        }
        if(currentNode.getPosition().y() > neighbor.getPosition().y()) {
            return Direction.UP;
        }
        return Direction.NONE;
    }


}
