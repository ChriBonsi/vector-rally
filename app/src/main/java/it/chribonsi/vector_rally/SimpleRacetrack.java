package it.chribonsi.vector_rally;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SimpleRacetrack implements Racetrack {
    private final CellType[][] grid;
    private final List<Player> players;
    private final Difficulty difficulty;
    private final Map<Player, Position> map = new HashMap<>();

    SimpleRacetrack(CellType[][] grid, List<Player> playerList, Difficulty difficulty) {
        this.grid = grid;
        this.players = playerList;
        this.difficulty = difficulty;
        for (Player player : playerList) {
            map.put(player, Position.of(0, 0));
        }
    }

    @Override
    public boolean addPlayer(Player player) {
        return false;
    }

    @Override
    public MoveResult movePlayer(Player player) {
        return null;
    }

    @Override
    public void getNeighbours(Position position) {

    }

    @Override
    public CellType getCell(Position position) {
        return null;
    }

    @Override
    public Optional<Player> getPlayer(Position position) {
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
