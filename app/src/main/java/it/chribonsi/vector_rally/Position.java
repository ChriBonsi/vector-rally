package it.chribonsi.vector_rally;

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
}
