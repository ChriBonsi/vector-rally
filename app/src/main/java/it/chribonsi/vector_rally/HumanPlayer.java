package it.chribonsi.vector_rally;

public class HumanPlayer implements Player {
    private final String name;

    public HumanPlayer(String name) {
        this.name = name;
    }

    @Override
    public Movement decideNextMove() {
        //TODO
        return null;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
