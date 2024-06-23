package it.chribonsi.vector_rally;

import java.util.Random;

public enum Difficulty {
    EASY, MEDIUM;

    private static final Random RANDOMIZER = new Random();
    private static final Difficulty[] VALUES = values();

    public static Difficulty getRandomDifficulty() {
        return VALUES[RANDOMIZER.nextInt(VALUES.length)];
    }
}
