package it.chribonsi.vector_rally;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;

public class IOManager {
    private final Scanner scanner;

    //Default constructor
    public IOManager() {
        this.scanner = new Scanner(System.in);
    }

    //Constructor meant for testing
    public IOManager(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public static void printMap(CellType[][] grid, Map<Player, Position> playersPositions) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                boolean isPlayer = false;
                for (Map.Entry<Player, Position> entry : playersPositions.entrySet()) {
                    if (entry.getValue().equals(Position.of(i, j))) {
                        System.out.print(entry.getKey().getName().charAt(0));
                        isPlayer = true;
                        break;
                    }
                }
                if (!isPlayer) {
                    System.out.print(grid[i][j].toString());
                }
            }
            System.out.println();
        }
    }

    /**
     * Prompts the user to input a path to select a map.
     *
     * @return the path of the selected map
     */
    public Path selectSchemaFilePath() {
        Path path = null;
        while (path == null || !path.toString().endsWith(".txt") || !Files.exists(path)) {
            System.out.println("Please enter the path of the schema file (It should be a .txt file):");
            path = Paths.get(scanner.nextLine());
            if (!Files.exists(path)) {
                System.out.println("Invalid path. Please enter a valid path ending with '.txt'.");
            }
        }
        this.scanner.close();
        return path;
    }
}
