package it.chribonsi.vector_rally;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SimpleGameManager implements GameManager {
    private final TXTSchematic gameSchema;
    private final ArrayList<Player> players;
    private final SimpleRacetrack racetrack;
    private final IOManager ioManager;
    private final HashMap<Player, Integer> leaderboard = new HashMap<>();

    //TODO: gameSchema potrebbe essere una variabile locale
    public SimpleGameManager() {
        this.ioManager = new IOManager();
        TXTSchematic tempSchema;
        do {
            tempSchema = new TXTSchematic(this.ioManager.selectSchemaFilePath());
        } while (!tempSchema.checkValidity());
        this.gameSchema = tempSchema;
        this.players = (ArrayList<Player>) this.gameSchema.getPlayers();
        this.racetrack = this.gameSchema.getTrack();
    }

    // Constructor meant for testing
    public SimpleGameManager(Path mapPath) {
        this.gameSchema = new TXTSchematic(mapPath);
        this.players = (ArrayList<Player>) gameSchema.getPlayers();
        this.racetrack = this.gameSchema.getTrack();
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

    public Racetrack getRacetrack() {
        return this.racetrack;
    }
}
