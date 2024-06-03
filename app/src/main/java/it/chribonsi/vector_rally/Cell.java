package it.chribonsi.vector_rally;

/**
 * Describes the structure for a cell of the racetrack.
 * The cell has an immutable type (CellType) and can eventually
 * host a Player.
 */
public class Cell {
    private final CellType type;
    private Player player;

    public Cell(CellType type) {
        this.type = type;
    }

    public CellType getType() {
        return type;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
