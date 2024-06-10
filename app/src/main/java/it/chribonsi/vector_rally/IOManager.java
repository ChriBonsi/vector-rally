package it.chribonsi.vector_rally;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class IOManager {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Prompts the user to input a path to select a map.
     *
     * @return the path of the selected map
     */
    public Path selectMapPath() {
        Path path = null;
        while (path == null || !Files.exists(path)) {
            System.out.println("Please enter the path of the map file (It should be a .txt file):");
            path = Paths.get(scanner.nextLine());
            if (!Files.exists(path)) {
                System.out.println("Invalid path. Please enter a valid path ending with '.txt'.");
            }
        }
        return path;
    }
}
