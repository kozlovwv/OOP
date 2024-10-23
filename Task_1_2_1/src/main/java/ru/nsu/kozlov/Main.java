package ru.nsu.kozlov;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Tests!");

        AdjacencyMatrixGraph<String, Integer> adjacencyMatrix = new AdjacencyMatrixGraph<>();

        adjacencyMatrix.addVertex("New-York");
        adjacencyMatrix.addVertex("Los-Angeles");
        adjacencyMatrix.addVertex("California");

        adjacencyMatrix.addEdge("New-York", "California", 10);
        adjacencyMatrix.addEdge("New-York", "Los-Angeles", 10);

        AdjacencyMatrixGraph<String, Integer> adjacencyMatrix2 = new AdjacencyMatrixGraph<>();

        adjacencyMatrix2.addVertex("New-York");
        adjacencyMatrix2.addVertex("Los-Angeles");
        adjacencyMatrix2.addVertex("California");

        adjacencyMatrix2.addEdge("New-York", "California", 10);
        adjacencyMatrix2.addEdge("New-York", "Los-Angeles", 10);

        String str = adjacencyMatrix.toString();

        System.out.println(str);
    }
}