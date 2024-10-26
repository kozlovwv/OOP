package ru.nsu.kozlov;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IncidenceMatrixTest {

    @Test
    void testAddVertex() {
        IncidenceMatrix<String, Integer> graph = new IncidenceMatrix<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        ArrayList<String> vS = graph.getListOfVertices();

        assertTrue(vS.get(0).equals("A") && vS.get(1).equals("B") && vS.get(2).equals("C"));
    }

    @Test
    void testAddVertex2() {
        IncidenceMatrix<String, Integer> graph = new IncidenceMatrix<>();

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
        IncidenceMatrix<String, Integer> graph = new IncidenceMatrix<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B", 0);
        graph.addEdge("C", "B", 0);
        graph.addEdge("B", "A", 0);
        graph.addEdge("B", "C", 0);

        graph.removeVertex("B");

        ArrayList<String> vS = graph.getListOfVertices();

        assertTrue(vS.get(0).equals("A") && vS.get(1).equals("C"));
    }

    @Test
    void testRemoveVertex2() {
        IncidenceMatrix<String, Integer> graph = new IncidenceMatrix<>();

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
        IncidenceMatrix<String, Integer> graph = new IncidenceMatrix<>();

        graph.addVertex("A");
        graph.addVertex("B");

        graph.addEdge("A", "B", 1);

        boolean c1 = graph.getCell(0,0) == -1;
        boolean c2 = graph.getCell(1,0) == 1;

        assertTrue(c1 && c2);
    }

    @Test
    void testAddEdge2() {
        IncidenceMatrix<String, Integer> graph = new IncidenceMatrix<>();

        graph.addVertex("A");

        try {
            graph.addEdge("B", "A", 0);
        } catch (NoSuchVertexException e) {
            assertTrue(true);
        }
    }

    @Test
    void testAddEdge3() {
        IncidenceMatrix<String, Integer> graph = new IncidenceMatrix<>();

        graph.addVertex("A");

        try {
            graph.addEdge("A", "B", 0);
        } catch (NoSuchVertexException e) {
            assertTrue(true);
        }
    }

    @Test
    void testRemoveEdge() {
        IncidenceMatrix<String, Integer> graph = new IncidenceMatrix<>();

        graph.addVertex("A");

        try {
            graph.removeEdge("B", "A", 0);
        } catch (NoSuchEdgeException e) {
            assertTrue(true);
        }
    }

    @Test
    void testRemoveEdge2() {
        IncidenceMatrix<String, Integer> graph = new IncidenceMatrix<>();

        graph.addVertex("A");

        try {
            graph.removeEdge("A", "B", 0);
        } catch (NoSuchEdgeException e) {
            assertTrue(true);
        }
    }

    @Test
    void testRemoveEdge3() {
        IncidenceMatrix<String, Integer> graph = new IncidenceMatrix<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B", 0);
        graph.addEdge("B", "C", 0);
        graph.addEdge("C", "A", 0);

        boolean c1 = (graph.getCell(1, 1) == -1) && (graph.getCell(2, 1) == 1);
        graph.removeEdge("B", "C", 0);
        boolean c2 = (graph.getCell(2, 1) == -1) && (graph.getCell(0, 1) == 1);

        assertTrue(c1 && c2);
    }

    @Test
    void testRemoveEdge4() {
        IncidenceMatrix<String, Integer> graph = new IncidenceMatrix<>();

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
        IncidenceMatrix<String, Integer> graph = new IncidenceMatrix<>();

        try {
            graph.getAdjacentVertices("A");
        } catch (NoSuchVertexException e) {
            assertTrue(true);
        }
    }

    @Test
    void testAdjacencyVertices2() {
        IncidenceMatrix<String, Integer> graph = new IncidenceMatrix<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("F");

        graph.addEdge("A", "B", 0);
        graph.addEdge("C", "B", 0);
        graph.addEdge("B", "A", 0);
        graph.addEdge("B", "C", 0);
        graph.addEdge("B", "F", 0);

        ArrayList<String> vS = graph.getAdjacentVertices("B");
        ArrayList<String> tS = new ArrayList<>();
        tS.add("A");
        tS.add("C");
        tS.add("F");

        assertEquals(vS, tS);
    }

    @Test
    void testReadFromFile() {
        IncidenceMatrix<String, Integer> graph = new IncidenceMatrix<>();

        Converter<String> vertC = line -> line;
        Converter<Integer> weightC = Integer::parseInt;

        graph.readGraphFromFile("src/test/resources/file.txt", vertC, weightC);

        ArrayList<String> vS = graph.getListOfVertices();

        boolean c1 = vS.get(0).equals("A") && vS.get(1).equals("B") && vS.get(2).equals("C")
                && vS.get(3).equals("D") && vS.get(4).equals("E");

        boolean c2 = graph.getEdge(0).equals(new Edge<>("A", "B", 10));
        boolean c3 = graph.getEdge(1).equals(new Edge<>("B", "A", 3));
        boolean c4 = graph.getEdge(2).equals(new Edge<>("C", "E", 5));
        boolean c5 = graph.getEdge(3).equals(new Edge<>("E", "D", 7));
        boolean c6 = graph.getEdge(4).equals(new Edge<>("D", "A", 999));

        assertTrue(c1 && c2 && c3 && c4 && c5 && c6);
    }

    @Test
    void testToString() {
        IncidenceMatrix<String, Integer> graph = new IncidenceMatrix<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B", 0);
        graph.addEdge("C", "B", 0);
        graph.addEdge("B", "A", 0);
        graph.addEdge("B", "C", 0);

        assertEquals("IncidenceMatrix:\n" +
                "[-1, 0, 1, 0]\n" +
                "[1, 1, -1, -1]\n" +
                "[0, -1, 0, 1]\n" +
                "totalVertices = 3\n" +
                "listOfVertices = [A, B, C]\n" +
                "totalEdges = 4\n" +
                "listOfVertices = [{A, B, 0}, {C, B, 0}, {B, A, 0}, {B, C, 0}]", graph.toString());
    }
}