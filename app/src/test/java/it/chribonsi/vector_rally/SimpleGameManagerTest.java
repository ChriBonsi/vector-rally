package it.chribonsi.vector_rally;

import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

class SimpleGameManagerTest {

    @Test
    void testStartRace() {
        //TODO

        SimpleGameManager gameManager = new SimpleGameManager(Paths.get("src/test/resources/schema_test.txt"));
        //assertTrue(gameManager.startRace());
    }
}