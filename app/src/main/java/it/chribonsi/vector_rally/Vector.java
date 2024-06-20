package it.chribonsi.vector_rally;

import java.util.Objects;

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

    public Vector sum(Vector other) {
        return Vector.of(this.dx + other.dx, this.dy + other.dy);
    }

    public Position addTo(Position position) {
        return Position.of(position.getX() + this.dx, position.getY() + this.dy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return dx == vector.dx && dy == vector.dy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dx, dy);
    }

    @Override
    public String toString() {
        return "Vector {" +
                "dx=" + dx +
                ", dy=" + dy +
                '}';
    }

    // Getters
    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}
