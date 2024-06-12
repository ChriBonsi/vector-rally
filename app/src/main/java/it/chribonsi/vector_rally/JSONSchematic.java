package it.chribonsi.vector_rally;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

public class JSONSchematic implements Schematic {
    private final Path txtSchematicPath;
    private final File schematic;
    private final CellType[][] grid;
    private final List<Player> players;
    private final Racetrack track;

    public JSONSchematic(Path txtSchematicPath) {
        this.txtSchematicPath = txtSchematicPath;
        this.schematic = this.txtSchematicPath.toFile();
        this.grid = deriveGrid();
        this.track = deriveTrack();
        this.players = this.generatePlayers();
    }

    @Override
    public boolean checkValidity() {
        return true;
    }

    //TODO
    @Override
    public CellType[][] deriveGrid() {
        if (this.schematic == null || !this.checkValidity()) {
            return null;
        }
        return new CellType[0][];
    }

    @Override
    public Racetrack deriveTrack() {
        if (this.grid == null){
            return null;
        }
        return null;
    }

    @Override
    public List<Player> generatePlayers() {
        return List.of();
    }

    public List<Player> getPlayers() {
        return this.players;
    }


//    public Player[] generatePlayers(int totalPlayers, int humanPlayers) {
//        Player[] players = new Player[totalPlayers];
//        for (int i = 0; i < humanPlayers; i++) {
//            players[i] = new HumanPlayer(name);
//        }
//        for (int i = humanPlayers; i < totalPlayers; i++) {
//            players[i] = new BotPlayer();
//        }
//        return players;
//    }

    public Path getTxtSchematicPath() {
        return this.txtSchematicPath;
    }

    public CellType[][] getGrid() {
        return this.grid;
    }

    public Racetrack getTrack() {
        return this.track;
    }
}
