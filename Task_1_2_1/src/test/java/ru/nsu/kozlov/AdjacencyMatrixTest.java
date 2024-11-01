package ru.nsu.kozlov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class AdjacencyMatrixTest {

    @Test
    void testAddVertex() {
        AdjacencyMatrix<String, Integer> graph = new AdjacencyMatrix<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        ArrayList<String> vs = graph.getListOfVertices();

        assertTrue(vs.get(0).equals("A") && vs.get(1).equals("B") && vs.get(2).equals("C"));
    }

    @Test
    void testAddVertex2() {
        AdjacencyMatrix<String, Integer> graph = new AdjacencyMatrix<>();

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
        AdjacencyMatrix<String, Integer> graph = new AdjacencyMatrix<>();

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
        AdjacencyMatrix<String, Integer> graph = new AdjacencyMatrix<>();

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
        AdjacencyMatrix<String, Integer> graph = new AdjacencyMatrix<>();

        graph.addVertex("A");
        graph.addVertex("B");

        graph.addEdge("A", "B", 1);

        assertEquals(1, graph.getCell(0, 1));
    }

    @Test
    void testAddEdge2() {
        AdjacencyMatrix<String, Integer> graph = new AdjacencyMatrix<>();

        graph.addVertex("A");

        try {
            graph.addEdge("B", "A", 0);
        } catch (NoSuchVertexException e) {
            assertTrue(true);
        }
    }

    @Test
    void testAddEdge3() {
        AdjacencyMatrix<String, Integer> graph = new AdjacencyMatrix<>();

        graph.addVertex("A");

        try {
            graph.addEdge("A", "B", 0);
        } catch (NoSuchVertexException e) {
            assertTrue(true);
        }
    }

    @Test
    void testRemoveEdge() {
        AdjacencyMatrix<String, Integer> graph = new AdjacencyMatrix<>();

        graph.addVertex("A");

        try {
            graph.removeEdge("B", "A", 0);
        } catch (NoSuchVertexException e) {
            assertTrue(true);
        }
    }

    @Test
    void testRemoveEdge2() {
        AdjacencyMatrix<String, Integer> graph = new AdjacencyMatrix<>();

        graph.addVertex("A");

        try {
            graph.removeEdge("A", "B", 0);
        } catch (NoSuchVertexException e) {
            assertTrue(true);
        }
    }

    @Test
    void testRemoveEdge3() {
        AdjacencyMatrix<String, Integer> graph = new AdjacencyMatrix<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B", 0);
        graph.addEdge("A", "B", 0);

        boolean c1 = graph.getCell(0, 1) == 2;
        graph.removeEdge("A", "B", 0);
        boolean c2 = graph.getCell(0, 1) == 1;

        assertTrue(c1 && c2);
    }

    @Test
    void testRemoveEdge4() {
        AdjacencyMatrix<String, Integer> graph = new AdjacencyMatrix<>();

        graph.addVertex("A");
        graph.addVertex("B");

        try {
            graph.removeEdge("A", "B", 0);
        } catch (NoSuchEdgeException e) {
            assertTrue(true);
        }
    }

    @Test
    void testAdjacencyVertices() {
        AdjacencyMatrix<String, Integer> graph = new AdjacencyMatrix<>();

        try {
            graph.getAdjacentVertices("A");
        } catch (NoSuchVertexException e) {
            assertTrue(true);
        }
    }

    @Test
    void testAdjacencyVertices2() {
        AdjacencyMatrix<String, Integer> graph = new AdjacencyMatrix<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("F");

        graph.addEdge("A", "B", 0);
        graph.addEdge("C", "B", 0);
        graph.addEdge("B", "A", 0);
        graph.addEdge("B", "C", 0);
        graph.addEdge("B", "F", 0);

        ArrayList<String> ts = new ArrayList<>();
        ts.add("A");
        ts.add("C");
        ts.add("F");

        ArrayList<String> vs = graph.getAdjacentVertices("B");

        assertEquals(vs, ts);
    }

    @Test
    void testReadFromFile() {
        AdjacencyMatrix<String, Integer> graph = new AdjacencyMatrix<>();

        Converter<String> vertC = line -> line;
        Converter<Integer> weightC = Integer::parseInt;

        graph.readGraphFromFile("src/test/resources/file.txt", vertC, weightC);

        ArrayList<String> vs = graph.getListOfVertices();

        boolean c1 = vs.get(0).equals("A") && vs.get(1).equals("B") && vs.get(2).equals("C")
                && vs.get(3).equals("D") && vs.get(4).equals("E");

        boolean c2 = graph.getCell(0, 1) == 1;
        boolean c3 = graph.getCell(1, 0) == 1;
        boolean c4 = graph.getCell(2, 4) == 1;
        boolean c5 = graph.getCell(4, 3) == 1;
        boolean c6 = graph.getCell(3, 0) == 1;;

        assertTrue(c1 && c2 && c3 && c4 && c5 && c6);
    }

    @Test
    void testToString() {
        AdjacencyMatrix<String, Integer> graph = new AdjacencyMatrix<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B", 0);
        graph.addEdge("C", "B", 0);
        graph.addEdge("B", "A", 0);
        graph.addEdge("B", "C", 0);

        assertEquals("adjacencyMatrix:\n"
                + "[0, 1, 0]\n"
                + "[1, 0, 1]\n"
                + "[0, 1, 0]\n"
                + "listOfVertices = [A, B, C]\n"
                + "totalVertices = 3", graph.toString());
    }
}