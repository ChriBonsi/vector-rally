package it.chribonsi.vector_rally;

import java.util.Optional;

/**
 * Describes a general structure for a racetrack.
 */
public interface Racetrack {

    /**
     * Adds a player to the racetrack.
     *
     * @param player the player to add
     * @return true if the player was added, false otherwise
     */
    boolean addPlayer(Player player);

    /**
     * Moves a player on the racetrack.
     *
     * @param player the player to move
     * @return the state that the player is in after the move
     */
    MoveResult movePlayer(Player player);

    void getNeighbours(int x, int y);

    /**
     * Returns the cell in the racetrack.
     *
     * @param x the x coordinate of the cell
     * @param y the y coordinate of the cell
     * @return the cell
     */
    Cell getCell(int x, int y);

    /**
     * Returns the type of cell in the racetrack.
     *
     * @param x the x coordinate of the cell
     * @param y the y coordinate of the cell
     * @return the state of the cell
     */
    CellType getCellType(int x, int y);

    /**
     * Returns the player in a cell.
     *
     * @param x the x coordinate of the cell
     * @param y the y coordinate of the cell
     * @return the player in the cell
     */
    Optional<Player> getPlayer(int x, int y);
}
