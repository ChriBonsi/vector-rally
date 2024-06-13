package it.chribonsi.vector_rally;

public class BotPlayer implements Player {
    private final String name;

    public BotPlayer(String name) {
        this.name = name;
    }

    @Override
    public int[] decideNextMove() {
        return new int[0];
    }

    @Override
    public String getName() {
        return this.name;
    }
}
