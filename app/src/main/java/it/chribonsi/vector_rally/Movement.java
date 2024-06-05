package it.chribonsi.vector_rally;

/**
 * Enumerates the possible movements of the player.
 * The movements are linked to the 9 possible cells of the grid
 * in which the player can move with the next move.
 */
public enum Movement {
    TOP_L, TOP_C, TOP_R,
    CENTER_L, CENTER_C, CENTER_R,
    BOTTOM_L, BOTTOM_C, BOTTOM_R;
}
