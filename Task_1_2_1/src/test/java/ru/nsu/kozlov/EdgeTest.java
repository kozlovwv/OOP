package ru.nsu.kozlov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EdgeTest {

    @Test
    void testEdgeEq() {
        Edge<String, Integer> edge = new Edge<>("A", "B", 10);
        assertTrue(edge.equals(edge));
    }

    @Test
    void testEdgeEq1() {
        Edge<String, Integer> edge = new Edge<>("A", "B", 10);
        assertTrue(!edge.equals("edge"));
    }

    @Test
    void testEdgeEq2() {
        Edge<String, Integer> edge = new Edge<>("A", "B", 10);
        assertTrue(!edge.equals(null));
    }

    @Test
    void testEdgeHash3() {
        Edge<String, Integer> edge = new Edge<>("A", "B", 10);
        Edge<String, Integer> edge2 = new Edge<>("A", "B", 10);
        assertEquals(edge.hashCode(), edge2.hashCode());
    }
}
