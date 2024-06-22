package it.chribonsi.vector_rally;

import java.util.Random;

/**
 * Enumerates the possible movements of the player.
 * The movements are linked to the 9 possible cells of the grid
 * in which the player can move with the next move.
 */
public enum Movement {
    TOP_L(-1, 1), TOP_C(0, 1), TOP_R(1, 1),
    MIDDLE_L(-1, 0), MIDDLE_C(0, 0), MIDDLE_R(1, 0),
    BOTTOM_L(-1, -1), BOTTOM_C(0, -1), BOTTOM_R(1, -1);

    public static final Movement[] VALUES = values();
    private static final Random RANDOMIZER = new Random();
    private final Vector offset;

    Movement(int dx, int dy) {
        this.offset = Vector.of(dx, dy);
    }

    /**
     * Returns a random movement.
     *
     * @return a random movement
     */
    public static Movement getRandomMovement(Movement... array) {
        if (array.length == 0) {
            return VALUES[RANDOMIZER.nextInt(VALUES.length)];
        }
        return array[RANDOMIZER.nextInt(VALUES.length)];
    }

    public static Movement getDirection(Vector vector) {

        return null;
    }

    /**
     * Returns the offset of the movement.
     *
     * @return the offset of the movement
     */
    public Vector getOffset() {
        return this.offset;
    }
}
