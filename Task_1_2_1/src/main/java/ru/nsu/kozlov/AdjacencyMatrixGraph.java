package ru.nsu.kozlov;

import java.util.ArrayList;

public class AdjacencyMatrixGraph implements Graph {

    ArrayList<ArrayList<Integer>> adjacencyMatrix;
    ArrayList<String> listOfVertices;
    int totalVertices;

    public AdjacencyMatrixGraph(int n) {
        adjacencyMatrix = new ArrayList<ArrayList<Integer>>();
        totalVertices = n;
        for (int i = 0; i < n; i++) {
            adjacencyMatrix.add(new ArrayList<Integer>());
            for (int j = 0; j < n; j++) {
                adjacencyMatrix.get(i).add(0);
            }
        }
    }

    @Override
    public void addEdge(String vertexFrom, String vertexTo, int weight) {
        int indexFrom = listOfVertices.indexOf(vertexFrom);
        int indexTo = listOfVertices.indexOf(vertexTo);
        int current = adjacencyMatrix.get(indexFrom).get(indexTo);
        adjacencyMatrix.get(indexTo).set(indexTo, current + 1);
    }

    @Override
    public void addVertex(String vertexName) {
        //checking for existence
        listOfVertices.add(vertexName);
        for (int i = 0; i < totalVertices; i++) {
            adjacencyMatrix.get(i).add(0);
        }
        adjacencyMatrix.add(new ArrayList<Integer>());
        for (int i = 0; i <= totalVertices; i++) {
            adjacencyMatrix.get(totalVertices).add(0);
        }
        totalVertices++;
    }

    @Override
    public ArrayList<String> getAdjacentVertices(String vertexName) {
        ArrayList<String> adjacentVertices = new ArrayList<String>();
        int indexV = listOfVertices.indexOf(vertexName);

//        for (int i = 0; i < totalVertices; i++) {
//            adjacencyMatrix.get(indexV).get(i);
//        }

        return adjacentVertices;
    }

    @Override
    public void readGraphFromFile() {

    }

    @Override
    public void removeEdge() {

    }

    @Override
    public void removeVertex() {

    }
}
