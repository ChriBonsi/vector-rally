package it.chribonsi.vector_rally;

import java.awt.*;

/**
 * Describes the structure of the drawing of a racetrack that can be transformed into one.
 */
public interface Drawing {

    /**
     * Receives the drawing of the racetrack in input.
     *
     * @param path the path of where the drawing is stored
     * @return the drawing as an Image
     */
    Image getDrawing(String path);

    /**
     * Transforms the drawing into a grid of integers.
     *
     * @param drawing the given drawing of the racetrack
     * @return a grid of integers
     */
    int[][] deriveRGBGrid(Image drawing);

    /**
     * Derives a grid of cells from the integers' grid.
     * This grid is a matrix of Cell where each cell represents if a pixel
     * is part of the track, the starting/finish line, the borders or something else.
     *
     * @param grid the calculated grid of integers
     * @return a grid of Cell
     */
    Cell[][] deriveTrack(int[][] grid);
}
