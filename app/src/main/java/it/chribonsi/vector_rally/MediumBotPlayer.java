package it.chribonsi.vector_rally;

public class MediumBotPlayer extends BotPlayer {

    public MediumBotPlayer(String name) {
        super(name);
    }

    @Override
    public Movement decideNextMove(SimpleRacetrack track) {
        Vector lastMove = track.getLastMove(this);
        //se l'ultimo movimento è (0,0) allora si è schiantato o sta partendo e bisogna scegliere una direzione
        if (lastMove.equals(Vector.of(0, 0))) {
            return this.chooseDirection();
        } else if (this.isAccelerating(lastMove, 4)) {
            return this.accelerate(lastMove);
        } else {
            return this.goSteady();
        }
    }
}
