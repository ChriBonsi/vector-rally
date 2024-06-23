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
    private final SimpleRacetrack track;
    private final List<String> trackLines = new ArrayList<>();
    private final List<Position> startingLine = new ArrayList<>();
    private final List<Position> finishLine = new ArrayList<>();

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
                    case '\'' -> this.processPlayerLine(line);
                    case '@', '/', '~', '-', '#' -> this.processTrackLine(line, trackLines);
                    default -> System.out.println("Invalid line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    @Override
    public boolean checkValidity() {
        boolean isValid = !players.isEmpty() && gridCheck();
        if (!isValid) {
            System.out.println("Validation failed: Players list is empty or grid checks failed.");
        }
        return isValid;
    }

    private boolean gridCheck() {
        boolean isValid = dimensionCheck() && linesCheck() && borderCheck();
        if (!isValid) {
            System.out.println("Grid check failed: one or more grid validation conditions are not met.");
        }
        return isValid;
    }

    private boolean borderCheck() {
        boolean isFirstRowValid = this.trackLines.getFirst().chars().allMatch(c -> c == '/' || c == '@');
        boolean isLastRowValid = this.trackLines.getLast().chars().allMatch(c -> c == '/' || c == '@');

        boolean isFirstAndLastCharsValid = this.trackLines.stream().allMatch(line -> (line.charAt(0) == '/' || line.charAt(0) == '@') && (line.charAt(line.length() - 1) == '/' || line.charAt(line.length() - 1) == '@'));

        boolean isValid = isFirstRowValid && isLastRowValid && isFirstAndLastCharsValid;
        if (!isValid) {
            System.out.println("Border check failed: Borders do not meet required conditions.");
        }
        return isValid;
    }

    private boolean linesCheck() {
        boolean isValid = this.startingLine.size() >= 4 && this.finishLine.size() >= 2;
        if (!isValid) {
            System.out.println("Lines check failed: Starting line or finish line does not meet the required size.");
        }
        return isValid;
    }

    private boolean dimensionCheck() {
        boolean isValid = this.trackLines.stream().allMatch(line -> line.length() == this.trackLines.getFirst().length() && line.length() > 10);
        if (!isValid) {
            System.out.println("Dimension check failed: One or more lines do not meet the required length or minimum size.");
        }
        return isValid;
    }

    private void processTrackLine(String line, List<String> trackLines) {
        trackLines.add(line);
    }

    private void processPlayerLine(String line) {
        String[] parts = line.split(" ");
        String name = parts[0].replace("'", ""); // Remove quotes
        String type = parts[1];

        this.selectPlayerType(type.toUpperCase(), name);
    }

    private void selectPlayerType(String line, String name) {
        switch (line) {
            case "HUMAN" -> players.add(new HumanPlayer(name));
            case "EASY" -> players.add(new EasyBotPlayer(name));
            case "MEDIUM" -> players.add(new MediumBotPlayer(name));
            case "HARD" -> players.add(new HardBotPlayer(name));
            default -> this.selectPlayerType(Difficulty.getRandomDifficulty().toString(), name);
        }
    }

    @Override
    public CellType[][] deriveGrid() {
        if (this.trackLines.isEmpty()) {
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
                } else if (grid[i][j] == CellType.FINISH) {
                    finishLine.add(Position.of(i, j));
                }
            }
        }
        return grid;
    }

    @Override
    public SimpleRacetrack deriveTrack() {
        if (this.grid == null) {
            return null;
        }
        // Implement Racetrack derivation logic
        return new SimpleRacetrack(this.grid, this.players, this.startingLine);
    }

    // Getters
    public List<Player> getPlayers() {
        return this.players;
    }

    public SimpleRacetrack getTrack() {
        return this.track;
    }

    public CellType[][] getGrid() {
        return grid;
    }
}
