package it.chribonsi.vector_rally;

import java.util.Optional;

/**
 * Describes a general structure for a racetrack.
 */
public interface Racetrack {

    /**
     * Adds a player to the racetrack and positions it on the starting line.
     *
     * @param player the player to add
     */
    void addPlayerToStartingLine(Player player);

    /**
     * Moves a player on the racetrack.
     *
     * @param player the player to move
     * @return the state that the player is in after the move
     */
    MoveResult movePlayer(Player player);

    /**
     * Determines the result of a movement.
     *
     * @param currentPosition the current position of the player
     * @param offset          the offset of the movement
     * @return the result of the movement
     */
    MoveResult determineResult(Position currentPosition, Vector offset, Position nextPosition);

    /**
     * Returns the neighbours of a cell.
     *
     * @param position the coordinates of the cell
     */
    void getNeighbours(Position position);

    /**
     * Checks if a cell is free or occupied by a Player.
     *
     * @param position the coordinates of the cell
     * @return true if the cell is free, false otherwise
     */
    boolean isCellFree(Position position);

    /**
     * Returns the cell in the racetrack.
     *
     * @param position the coordinates of the cell
     * @return the cell
     */
    CellType getCell(Position position);

    /**
     * Returns the player in a cell.
     *
     * @param position the coordinates of the cell
     * @return the player in the cell
     */
    Optional<Player> getPlayer(Position position);
}
