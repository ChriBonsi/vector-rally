package it.chribonsi.vector_rally;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SimpleRacetrackTest {
    static SimpleRacetrack r;

    @BeforeAll
    static void setUp() {
        TXTSchematic s = new TXTSchematic(Paths.get("src/test/resources/schema_test.txt"));
        r = s.getTrack();
    }

    @Test
    void testAddPlayerToStartingLine() {
        r.getRacePositions().forEach((k, v) -> assertNotNull(v));
        List<Position> l = r.getRacePositions().values().stream().toList();
        Set<Position> set = new HashSet<>(l);

        assertEquals(l.size(), set.size());
    }

    @Test
    void testMovePlayer() {
        //TODO
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Test
    void testGetNeighbours() {
        //TODO
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Test
    void testIsCellFree() {
        assertTrue(r.isCellFree(Position.of(0, 0)));
        r.getRacePositions().forEach((k, v) -> Assertions.assertFalse(r.isCellFree(v)));
    }

    @Test
    void testGetCell() {
        for (int i = 0; i < r.getGrid().length; i++) {
            for (int j = 0; j < r.getGrid()[i].length; j++) {
                assertNotNull(r.getCell(Position.of(i, j)));
                assertEquals(r.getCell(Position.of(i, j)), r.getGrid()[i][j]);
            }
        }
    }


    @Test
    void testGetPlayer() {
        assertEquals(r.getPlayer(Position.of(0, 0)), Optional.empty());
        r.getRacePositions().forEach((k, v) -> assertEquals(r.getPlayer(v), Optional.of(k)));
    }
}