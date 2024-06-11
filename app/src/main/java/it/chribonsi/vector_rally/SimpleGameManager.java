package it.chribonsi.vector_rally;

import java.nio.file.Path;
import java.util.List;

public class SimpleGameManager implements GameManager {
    private final static String[] DIFFICULTY = {"EASY", "MEDIUM", "HARD", "RANDOM"};
    private final int playersNumber;
    private final Path mapPath;
    private final JSONSchematic gameSchema;
    private final List<Player> players;
    private final Racetrack racetrack;
    private final IOManager ioManager;

    public SimpleGameManager() {
        this.ioManager = new IOManager();
        this.mapPath = this.ioManager.selectSchemaFilePath();
        this.gameSchema = new JSONSchematic(this.mapPath);
        this.playersNumber = this.gameSchema.getPlayers().size();
        this.players = this.gameSchema.getPlayers();
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

    @Override
    public Player[] generatePlayers(int totalPlayers, int humanPlayers) {
        Player[] players = new Player[totalPlayers];
        for (int i = 0; i < humanPlayers; i++) {
            players[i] = new HumanPlayer();
        }
        for (int i = humanPlayers; i < totalPlayers; i++) {
            players[i] = new BotPlayer();
        }
        return players;
    }
}
