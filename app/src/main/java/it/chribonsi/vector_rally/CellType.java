package it.chribonsi.vector_rally;

/**
 * Describes the type of cells in the racetrack.
 * START: the cells of the starting line
 * FINISH: the cells of the finish line
 * ROAD: the cells of the road (inside the borders)
 * BORDER: the cells of the borderlines
 * OBSTACLE: the cells of the obstacles (oil spills)
 * OUTSIDE: the cells outside the racetrack
 */
public enum CellType {
    START, FINISH, ROAD, BORDER, OUTSIDE;

    public static CellType fromChar(char c) {
        return switch (c) {
            case '-' -> ROAD;
            case '@' -> OUTSIDE;
            case '/' -> BORDER;
            case '~' -> START;
            case '#' -> FINISH;
            default -> throw new IllegalArgumentException("Unknown cell type: " + c);
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case START -> "~";
            case FINISH -> "#";
            case ROAD -> "-";
            case BORDER -> "/";
            case OUTSIDE -> "@";
        };
    }
}