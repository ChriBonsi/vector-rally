package it.chribonsi.vector_rally;

public class MediumBotPlayer extends BotPlayer {

    public MediumBotPlayer(String name) {
        super(name);
    }

    @Override
    public Movement decideNextMove(SimpleRacetrack track) {
        Vector lastMove = track.getLastMove(this);
        if (lastMove.equals(Vector.of(0, 0))) {
            return this.chooseDirection();
        } else if (this.isAccelerating(lastMove, 4)) {
            return this.accelerate();
        } else {
            return this.goSteady();
        }
    }
}
