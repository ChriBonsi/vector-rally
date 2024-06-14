package it.chribonsi.vector_rally;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CellTypeTest {

    @Test
    void fromChar() {
        assertEquals(CellType.ROAD, CellType.fromChar('-'));
        assertEquals(CellType.OUTSIDE, CellType.fromChar('@'));
        assertEquals(CellType.BORDER, CellType.fromChar('/'));
        assertEquals(CellType.START, CellType.fromChar('~'));
        assertThrows(IllegalArgumentException.class, () -> CellType.fromChar('x'));
    }
}