package ru.nsu.kozlov;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ToposortTest {
    @Test
    void testToposortNoCycle() {
        AdjacencyList<String, Integer> graph = new AdjacencyList<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");

        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "C", 1);
        graph.addEdge("B", "D", 1);
        graph.addEdge("C", "F", 1);
        graph.addEdge("D", "E", 1);
        graph.addEdge("E", "F", 1);
        graph.addEdge("A", "D", 1);
        graph.addEdge("B", "E", 1);

        ArrayList<String> sorted = TopologicalSort.topoSort(graph);

        if (sorted.get(0).equals("A") && sorted.get(1).equals("B")
            && sorted.get(2).equals("D") && sorted.get(3).equals("E")
                && sorted.get(4).equals("C") && sorted.get(5).equals("F")) {
            assertTrue(true);
        }
    }

    @Test
    void testToposortWithCycle() {
        AdjacencyList<String, Integer> graph = new AdjacencyList<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");

        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "C", 1);
        graph.addEdge("B", "D", 1);
        graph.addEdge("C", "F", 1);
        graph.addEdge("D", "E", 1);
        graph.addEdge("E", "F", 1);
        graph.addEdge("A", "D", 1);
        graph.addEdge("F", "A", 1);

        try {
            ArrayList<String> sorted = TopologicalSort.topoSort(graph);
        } catch (CycleFoundException e) {
            assertTrue(true);
        }
    }
}