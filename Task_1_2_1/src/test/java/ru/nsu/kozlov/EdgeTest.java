package ru.nsu.kozlov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
