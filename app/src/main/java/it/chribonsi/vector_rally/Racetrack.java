package it.chribonsi.vector_rally;

import java.util.Optional;

/**
 * Describes a general structure for a racetrack.
 */
public interface Racetrack {

    /**
     * Checks the validity of the racetrack.
     *
     * @return true if the racetrack is valid, false otherwise
     */
    boolean isValid();

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

    //TODO
    /**
     * Returns the neighbours of a cell.
     *
     * @param position the coordinates of the cell
     */
    void getNeighbours(Position position);

    /**
     * Returns the cell in the racetrack.
     *
     * @param position the coordinates of the cell
     * @return the cell
     */
    CellType getCell(Position position);

    /**
     * Returns the type of cell in the racetrack.
     *
     * @param position the coordinates of the cell
     * @return the state of the cell
     */
    CellType getCellType(Position position);

    /**
     * Returns the player in a cell.
     *
     * @param position the coordinates of the cell
     * @return the player in the cell
     */
    Optional<Player> getPlayer(Position position);

    /**
     * Calculates the height of the racetrack.
     *
     * @return the height
     */
    int calculateHeight();

    /**
     * Calculates the width of the racetrack.
     *
     * @return the width
     */
    int calculateWidth();
}
