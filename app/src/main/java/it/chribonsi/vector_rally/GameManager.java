package it.chribonsi.vector_rally;

/**
 * Describes the general structure of a game manager.
 */
public interface GameManager {

    //TODO: probably just a step instead of a method
    /**
     * Selects the number of players for the game.
     *
     * @return the number of players selected
     */
    int selectPlayersNumber(int number);

    /**
     * Selects the players for the game.
     *
     * @param number the number of players to select for the game
     * @return the selected players
     */
    Player[] selectPlayers(int number);

    /**
     * Selects the racetrack for the game.
     *
     * @param schematic the schematic of the racetrack
     * @return the selected racetrack
     */
    Racetrack selectRacetrack(Schematic schematic);

    /**
     * Submits a racetrack schematic to the game manager.
     *
     * @param path the path of the schematic to submit
     * @return the submitted schematic
     */
    Schematic submitSchematic(String path);

    /**
     * Gets the default schematic path for the given schematic name.
     *
     * @param schematicName the name of the schematic
     * @return the default schematic path
     */
    String getDefaultSchematicPath(String schematicName);

    /**
     * Starts a game with the given players.
     *
     * @param players the players to start the game with
     * @return true if the game was started, false otherwise
     */
    boolean startGame(Player[] players);

    /**
     * Stops the game.
     *
     * @return true if the game was stopped, false otherwise
     */
    boolean endGame();
}