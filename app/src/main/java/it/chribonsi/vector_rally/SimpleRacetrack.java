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
        //TODO
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
        //TODO
    }

    @Override
    public boolean isCellFree(Position position) {
        return this.racePositions.values().stream().noneMatch(p -> p.equals(position));
    }

    @Override
    public CellType getCell(Position position) {
        return this.grid[position.getX()][position.getY()];
    }

    @Override
    public Optional<Player> getPlayer(Position position) {
        if (this.racePositions.isEmpty() || !this.racePositions.containsValue(position)) {
            return Optional.empty();
        }
        return this.racePositions.entrySet().stream().filter(entry -> entry.getValue().equals(position)).findFirst().map(Map.Entry::getKey);
    }

    public void movePlayerBack(Player player) {
        //get the player's last position
        //update the player's position in the map
        //update the player's last movement in the map
        //TODO
    }

    // Getters
    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Map<Player, Position> getRacePositions() {
        return racePositions;
    }

    public CellType[][] getGrid() {
        return grid;
    }
}
