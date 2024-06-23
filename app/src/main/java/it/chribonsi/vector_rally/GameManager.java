package it.chribonsi.vector_rally;

/**
 * Describes the general structure of a game manager.
 */
public interface GameManager {
    /**
     * Starts a race with the given players and racetrack.
     *
     * @return true if the race was started, false otherwise
     */
    boolean race();

    /**
     * Stops the game.
     *
     * @return true if the game was stopped, false otherwise
     */
    boolean isRaceFinished();

    /**
     * Adds a player to the leaderboard.
     *
     * @param player the player to add
     */
    void addPlayerToLeaderboard(Player player);
}
