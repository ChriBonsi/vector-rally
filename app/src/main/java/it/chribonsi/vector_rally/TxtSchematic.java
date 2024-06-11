package it.chribonsi.vector_rally;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

public class TxtSchematic implements Schematic {
    private final Path txtSchematicPath;
    private final CellType[][] grid;
    private final Racetrack track;

    public TxtSchematic(Path txtSchematicPath) {
        this.txtSchematicPath = txtSchematicPath;
        this.grid = deriveGrid(txtSchematicPath.toFile());
        this.track = deriveTrack(grid);
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

    @Override
    public List<Player> getPlayers() {
        return List.of();
    }

    public Path getTxtSchematicPath() {
        return txtSchematicPath;
    }

    public CellType[][] getGrid() {
        return grid;
    }

    public Racetrack getTrack() {
        return track;
    }
}
