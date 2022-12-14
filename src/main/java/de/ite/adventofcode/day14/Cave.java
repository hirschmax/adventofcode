package de.ite.adventofcode.day14;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.IntStream;

@Slf4j
public class Cave {
    private final Map<Position, Cell> cellMap = new HashMap<>();
    private Cell fallingSandCell;
    private int sandUnitsAtRest = 0;
    private boolean withFloor = false;
    private int floorY;

    private final Position startingPosition = new Position(500, 0);
    public Cave(List<String> inputLines) {
        this(inputLines, false);
    }
    public Cave(List<String> inputLines, boolean withFloor) {
        this.withFloor = withFloor;
        fallingSandCell = new Cell(startingPosition, Cell.Type.SAND);
        cellMap.put(fallingSandCell.getPosition(), fallingSandCell);
        readCells(inputLines);
    }

    public Optional<Cell> findCell(int x, int y) {
        return findCell(new Position(x, y));
    }

    public Optional<Cell> findCell(Position position) {
        Cell cell = cellMap.get(position);
        if(withFloor && cell == null) {
            if(position.y() < getFloorY()) {
                Cell newAirCell = new Cell(position, Cell.Type.AIR);
                cellMap.put(position, newAirCell);
                return Optional.of(newAirCell);
            } else {
                Cell floorCell = new Cell(position, Cell.Type.ROCK);
                cellMap.put(position, floorCell);
                return Optional.of(floorCell);
            }
        }

        return Optional.ofNullable(cell);
    }

    public boolean simulatePouringSand() {
        Position currentPosition = fallingSandCell.getPosition();
        Optional<Cell> cellBelow = findCell(currentPosition.x(), currentPosition.y() + 1);
        Optional<Cell> cellLeft = findCell(currentPosition.x()-1, currentPosition.y() + 1);
        Optional<Cell> cellRight = findCell(currentPosition.x()+1, currentPosition.y() + 1);

        if(cellBelow.isPresent() && cellBelow.get().getType() == Cell.Type.AIR) {
            moveToCell(cellBelow.get());
            return true;
        } else if(cellLeft.isPresent() && cellLeft.get().getType() == Cell.Type.AIR) {
            moveToCell(cellLeft.get());
            return true;
        } else if(cellRight.isPresent() && cellRight.get().getType() == Cell.Type.AIR) {
            moveToCell(cellRight.get());
            return true;
        } else if(cellBelow.isPresent() && cellLeft.isPresent() && cellRight.isPresent()) {
            sandUnitsAtRest += 1;
            fallingSandCell.setType(Cell.Type.SAND_AT_REST);
            fallingSandCell = findCell(startingPosition).orElseThrow();
            if(fallingSandCell.getType() == Cell.Type.SAND_AT_REST) {
                return false;
            }
            fallingSandCell.setType(Cell.Type.SAND);

            return true;
        }

        return false;
    }

    public int getSandUnitsAtRest() {
        return sandUnitsAtRest;
    }
    public void print() {
        for (int y : getVerticalRange()) {
            StringBuilder sb = new StringBuilder();
            for (int x : getHorizontalRange()) {
                sb.append(findCell(x, y).map(Cell::getType).map(type -> type.code).orElse('.'));
            }
            log.info(sb.toString());
        }
    }

    private void moveToCell(Cell nextCell) {
        fallingSandCell.setType(Cell.Type.AIR);
        nextCell.setType(Cell.Type.SAND);
        fallingSandCell = nextCell;
    }

    public int[] getHorizontalRange() {
        int min = cellMap.keySet().stream().mapToInt(Position::x).min().orElse(0);
        int max = cellMap.keySet().stream().mapToInt(Position::x).max().orElse(0);
        return IntStream.rangeClosed(min, max).toArray();
    }

    public int[] getVerticalRange() {
        int min = cellMap.keySet().stream().mapToInt(Position::y).min().orElse(0);
        int max = cellMap.keySet().stream().mapToInt(Position::y).max().orElse(0);
        return IntStream.rangeClosed(min, max).toArray();
    }

    private void readCells(List<String> inputLines) {
        for(String line : inputLines) {
            String[] points = line.split(" -> ");
            for(int i = 0; i+1 < points.length; i++) {
                int xFrom = Integer.parseInt(points[i].split(",")[0]);
                int yFrom = Integer.parseInt(points[i].split(",")[1]);
                int xTo = Integer.parseInt(points[i+1].split(",")[0]);
                int yTo = Integer.parseInt(points[i+1].split(",")[1]);
                if(xFrom == xTo) {
                    addVerticalLine(xFrom, yFrom, yTo);
                }
                if(yFrom == yTo) {
                    addHorizontalLine(xFrom, xTo, yFrom);
                }
            }
        }
        int[] verticalRange = getVerticalRange();
        int[] horizontalRange = getHorizontalRange();

        for (int y : verticalRange) {
            for (int x : horizontalRange) {
                Position position = new Position(x, y);
                cellMap.putIfAbsent(position, new Cell(position, Cell.Type.AIR));
            }
        }

        floorY = Arrays.stream(getVerticalRange()).max().orElse(0) + (withFloor ? 2 : 0);
    }

    private void addHorizontalLine(int xFrom, int xTo, int y) {
        for(int index = Math.min(xFrom, xTo); index <= Math.max(xFrom, xTo); index++) {
            Position position = new Position(index, y);
            cellMap.computeIfAbsent(position, p -> new Cell(p, Cell.Type.ROCK));
        }
    }

    private void addVerticalLine(int x, int yFrom, int yTo) {
        for(int index = Math.min(yFrom, yTo); index <= Math.max(yFrom, yTo); index++) {
            Position position = new Position(x, index);
            cellMap.computeIfAbsent(position, p -> new Cell(p, Cell.Type.ROCK));
        }
    }

    private int getFloorY() {
        return floorY;
    }
}
