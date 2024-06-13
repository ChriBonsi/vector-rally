package it.chribonsi.vector_rally;

import java.nio.file.Path;
import java.util.List;

public class SimpleGameManager implements GameManager {
    private final static String[] DIFFICULTY = {"EASY", "MEDIUM", "HARD", "RANDOM"};
    private final int playersNumber;
    private final Path mapPath;
    private final TXTSchematic gameSchema;
    private final List<Player> players;
    private final Racetrack racetrack;
    private final IOManager ioManager;

    public SimpleGameManager() {
        this.ioManager = new IOManager();
        TXTSchematic tempSchema;
        Path tempPath;
        do {
            tempPath = this.ioManager.selectSchemaFilePath();
            tempSchema = new TXTSchematic(tempPath);
        } while (tempSchema.checkValidity());
        this.mapPath = tempPath;
        this.gameSchema = tempSchema;
        this.players = this.gameSchema.getPlayers();
        this.playersNumber = this.players.size();
        this.racetrack = this.gameSchema.getTrack();
    }

    @Override
    public boolean startRace() {
        System.out.println("Starting race with " + this.playersNumber + " players on the track.");
        return false;
    }

    @Override
    public boolean stopRace() {
        return false;
    }

    public Racetrack getRacetrack() {
        return this.racetrack;
    }
}
