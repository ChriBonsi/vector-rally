package it.chribonsi.vector_rally;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SimpleRacetrack implements Racetrack {
    private final CellType[][] grid;
    private final List<Player> players;

    private final Map<Player, Position> racePositions = new HashMap<>();
    private final Map<Player, Vector> lastMovements = new HashMap<>();
    private final List<Position> startingLine;

    SimpleRacetrack(CellType[][] grid, List<Player> playerList, List<Position> startingLine) {
        this.grid = grid;
        this.players = playerList;
        this.startingLine = startingLine;
        playerList.forEach(this::addPlayerToStartingLine);
        this.initializePlayersLastMovements();
    }

    private void initializePlayersLastMovements() {
        players.forEach(player -> this.lastMovements.put(player, Vector.of(0, 0)));

        IOManager.printMap(this.grid, this.racePositions);
    }

    @Override
    public void addPlayerToStartingLine(Player player) {
        this.racePositions.put(player, this.selectRandomStartingCell());

        System.out.println("Player " + player.getName() + " starts at position " + this.racePositions.get(player).toString());

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
        // Get the player's current position
        Position currentPosition = this.racePositions.get(player);

        // Get the player's next position from the player's strategy
        Movement nextMove = player.decideNextMove(this);
        if (nextMove == null) {
            return MoveResult.CRASH;
        }
        Vector playerMovement = this.getLastMove(player).sum(nextMove.getOffset());

        Position nextPosition = calculateNextPosition(currentPosition, playerMovement);
        //System.out.println("MOVE: Player " + player.getName() + " tries to move to " + nextPosition.toString());

        // Get the actual result of the move
        MoveResult result = this.determineResult(currentPosition, playerMovement, nextPosition);

        // If the result is CRASH handle it
        if (result == MoveResult.CRASH) {
            // Cancel the move and the inertia
            System.out.println("CRASH: Player " + player.getName() + " crashed in " + nextPosition);
            this.lastMovements.put(player, Vector.of(0, 0));
            return result;
        }
        // Update the player's position in racePositions
        this.racePositions.put(player, nextPosition);

        // Update the player's last movement
        this.lastMovements.put(player, playerMovement);

        return result;
    }

    @Override
    public MoveResult determineResult(Position currentPosition, Vector offset, Position nextPosition) {
        // Check if the path is clear
        if (isPathClear(currentPosition, nextPosition)) {
            // Check the type of the landing cell
            CellType landingCell = this.getCell(nextPosition);
            return switch (landingCell) {
                case FINISH -> MoveResult.WIN;
                case ROAD, START -> MoveResult.OK;
                default -> MoveResult.CRASH;
            };
        }
        return MoveResult.CRASH;
    }

    private Position calculateNextPosition(Position currentPosition, Vector offset) {
        return offset.addTo(currentPosition);
    }

    private boolean isPathClear(Position start, Position end) {
        int x0 = start.getX();
        int y0 = start.getY();
        int x1 = end.getX();
        int y1 = end.getY();

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;

        int err = dx - dy;

        while (true) {
            // Check if the current cell is free
            CellType cellType = this.getCell(Position.of(x0, y0));
            if (!isValidPathCell(cellType)) {
                return false;
            }

            // If we've reached the end point, break
            if (x0 == x1 && y0 == y1) {
                break;
            }

            int e2 = 2 * err;

            if (e2 > -dy) {
                err -= dy;
                x0 += sx;
            }

            if (e2 < dx) {
                err += dx;
                y0 += sy;
            }
        }

        return true;
    }

    private boolean isValidPathCell(CellType cellType) {
        return cellType == CellType.ROAD || cellType == CellType.START || cellType == CellType.FINISH;
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
        return this.racePositions.entrySet().stream()
                .filter(entry -> entry.getValue().equals(position))
                .map(Map.Entry::getKey)
                .findFirst();
    }

    // Getters
    public List<Player> getPlayers() {
        return players;
    }

    public Map<Player, Position> getRacePositions() {
        return racePositions;
    }

    public CellType[][] getGrid() {
        return grid;
    }

    public Vector getLastMove(Player player) {
        return this.lastMovements.get(player);
    }

    public Position getCurrentPosition(Player player) {
        return this.racePositions.get(player);
    }
}
