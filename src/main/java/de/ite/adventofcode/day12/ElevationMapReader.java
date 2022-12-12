package de.ite.adventofcode.day12;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ElevationMapReader {

    private ElevationMapReader() {}
    public static ElevationMap readElevationMap(List<String> inputLines) {
        int width = inputLines.get(0).length();
        int height = inputLines.size();
        Coordinate[][] coordinates = new Coordinate[width][height];
        List<Coordinate> coordinateList = new ArrayList<>();

        for (int line = 0; line < height; line++) {
            for (int column = 0; column < width; column++) {
                char elevationCode = inputLines.get(line).charAt(column);
                Position position = new Position(column, line);
                Coordinate coordinate = new Coordinate(position, getAltitude(elevationCode));
                if(elevationCode == 'E') {
                    coordinate.setTarget(true);
                }
                coordinates[column][line] = coordinate;
                coordinateList.add(coordinate);
            }
        }

        Coordinate target = coordinateList.stream().filter(Coordinate::isTarget).findFirst().orElseThrow();

        addReachableAdjacentCoordinates(coordinates);

        return new ElevationMapBuilder()
                .withCoordinateList(coordinateList)
                .withCoordinates(coordinates)
                .withTarget(target)
                .build();
    }

    private static void addReachableAdjacentCoordinates(Coordinate[][] coordinates) {
        int width = coordinates.length;
        int height = coordinates[0].length;

        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                Coordinate coordinate = coordinates[x][y];
                List<Coordinate> neighbors = new ArrayList<>();
                if(x > 0) {
                    neighbors.add(coordinates[x-1][y]);
                }
                if(x + 1 < width) {
                    neighbors.add(coordinates[x+1][y]);
                }
                if(y > 0) {
                    neighbors.add(coordinates[x][y-1]);
                }
                if(y + 1 < height) {
                    neighbors.add(coordinates[x][y+1]);
                }
                Predicate<Coordinate> filter = neighbor -> neighbor.getHeight() <= coordinate.getHeight() + 1;
                neighbors.stream().filter(filter).forEach(coordinate::addNeighbor);
            }
        }


    }

    private static int getAltitude(char elevationCode) {
        if (elevationCode == 'S') {
            return 0;
        } else if (elevationCode == 'E') {
            return 'z' - 'a';
        } else {
            return elevationCode - 'a';
        }
    }


    private static class ElevationMapBuilder {
        private Coordinate[][] coordinates;
        private Coordinate target;
        private List<Coordinate> coordinateList;

        ElevationMapBuilder withCoordinateList(List<Coordinate> coordinateList) {
            this.coordinateList = coordinateList;
            return this;
        }

        ElevationMapBuilder withCoordinates(Coordinate[][] coordinates) {
            this.coordinates = coordinates;
            return this;
        }

        ElevationMapBuilder withTarget(Coordinate target) {
            this.target = target;
            return this;
        }

        ElevationMap build() {
            return new ElevationMap(coordinateList, coordinates, target);
        }
    }
}
