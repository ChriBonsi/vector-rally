package it.chribonsi.vector_rally;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        int i = 0;
        while (!this.isRaceFinished()) {
            i = this.printRaceStatus(i);
            List<Player> winners = new ArrayList<>();
            for (Player player : this.players) {
                MoveResult a = this.playerMove(player);
                if (a == MoveResult.WIN) {
                    winners.add(player);
                }
            }
            if (!winners.isEmpty()) {
                winners.forEach(players::remove);
            }
        }
        return this.isRaceFinished();
    }

    private int printRaceStatus(int counter) {
        counter++;
        System.out.println("\nTurn #" + counter);
        for (Player player : this.players) {
            System.out.println("Player " + player.getName() + " starts at" +
                    " position (" + this.racetrack.getRacePositions().get(player) + ")");
        }
        System.out.println("\n");
        return counter;
    }


    private MoveResult playerMove(Player player) {
        MoveResult thisResult = this.racetrack.movePlayer(player);
        if (thisResult == MoveResult.WIN) {
            System.out.println("WIN: Player " + player.getName() + " has finished the race in position #" + (this.leaderboard.size() + 1));
            //this.players.remove(player);
            this.addPlayerToLeaderboard(player);
        }
        return thisResult;
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
