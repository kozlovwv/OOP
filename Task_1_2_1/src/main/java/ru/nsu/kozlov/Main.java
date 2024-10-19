package ru.nsu.kozlov;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Tests!");

        AdjacencyMatrixGraph adjacencyMatrix = new AdjacencyMatrixGraph();

        adjacencyMatrix.addVertex("New-York");
        adjacencyMatrix.addVertex("Los-Angeles");
        adjacencyMatrix.addVertex("California");

        adjacencyMatrix.addEdge("New-York", "California", 10);
        adjacencyMatrix.addEdge("New-York", "Los-Angeles", 10);

        ArrayList<String> adjV = adjacencyMatrix.getAdjacentVertices("New-York");

        for (String v : adjV) {
            System.out.println(v);
        }

        System.out.println();

        adjacencyMatrix.printAll();

        adjacencyMatrix.removeVertex("Los-Angeles");
        adjacencyMatrix.removeEdge("New-York", "California", 10);

        adjacencyMatrix.printAll();
    }
}