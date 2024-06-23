package it.chribonsi.vector_rally;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class TXTSchematicTest {

    @Test
    void testParseSchematic() {
        Path testFilePath = Paths.get("src/test/resources/schema_test.txt");
        TXTSchematic schematic = new TXTSchematic(testFilePath);

        assertEquals(3, schematic.getPlayers().size());

        schematic.getPlayers().forEach(player -> {
            assertNotNull(player);
            assertTrue(player instanceof EasyBotPlayer || player instanceof MediumBotPlayer
                    || player instanceof HumanPlayer);
        });

        assertNotNull(schematic.getGrid());
        assertNotNull(schematic.getTrack());
    }

    @Test
    void testCheckValidity() {
        Path validFilePath = Paths.get("src/test/resources/schema_test.txt");
        Path invalidFilePath = Paths.get("src/test/resources/invalid_schema.txt");

        TXTSchematic validSchematic = new TXTSchematic(validFilePath);
        TXTSchematic invalidSchematic = new TXTSchematic(invalidFilePath);

        assertTrue(validSchematic::checkValidity);
        assertFalse(invalidSchematic::checkValidity);
    }

    @Test
    void testDeriveGrid() {
        Path testFilePath = Paths.get("src/test/resources/schema_test.txt");
        TXTSchematic schematic = new TXTSchematic(testFilePath);

        CellType[][] grid = schematic.getGrid();
        assertNotNull(grid);
        assertEquals(CellType.OUTSIDE, grid[0][0]);
        assertEquals(CellType.ROAD, grid[2][10]);
        assertEquals(CellType.WALL, grid[2][9]);
        assertEquals(CellType.START, grid[14][10]);
        assertEquals(CellType.FINISH, grid[18][8]);
    }

    @Test
    void testDeriveTrack() {
        Path testFilePath = Paths.get("src/test/resources/schema_test.txt");
        TXTSchematic schematic = new TXTSchematic(testFilePath);

        SimpleRacetrack track = schematic.getTrack();
        assertNotNull(track);
        assertEquals(3, track.getPlayers().size());
    }
}
