package it.chribonsi.vector_rally;

public class SimpleGameManager implements GameManager {
    private final int playersNumber;
    private final Player[] players;
    private final Racetrack racetrack;

    public SimpleGameManager() {
        this.playersNumber = 0;
        this.players = null;
        this.racetrack = null;
    }

//    public SimpleGameManager(IOManager ioManager) {
//        this.playersNumber = ioManager.selectPlayersNumber();
//        this.players = this.generatePlayers(this.playersNumber, ioManager.selectHumanPlayers(this.playersNumber));
//        TxtSchematic schematic = new TxtSchematic(ioManager.selectMapPath());
//        this.racetrack = schematic.getTrack();
//    }

    @Override
    public boolean startRace() {
        System.out.println("Starting race with " + this.playersNumber + " players on the track.");
        return false;
    }

    @Override
    public boolean stopRace() {
        return false;
    }

    @Override
    public Player[] generatePlayers(int totalPlayers, int humanPlayers) {
        Player[] players = new Player[totalPlayers];
        for (int i = 0; i < humanPlayers; i++) {
            players[i] = new HumanPlayer();
        }
        for (int i = humanPlayers; i < totalPlayers; i++) {
            players[i] = new BotPlayer();
        }
        return players;
    }
}
