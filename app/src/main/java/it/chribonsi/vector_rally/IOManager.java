package it.chribonsi.vector_rally;

import java.util.Scanner;

public class IOManager {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Prompts the user to select a map.
     *
     * @return the path of the selected map
     */
    public String selectMapPath() {
        String trackPath = null;
        while (trackPath == null) {
            System.out.println("\nDo you want to use a default map? [yes/no]");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("yes")) {
                System.out.println("\nPlease enter the name of the map: [oval, square, triangle]");
                trackPath = this.getDefaultMapPath(scanner.nextLine().toLowerCase());
            } else if (response.equalsIgnoreCase("no")) {
                System.out.println("\nPlease enter the path of the map you want to use:");
                trackPath = this.checkIfValidPath(scanner.nextLine());
            } else {
                System.out.println("\nInvalid input. Please enter 'yes' or 'no'.");
            }
        }
        
        return trackPath;
    }

    private String checkIfValidPath(String s) {
        if (s.length() >= 5 && s.endsWith(".txt")) {
            return s;
        }
        return this.selectMapPath();
    }

    private String getDefaultMapPath(String s) {
        if (s.equals("oval") || s.equals("square") || s.equals("triangle")) {
            //System.out.println("/default_tracks/" + s + ".txt");
            return "/default_tracks/" + s + ".txt";
        } else return null;
    }

    public int selectPlayersNumber() {
        int playersNumber = 0;
        while (playersNumber < 2 || playersNumber > 4) {
            System.out.println("\nPlease enter the number of players (2-4):");
            playersNumber = scanner.nextInt();
        }
        return playersNumber;
    }

    public Player[] selectPlayers(int playersNumber) {
        return null;
    }
}
