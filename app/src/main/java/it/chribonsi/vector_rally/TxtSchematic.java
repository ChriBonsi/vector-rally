package it.chribonsi.vector_rally;

import java.io.File;

public class TxtSchematic implements Schematic {
    private final String txtSchematicPath;
    private final CellType[][] grid;
    private final Racetrack track;

    public TxtSchematic(String txtSchematicPath) {
        this.txtSchematicPath = txtSchematicPath;
        if (!this.checkValidity()) throw new IllegalArgumentException("Invalid schematic file.");
        else {
            this.grid = this.deriveGrid(new File(txtSchematicPath));
            this.track = this.deriveTrack(this.grid);
        }
    }

    @Override
    public boolean checkValidity() {
        return false;
    }

    @Override
    public CellType[][] deriveGrid(File schematic) {
        return new CellType[0][];
    }

    @Override
    public Racetrack deriveTrack(CellType[][] grid) {
        return null;
    }

    public String getTxtSchematicPath() {
        return txtSchematicPath;
    }

    public CellType[][] getGrid() {
        return grid;
    }

    public Racetrack getTrack() {
        return track;
    }
}
