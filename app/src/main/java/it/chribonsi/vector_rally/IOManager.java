package it.chribonsi.vector_rally;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        return path;
    }
}
