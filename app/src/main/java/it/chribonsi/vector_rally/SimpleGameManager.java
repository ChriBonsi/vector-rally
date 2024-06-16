package it.chribonsi.vector_rally;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SimpleGameManager implements GameManager {
    private final static String[] DIFFICULTY = {"EASY", "MEDIUM", "HARD", "RANDOM"};
    private final Path mapPath;
    private final TXTSchematic gameSchema;
    private final ArrayList<Player> players;
    private final SimpleRacetrack racetrack;
    private final IOManager ioManager;
    private final HashMap<Player, Integer> leaderboard = new HashMap<>();

    public SimpleGameManager() {
        this.ioManager = new IOManager();
        TXTSchematic tempSchema;
        Path tempPath;
        do {
            tempPath = this.ioManager.selectSchemaFilePath();
            tempSchema = new TXTSchematic(tempPath);
        } while (!tempSchema.checkValidity());
        this.mapPath = tempPath;
        this.gameSchema = tempSchema;
        this.players = (ArrayList<Player>) this.gameSchema.getPlayers();
        this.racetrack = this.gameSchema.getTrack();
    }

    @Override
    public boolean startRace() {
        System.out.println("Starting race with " + this.players.size() + " players on the track.");
        while (!this.isRaceFinished()) {
            for (Player player : this.players) {
                this.playerMove(player);
            }
        }
        return this.isRaceFinished();
    }

    private void playerMove(Player player) {
        MoveResult thisResult = this.racetrack.movePlayer(player);
        if (thisResult == MoveResult.WIN) {
            this.players.remove(player);
            this.addPlayerToLeaderboard(player);
        } else if (thisResult == MoveResult.CRASH) {
            this.racetrack.movePlayerBack(player);
        }
    }

    @Override
    public boolean isRaceFinished() {
        return this.leaderboard.size() == this.players.size();
    }

    @Override
    public void addPlayerToLeaderboard(Player player) {
        this.leaderboard.put(player, this.leaderboard.size() + 1);
    }

    // Getters
    public Map<Player, Integer> getLeaderboard() {
        return this.leaderboard;
    }

    public Racetrack getRacetrack() {
        return this.racetrack;
    }
}
