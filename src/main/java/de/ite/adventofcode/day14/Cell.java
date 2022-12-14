package de.ite.adventofcode.day14;

public class Cell {

    enum Type {
        ROCK('#'),
        AIR('.'),
        SAND('+'),
        SAND_AT_REST('o');

        final char code;
        Type(char code) {
            this.code = code;
        }
    }

    private Type type;
    private final Position position;

    public Cell(Position position, Type type) {
        this.position = position;
        this.type = type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public Position getPosition() {
        return position;
    }

}
