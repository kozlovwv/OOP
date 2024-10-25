package ru.nsu.kozlov;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Tests!");

        AdjacencyList<String, Integer> adjacencyList = new AdjacencyList<>();

        adjacencyList.addVertex("New-York");
        adjacencyList.addVertex("Los-Angeles");
        adjacencyList.addVertex("California");

        adjacencyList.addEdge("New-York", "California", 10);
        adjacencyList.addEdge("New-York", "Los-Angeles", 10);
        adjacencyList.addEdge("California", "Los-Angeles", 10);
        adjacencyList.addEdge("Los-Angeles", "California", 10);

        try {
            ArrayList<String> sorted = TopologicalSort.topoSort(adjacencyList);
            System.out.println(sorted);
        } catch (CycleFoundException e) {
            System.out.println("something went bad...///");
        }

    }
}