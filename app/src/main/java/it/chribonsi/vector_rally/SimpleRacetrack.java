package it.chribonsi.vector_rally;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SimpleRacetrack implements Racetrack {
    private final CellType[][] grid;
    private final List<Player> players;
    private final Difficulty difficulty;
    private final Map<Player, Position> racePositions = new HashMap<>();
    private final List<Position> startingLine;

    SimpleRacetrack(CellType[][] grid, List<Player> playerList, Difficulty difficulty, List<Position> startingLine) {
        this.grid = grid;
        this.players = playerList;
        this.difficulty = difficulty;
        this.startingLine = startingLine;
        for (Player player : playerList) {
            this.addPlayerToStartingLine(player);
            System.out.println("Player " + player.getName() + " added to the starting line in position (" + this.racePositions.get(player).getX() + " , " + this.racePositions.get(player).getY() + ")");
        }
    }

    @Override
    public void addPlayerToStartingLine(Player player) {
        this.racePositions.put(player, this.selectRandomStartingCell());
    }

    private Position selectRandomStartingCell() {
        Position startingCell = null;
        while (startingCell == null || !this.isCellFree(startingCell)) {
            if (startingCell != null) {
                this.startingLine.remove(startingCell);
            }
            startingCell = this.startingLine.get((int) (Math.random() * this.startingLine.size()));
        }
        return startingCell;
    }

    @Override
    public MoveResult movePlayer(Player player) {
        //get the player's current position
        //get the player's next position from the player's strategy
        //check cell type of the next position
        //get the result of the move
        //if the result is CRASH handle it
        //update the player's position in the map
        //update the player's last movement in the map
        //return the result of the move
        return null;
    }

    @Override
    public void getNeighbours(Position position) {

    }

    @Override
    public boolean isCellFree(Position position) {
        return this.getPlayer(position).isEmpty();
    }

    @Override
    public CellType getCell(Position position) {
        return null;
    }

    @Override
    public Optional<Player> getPlayer(Position position) {
        for (Player player : this.players) {
            if (this.racePositions.isEmpty()) {
                return Optional.empty();
            }
            try {
                if (this.racePositions.get(player).equals(position)) {
                    return Optional.of(player);
                }
            } catch (NullPointerException e) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    @Override
    public int calculateHeight() {
        return 0;
    }

    @Override
    public int calculateWidth() {
        return 0;
    }
}
