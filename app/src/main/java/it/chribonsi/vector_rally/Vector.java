package it.chribonsi.vector_rally;

public class Vector {
    final int dx;
    final int dy;

    private Vector(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public static Vector of(int dx, int dy) {
        return new Vector(dx, dy);
    }

    //todo
}
