package it.chribonsi.vector_rally;

import java.util.Objects;

public class Position {
    final int x;
    final int y;

    private Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(int x, int y) {
        return new Position(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "position (" + x + ", " + y + ")";
    }

    // Getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
