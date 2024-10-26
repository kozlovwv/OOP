package ru.nsu.kozlov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class AdjacencyListTest {

    @Test
    void testAddVertex() {
        AdjacencyList<String, Integer> graph = new AdjacencyList<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        ArrayList<String> vs = graph.getListOfVertices();

        assertTrue(vs.get(0).equals("A") && vs.get(1).equals("B") && vs.get(2).equals("C"));
    }

    @Test
    void testAddVertex2() {
        AdjacencyList<String, Integer> graph = new AdjacencyList<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        try {
            graph.addVertex("A");
        } catch (ExistingVertexException e) {
            assertTrue(true);
        }
    }

    @Test
    void testRemoveVertex() {
        AdjacencyList<String, Integer> graph = new AdjacencyList<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B", 0);
        graph.addEdge("C", "B", 0);
        graph.addEdge("B", "A", 0);
        graph.addEdge("B", "C", 0);

        graph.removeVertex("B");

        ArrayList<String> vs = graph.getListOfVertices();

        assertTrue(vs.get(0).equals("A") && vs.get(1).equals("C"));
    }

    @Test
    void testRemoveVertex2() {
        AdjacencyList<String, Integer> graph = new AdjacencyList<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        try {
            graph.removeVertex("F");
        } catch (NoSuchVertexException e) {
            assertTrue(true);
        }
    }

    @Test
    void testAddEdge() {
        AdjacencyList<String, Integer> graph = new AdjacencyList<>();

        graph.addVertex("A");
        graph.addVertex("B");

        graph.addEdge("A", "B", 0);

        assertEquals(graph.getEdge(0, 0), new Edge<String, Integer>("A", "B", 0));
    }

    @Test
    void testAddEdge2() {
        AdjacencyList<String, Integer> graph = new AdjacencyList<>();

        graph.addVertex("A");

        try {
            graph.addEdge("B", "A", 0);
        } catch (NoSuchVertexException e) {
            assertTrue(true);
        }
    }

    @Test
    void testAddEdge3() {
        AdjacencyList<String, Integer> graph = new AdjacencyList<>();

        graph.addVertex("A");

        try {
            graph.addEdge("A", "B", 0);
        } catch (NoSuchVertexException e) {
            assertTrue(true);
        }
    }

    @Test
    void testRemoveEdge() {
        AdjacencyList<String, Integer> graph = new AdjacencyList<>();

        graph.addVertex("A");

        try {
            graph.removeEdge("B", "A", 0);
        } catch (NoSuchVertexException e) {
            assertTrue(true);
        }
    }

    @Test
    void testRemoveEdge2() {
        AdjacencyList<String, Integer> graph = new AdjacencyList<>();

        graph.addVertex("A");

        try {
            graph.removeEdge("A", "B", 0);
        } catch (NoSuchVertexException e) {
            assertTrue(true);
        }
    }

    @Test
    void testRemoveEdge3() {
        AdjacencyList<String, Integer> graph = new AdjacencyList<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B", 0);
        graph.addEdge("A", "C", 0);

        boolean c1 = graph.getEdge(0, 0).equals(new Edge<String, Integer>("A", "B", 0));
        graph.removeEdge("A", "B", 0);
        boolean c2 = graph.getEdge(0, 0).equals(new Edge<String, Integer>("A", "C", 0));

        assertTrue(c1 && c2);
    }

    @Test
    void testAdjacencyVertices() {
        AdjacencyList<String, Integer> graph = new AdjacencyList<>();

        try {
            graph.getAdjacentVertices("A");
        } catch (NoSuchVertexException e) {
            assertTrue(true);
        }
    }

    @Test
    void testReadFromFile() {
        AdjacencyList<String, Integer> graph = new AdjacencyList<>();

        Converter<String> vertC = line -> line;
        Converter<Integer> weightC = Integer::parseInt;

        graph.readGraphFromFile("src/test/resources/file.txt", vertC, weightC);

        ArrayList<String> vs = graph.getListOfVertices();

        boolean c1 = vs.get(0).equals("A") && vs.get(1).equals("B") && vs.get(2).equals("C")
                                           && vs.get(3).equals("D") && vs.get(4).equals("E");

        boolean c2 = graph.getEdge(0, 0).equals(new Edge<String, Integer>("A", "B", 10));
        boolean c3 = graph.getEdge(1, 0).equals(new Edge<String, Integer>("B", "A", 3));
        boolean c4 = graph.getEdge(2, 0).equals(new Edge<String, Integer>("C", "E", 5));
        boolean c5 = graph.getEdge(4, 0).equals(new Edge<String, Integer>("E", "D", 7));
        boolean c6 = graph.getEdge(3, 0).equals(new Edge<String, Integer>("D", "A", 999));

        assertTrue(c1 && c2 && c3 && c4 && c5 && c6);
    }

    @Test
    void testToString() {
        AdjacencyList<String, Integer> graph = new AdjacencyList<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B", 0);
        graph.addEdge("C", "B", 0);
        graph.addEdge("B", "A", 0);
        graph.addEdge("B", "C", 0);

        assertEquals("adjacencyList:\n"
                + "[{A, B, 0}]\n"
                + "[{B, A, 0}, {B, C, 0}]\n"
                + "[{C, B, 0}]\n"
                + "totalVertices = 3\n"
                + "listOfVertices = [A, B, C]", graph.toString());
    }
}