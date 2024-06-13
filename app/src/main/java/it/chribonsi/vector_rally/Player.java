package it.chribonsi.vector_rally;

/**
 * Describes a general structure for a player.
 */
public interface Player {

    int[] decideNextMove();

    /**
     * Returns the name given to the Player.
     *
     * @return the name
     */
    String getName();
}
