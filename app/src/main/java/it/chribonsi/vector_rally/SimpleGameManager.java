package it.chribonsi.vector_rally;

public class SimpleGameManager implements GameManager{
    private final int playersNumber;
    private final Player[] players;
    private final Racetrack racetrack;

    public SimpleGameManager(int playersNumber, Player[] players, Racetrack racetrack) {
        this.playersNumber = playersNumber;
        this.players = players;
        this.racetrack = racetrack;
    }

    public SimpleGameManager(IOManager ioManager) {
        System.out.println("asking players number");
        this.playersNumber = ioManager.selectPlayersNumber();

        System.out.println("asking players");
        this.players = ioManager.selectPlayers(this.playersNumber);

        System.out.println("asking track");
        TxtSchematic schematic = new TxtSchematic(ioManager.selectMapPath());
        this.racetrack = schematic.getTrack();
    }

    @Override
    public boolean startRace() {
        System.out.println("Starting race with " + this.playersNumber + " players on the track.");
        return false;
    }

    @Override
    public boolean stopRace() {
        return false;
    }
}
