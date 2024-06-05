package it.chribonsi.vector_rally;

import java.io.File;

/**
 * Describes the structure of the drawing of a racetrack that can be transformed into one.
 */
public interface Schematic {

    /**
     * Receives the schematic of the racetrack in input.
     *
     * @param path the path of where the schematic is stored
     * @return the schematic as an Image
     */
    File getSchematic(String path);

    /**
     * Transforms the schematic into a grid of CellType.
     *
     * @param schematic the given schematic of the racetrack
     * @return a grid of CellType
     */
    CellType[][] deriveGrid(File schematic);

    /**
     * Derives a racetrack from the given grid of CellTypes.
     *
     * @param grid the calculated grid of integers
     * @return a racetrack
     */
    Racetrack deriveTrack(CellType[][] grid);
}
