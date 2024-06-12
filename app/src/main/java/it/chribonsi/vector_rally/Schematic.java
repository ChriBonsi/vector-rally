package it.chribonsi.vector_rally;

import java.util.List;

/**
 * Describes the structure of the drawing of a racetrack that can be transformed into one.
 */
public interface Schematic {

    /**
     * Checks the validity of the schematic.
     *
     * @return true if the schematic is valid, false otherwise
     */
    boolean checkValidity();

    /**
     * Parses the schematic to transform it into a grid of CellType.
     *
     * @return a grid of CellType
     */
    CellType[][] deriveGrid();

    /**
     * Derives a racetrack from the given grid of CellTypes.
     *
     * @return a racetrack
     */
    Racetrack deriveTrack();

    /**
     * Generates a list of players according to the schematic.
     *
     * @return the list of players
     */
    List<Player> generatePlayers();
}
