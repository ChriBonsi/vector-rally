package it.chribonsi.vector_rally;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SimpleGameManager implements GameManager {
    private final ArrayList<Player> players;
    private final SimpleRacetrack racetrack;
    private final IOManager ioManager;
    private final HashMap<Player, Integer> leaderboard = new HashMap<>();

    public SimpleGameManager() {
        this.ioManager = new IOManager();
        TXTSchematic gameSchema;
        do {
            gameSchema = new TXTSchematic(this.ioManager.selectSchemaFilePath());
        } while (!gameSchema.checkValidity());
        this.players = (ArrayList<Player>) gameSchema.getPlayers();
        this.racetrack = gameSchema.getTrack();
    }

    // Constructor meant for testing
    public SimpleGameManager(Path mapPath) {
        TXTSchematic gameSchema = new TXTSchematic(mapPath);
        this.players = (ArrayList<Player>) gameSchema.getPlayers();
        this.racetrack = gameSchema.getTrack();
        this.ioManager = null;
    }

    @Override
    public boolean startRace() {
        System.out.println("Starting race with " + this.players.size() + " players on the track.");
        int i = 1;
        while (!this.isRaceFinished()) {
            i = this.printRaceStatus(i);
            for (Player player : this.players) {
                this.playerMove(player);
            }
            if (i > 20) break;
        }
        return this.isRaceFinished();
    }

    private int printRaceStatus(int counter) {
        System.out.println("Turn #" + counter);
        return counter + 1;
    }


    private void playerMove(Player player) {
        MoveResult thisResult = this.racetrack.movePlayer(player);
        if (thisResult == MoveResult.WIN) {
            this.players.remove(player);
            this.addPlayerToLeaderboard(player);
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
}
