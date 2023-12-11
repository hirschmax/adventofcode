package de.ite.adventofcode.day03;

import java.math.BigInteger;
import java.util.*;

public class Engine {
    private final char[][] schematic;
    private final Map<Position, SchematicNumber> positionToSchematicNumberMap = new HashMap<>();
    private final List<SchematicNumber> schematicNumbers = new ArrayList<>();
    private final List<Star> stars = new ArrayList<>();

    public List<SchematicNumber> getSchematicNumbers() {
        return Collections.unmodifiableList(schematicNumbers);
    }

    public char[][] getSchematic() {
        return schematic;
    }

    public List<Star> getStars() {
        return Collections.unmodifiableList(stars);
    }

    public Map<Position, SchematicNumber> getPositionToSchematicNumberMap() {
        return positionToSchematicNumberMap;
    }

    public Engine(List<String> inputLines) {
        schematic = new char[inputLines.size()][inputLines.get(0).length()];

        for (int row = 0; row < inputLines.size(); row++) {
            String line = inputLines.get(row);
            StringBuilder currentNumber = new StringBuilder();
            readRow(row, line, currentNumber);
        }
    }

    private void readRow(int rowNumber, String line, StringBuilder currentNumber) {
        for(int col = 0; col < line.length(); col++) {
            char c = line.charAt(col);
            schematic[rowNumber][col] = c;
            if(Character.isDigit(c)) {
                currentNumber.append(c);
            } else if(!currentNumber.isEmpty()) {
                int minColInclusive = col - currentNumber.length();
                int maxColInclusive = col - 1;
                createSchematicNumberAndAddItToCorrespondingFields(rowNumber, currentNumber, minColInclusive, maxColInclusive);
                currentNumber = new StringBuilder();
            }
            if(c == '*') {
                stars.add(new Star(rowNumber, col));
            }
        }

        if(!currentNumber.isEmpty()) {
            int minColInclusive = line.length() - currentNumber.length();
            int maxColInclusive = line.length() - 1;
            createSchematicNumberAndAddItToCorrespondingFields(rowNumber, currentNumber, minColInclusive, maxColInclusive);
        }
    }

    private void createSchematicNumberAndAddItToCorrespondingFields(int row, StringBuilder currentNumber, int minColInclusive, int maxColInclusive) {
        int number = Integer.parseInt(currentNumber.toString());
        SchematicNumber schematicNumber = new SchematicNumber(number, row, minColInclusive, maxColInclusive);
        schematicNumbers.add(schematicNumber);
        for(int i = minColInclusive; i <= maxColInclusive; i++) {
            positionToSchematicNumberMap.put(new Position(row, i), schematicNumber);
        }
    }

    public record SchematicNumber(int numberValue, int row, int minColInclusive, int maxColInclusive) {
        public boolean isAdjacentToSymbol(char[][] schematic) {
            int rowMin = Math.max(0, row - 1);
            int rowMax = Math.min(schematic.length - 1, row + 1);
            int minCol = Math.max(0, minColInclusive - 1);
            int maxCol = Math.min(schematic[0].length - 1, maxColInclusive + 1);
            for(int r = rowMin; r <= rowMax; r++) {
                for(int c = minCol; c <= maxCol; c++) {
                    char characterAtPosition = schematic[r][c];
                    if(!Character.isDigit(characterAtPosition) && characterAtPosition != '.') {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public record Star(int x, int y) {
        BigInteger getGearRatio(Map<Position, SchematicNumber> partNumberMap) {
            Set<SchematicNumber> adjacentPartNumbers = new HashSet<>();
            for(int i = x - 1; i <= x + 1; i++) {
                for(int j = y - 1; j <= y + 1; j++) {
                    SchematicNumber partNumber = partNumberMap.get(new Position(i, j));
                    if(partNumber != null) {
                        adjacentPartNumbers.add(partNumber);
                    }
                }
            }
            if(adjacentPartNumbers.size() != 2) {
                return BigInteger.ZERO;
            }
            return adjacentPartNumbers
                    .stream()
                    .map(n -> BigInteger.valueOf(n.numberValue()))
                    .reduce(BigInteger.ONE, BigInteger::multiply);
        }
    }
    public record Position(int x, int y) {}

}
