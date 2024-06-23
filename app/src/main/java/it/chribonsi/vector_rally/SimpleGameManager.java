package it.chribonsi.vector_rally;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SimpleGameManager implements GameManager {
    private final List<Player> players;
    private final SimpleRacetrack racetrack;
    private final IOManager ioManager;
    private final LinkedHashMap<Player, Integer> leaderboard = new LinkedHashMap<>();

    public SimpleGameManager() {
        this.ioManager = new IOManager();
        TXTSchematic gameSchema;
        do {
            gameSchema = new TXTSchematic(this.ioManager.selectSchemaFilePath());
        } while (!gameSchema.checkValidity());
        this.players = gameSchema.getPlayers();
        this.racetrack = gameSchema.getTrack();
    }

    // Constructor meant for testing
    public SimpleGameManager(Path mapPath) {
        TXTSchematic gameSchema = new TXTSchematic(mapPath);
        this.players = gameSchema.getPlayers();
        this.racetrack = gameSchema.getTrack();
        this.ioManager = null;
    }

    @Override
    public boolean race() {
        int turnCounter = 0;
        while (!this.isRaceFinished()) {
            turnCounter = this.printRaceStatus(turnCounter);
            List<Player> winners = new ArrayList<>();
            for (Player player : this.players) {
                MoveResult moveResult = this.playerMove(player);
                if (moveResult == MoveResult.WIN) {
                    winners.add(player);
                }
            }
            if (!winners.isEmpty()) {
                this.players.removeAll(winners);
            }
            IOManager.printMap(this.racetrack.getGrid(), this.racetrack.getRacePositions());
            System.out.println("\n");
        }
        return this.isRaceFinished();
    }

    private int printRaceStatus(int counter) {
        counter++;
        System.out.println("TURN #" + counter + "\n");
        return counter;
    }

    private MoveResult playerMove(Player player) {
        MoveResult moveResult = this.racetrack.movePlayer(player);
        if (moveResult == MoveResult.WIN) {
            System.out.println("WIN: Player " + player.name() + " has finished the race in position #" + (this.leaderboard.size() + 1));
            this.addPlayerToLeaderboard(player);
        }
        return moveResult;
    }

    @Override
    public boolean isRaceFinished() {
        return !this.leaderboard.isEmpty() && this.players.isEmpty();
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
