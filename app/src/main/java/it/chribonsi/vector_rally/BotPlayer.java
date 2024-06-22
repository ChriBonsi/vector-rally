package it.chribonsi.vector_rally;

public abstract class BotPlayer implements Player {
    private final String name;

    public BotPlayer(String name) {
        this.name = name;
    }

    @Override
    public abstract Movement decideNextMove(SimpleRacetrack simpleRacetrack);

    @Override
    public String getName() {
        return this.name;
    }
}
