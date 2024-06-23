package it.chribonsi.vector_rally;

/**
 * Describes a general structure for a player.
 */
public interface Player {

    Movement decideNextMove(SimpleRacetrack simpleRacetrack);

    /**
     * Returns the name given to the Player.
     *
     * @return the name
     */
    String name();

    /**
     * Checks if the player is accelerating.
     * The player is considered to be accelerating if the absolute value of the dx or dy of the last move
     * is greater than the acceleration threshold.
     *
     * @param lastMove              the last move of the player
     * @param accelerationThreshold the threshold for the acceleration
     * @return true if the player is accelerating, false otherwise
     */
    default boolean isAccelerating(Vector lastMove, int accelerationThreshold) {
        return Math.abs(lastMove.getDx()) > accelerationThreshold || Math.abs(lastMove.getDy()) > accelerationThreshold;
    }

    /**
     * Returns the movement to use when going at constant speed.
     *
     * @return Movement.MIDDLE_C
     */
    default Movement goSteady() {
        return Movement.MIDDLE_C;
    }

    default Movement accelerate() {
        return Movement.getRandomMovement(Movement.TOP_C, Movement.TOP_R, Movement.MIDDLE_R);
    }

    default Movement chooseDirection() {
        Movement randomMovement = Movement.getRandomMovement();
        while (randomMovement == Movement.MIDDLE_C) {
            randomMovement = Movement.getRandomMovement();
        }
        return randomMovement;
    }
}
