package it.chribonsi.vector_rally;

public class SimpleGameManager {
    private final int playersNumber;
    private final Player[] players;
    private final Racetrack racetrack;

    public SimpleGameManager(int playersNumber, Player[] players, Racetrack racetrack) {
        this.playersNumber = playersNumber;
        this.players = players;
        this.racetrack = racetrack;
    }
}
