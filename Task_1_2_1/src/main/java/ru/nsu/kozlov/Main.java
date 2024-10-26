package ru.nsu.kozlov;

/**
 * main class.
 */
public class Main {
    /**
     * main method.
     * @param args some arguments
     */
    public static void main(String[] args) {
        IncidenceMatrix<String, Integer> graph = new IncidenceMatrix<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B", 0);
        graph.addEdge("C", "B", 0);
        graph.addEdge("B", "A", 0);
        graph.addEdge("B", "C", 0);

        System.out.println(graph.toString());
    }
}