package ru.nsu.kozlov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class CellTest {
    @Test
    void testEquals() {
        Cell<String, Integer> cell = new Cell<>("qwe", 123);
        assertEquals(cell, cell);
    }

    @Test
    void testEquals1() {
        Cell<String, Integer> cell = new Cell<>("qwe", 123);
        assertNotEquals(cell, null);
    }

    @Test
    void testEquals2() {
        Cell<String, Integer> cell = new Cell<>("qwe", 123);
        assertNotEquals(cell, 13);
    }

    @Test
    void testEquals3() {
        Cell<String, Integer> cell = new Cell<>("qwe", 123);
        Cell<String, Integer> cell2 = new Cell<>("qwe", 123);
        assertEquals(cell, cell2);
    }
}