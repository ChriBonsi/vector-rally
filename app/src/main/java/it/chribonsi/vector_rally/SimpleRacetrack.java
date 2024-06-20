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
    private final Map<Player, Vector> lastMovements = new HashMap<>();
    private final List<Position> startingLine;

    SimpleRacetrack(CellType[][] grid, List<Player> playerList, Difficulty difficulty, List<Position> startingLine) {
        this.grid = grid;
        this.players = playerList;
        this.difficulty = difficulty;
        this.startingLine = startingLine;
        playerList.forEach(this::addPlayerToStartingLine);
        this.racePositions.forEach((key, value) -> System.out.println("Player " + key.getName() + " added to the starting line in position (" + value.getX() + " , " + value.getY() + ")")); //TODO eliminare
        this.initializePlayersLastMovements();
    }

    private void initializePlayersLastMovements() {
        players.forEach(player -> this.lastMovements.put(player, Vector.of(0, 0)));
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

    //TODO check correctness
    @Override
    public MoveResult movePlayer(Player player) {
        //get the player's current position
        Position currentPosition = this.racePositions.get(player);

        //get the player's next position from the player's strategy
        Movement nextMove = player.decideNextMove();
        Vector offset = nextMove.getOffset(this.getLastMove(player));
        Position nextPosition = calculateNextPosition(currentPosition, offset, player);

        //check cell type of the next position TODO forse eliminare
        CellType nextCellType = this.getCell(nextPosition);

        //get the actual result of the move
        MoveResult result = this.determineResult(currentPosition, offset, nextPosition);

        //if the result is CRASH handle it
        if (result == MoveResult.CRASH) {
            //cancel the move and the inertia
            this.lastMovements.put(player, Vector.of(0, 0));
            return result;
        }

        //update the player's position in racePositions
        this.racePositions.put(player, nextPosition);

        //update the player's last movement
        this.lastMovements.put(player, offset);

        return result;
    }

    @Override
    public MoveResult determineResult(Position currentPosition, Vector offset, Position nextPosition) {
        //TODO
        CellType landingCell = this.getCell(nextPosition);
        if (this.isCellFree(nextPosition)) {
            return MoveResult.OK;
        }
        return null;
    }

    private Position calculateNextPosition(Position currentPosition, Vector offset, Player player) {
        //TODO
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

    private Vector getLastMove(Player player) {
        return this.lastMovements.get(player);
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
