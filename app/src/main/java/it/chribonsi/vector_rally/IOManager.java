package it.chribonsi.vector_rally;

import java.util.Scanner;

public class IOManager {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Prompts the user to select a map.
     * @return the path of the selected map
     */
    public String selectMap() {
        System.out.println("\nDo you want to use a default map? [yes/no]");
        String trackPath = scanner.nextLine();
        if (trackPath.equalsIgnoreCase("yes")) {
            System.out.println("\nPlease enter the name of the map: [oval, square, triangle]");
            trackPath = this.getDefaultMapPath(scanner.nextLine().toLowerCase());
        } else if (trackPath.equalsIgnoreCase("no")) {
            System.out.println("\nPlease enter the path of the map you want to use:");
            trackPath = this.checkIfValidPath(scanner.nextLine());
        } else {
            System.out.println("\nInvalid input. Please enter 'yes' or 'no'.");
            trackPath = this.selectMap();
        }
        return trackPath;
    }

    private String checkIfValidPath(String s) {
        return s;
    }

    private String getDefaultMapPath(String s) {
        if (s.equals("oval") || s.equals("square") || s.equals("triangle")) {
            //System.out.println("/default_tracks/" + s + ".txt");
            return "/default_tracks/" + s + ".txt";
        } else return null;
    }

    //TODO
    public int selectPlayersNumber() {
        System.out.println("\nPlease enter the number of players (2-4):");
        int numPlayers = scanner.nextInt();
        return numPlayers;
    }
}
