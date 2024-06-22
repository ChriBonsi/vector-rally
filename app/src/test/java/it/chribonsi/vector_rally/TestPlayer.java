package it.chribonsi.vector_rally;

/**
 * A player used for testing that always returns the same move until you change it.
 */
public class TestPlayer implements Player {

    private Movement move;

    public TestPlayer(Movement move) {
        if (move != Movement.MIDDLE_C) {
            this.move = move;
        } else {
            this.move = Movement.MIDDLE_R;
        }
    }

    public TestPlayer() {
        this.move = Movement.MIDDLE_R;
    }

    @Override
    public Movement decideNextMove(SimpleRacetrack simpleRacetrack) {
        return this.move;
    }

    @Override
    public String getName() {
        return "TestPlayer";
    }

    public void setMove(Movement move) {
        this.move = move;
    }
}
