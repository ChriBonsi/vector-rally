package it.chribonsi.vector_rally;

public class EasyBotPlayer extends BotPlayer {

    public EasyBotPlayer(String name) {
        super(name);
    }

    @Override
    public Movement decideNextMove(SimpleRacetrack simpleRacetrack) {
        return Movement.getRandomMovement(Movement.VALUES);
    }
}
