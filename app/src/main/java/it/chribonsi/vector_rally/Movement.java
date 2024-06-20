package it.chribonsi.vector_rally;

/**
 * Enumerates the possible movements of the player.
 * The movements are linked to the 9 possible cells of the grid
 * in which the player can move with the next move.
 */
public enum Movement {
    FAST_L, FAST_C, FAST_R,
    MEDIUM_L, MEDIUM_C, MEDIUM_R,
    SLOW_L, SLOW_C, SLOW_R;

    public Vector getOffset(Vector lastMovement) {
        //TODO
        return null;
    }
}
