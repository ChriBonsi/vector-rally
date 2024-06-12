package it.chribonsi.vector_rally;

import java.util.List;

/**
 * Parses the file of the structure of a racetrack with its players and returns it.
 */
public interface Schematic {

    /**
     * Checks the validity of the schematic.
     *
     * @return true if the schematic is valid, false otherwise
     */
    boolean checkValidity();

    /**
     * Parses the schematic file and sets the grid, the difficulty and the players.
     */
    void parseSchematic();

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
}
