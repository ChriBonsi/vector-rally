package it.chribonsi.vector_rally;

public class RandomBotPlayer extends BotPlayer {

    public RandomBotPlayer(String name) {
        super(name);
    }

    @Override
    public Movement decideNextMove() {
        return Movement.getRandomMovement();
    }
}
