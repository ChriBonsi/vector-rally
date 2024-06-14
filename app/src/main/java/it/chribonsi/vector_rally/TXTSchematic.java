package it.chribonsi.vector_rally;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TXTSchematic implements Schematic {
    private final Path filePath;
    private final CellType[][] grid;
    private final List<Player> players = new ArrayList<>();
    private final Racetrack track;
    private final List<String> trackLines = new ArrayList<>();
    private final List<Position> startingLine = new ArrayList<>();
    private Difficulty difficulty = Difficulty.valueOf("EASY");

    public TXTSchematic(Path filePath) {
        this.filePath = filePath;
        this.parseSchematic();
        this.grid = deriveGrid();
        this.track = deriveTrack();
    }

    @Override
    public void parseSchematic() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath.toString()))) {
            String line;
            while ((line = br.readLine()) != null) {
                switch (line.charAt(0)) {
                    case '\'' -> this.processPlayerLine(line, players);
                    case '@', '/', '~', '-' -> this.processTrackLine(line, trackLines);
                    default -> difficulty = this.processDifficultyLine(line, difficulty);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    @Override
    public boolean checkValidity() {
        // Implement validity checks as needed
        return true;
    }

    private Difficulty processDifficultyLine(String line, Difficulty currentDifficulty) {
        try {
            return Difficulty.valueOf(line.toUpperCase());
        } catch (IllegalArgumentException e) {
            return currentDifficulty;
        }
    }

    private void processTrackLine(String line, List<String> trackLines) {
        trackLines.add(line);
    }

    private void processPlayerLine(String line, List<Player> players) {
        String[] parts = line.split(" ");
        String name = parts[0].replace("'", ""); // Remove quotes
        String type = parts[1];

        if (type.equalsIgnoreCase("HUMAN")) {
            players.add(new HumanPlayer(name));
        } else if (type.equalsIgnoreCase("BOT")) {
            players.add(new BotPlayer(name));
        }
    }

    @Override
    public CellType[][] deriveGrid() {
        if (this.trackLines.isEmpty() || !this.checkValidity()) {
            return null;
        }

        int rows = this.trackLines.size();
        int cols = this.trackLines.getFirst().length();
        CellType[][] grid = new CellType[rows][cols];

        for (int i = 0; i < rows; i++) {
            String line = this.trackLines.get(i);
            for (int j = 0; j < cols; j++) {
                grid[i][j] = CellType.fromChar(line.charAt(j));
                if (grid[i][j] == CellType.START) {
                    startingLine.add(Position.of(i, j));
                }
            }
        }
        return grid;
    }

    @Override
    public Racetrack deriveTrack() {
        if (this.grid == null) {
            return null;
        }
        // Implement Racetrack derivation logic
        return new SimpleRacetrack(this.grid, this.players, this.difficulty, this.startingLine);
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public Racetrack getTrack() {
        return this.track;
    }

    public CellType[][] getGrid() {
        return grid;
    }
}
