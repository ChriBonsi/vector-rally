package it.chribonsi.vector_rally;

/**
 * Describes the general structure of a game manager.
 */
public interface GameManager {

    /**
     * Starts the selection process and then the race.
     */
    //public void startGame();

    /**
     * Selects the players for the game.
     *
     * @param number the number of players to select for the game
     * @return the selected players
     */
    //Player[] selectPlayers(int number);

    /**
     * Selects the racetrack for the game.
     *
     * @param schematic the schematic of the racetrack
     * @return the selected racetrack
     */
    //Racetrack selectRacetrack(Schematic schematic);

    /**
     * Submits a racetrack schematic to the game manager.
     *
     * @param path the path of the schematic to submit
     * @return the submitted schematic
     */
    //Schematic submitSchematic(String path);

    /**
     * Gets the default schematic path for the given schematic name.
     *
     * @param schematicName the name of the schematic
     * @return the default schematic path
     */
    //String getDefaultSchematicPath(String schematicName);

    /**
     * Starts a race with the given players and racetrack.
     *
     * @return true if the race was started, false otherwise
     */
    boolean startRace();

    /**
     * Stops the game.
     *
     * @return true if the game was stopped, false otherwise
     */
    boolean stopRace();

    /**
     * Generates the players for the game.
     *
     * @param totalPlayers the number of players to generate
     * @param humanPlayers the number of human players to generate
     * @return the generated players
     */
    Player[] generatePlayers(int totalPlayers, int humanPlayers);
}
