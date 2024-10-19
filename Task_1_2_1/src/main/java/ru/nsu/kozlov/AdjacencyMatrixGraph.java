package ru.nsu.kozlov;

import java.io.File;
import java.util.ArrayList;

public class AdjacencyMatrixGraph implements Graph {

    ArrayList<ArrayList<Integer>> adjacencyMatrix;
    ArrayList<String> listOfVertices;
    int totalVertices;

    public AdjacencyMatrixGraph() {
        listOfVertices = new ArrayList<String>();
        adjacencyMatrix = new ArrayList<ArrayList<Integer>>();
        totalVertices = 0;
    }

    public void printAll() {
        boolean isEmpty = true;
        for (String vFrom : listOfVertices) {
            for (String vTo : listOfVertices) {
                int count = adjacencyMatrix.get(listOfVertices.indexOf(vFrom)).get(listOfVertices.indexOf(vTo));
                if (count > 0) {
                    System.out.println(vFrom + " " + vTo + " " + count);
                    isEmpty = false;
                }
            }
        }

        if (isEmpty) {
            System.out.println("No edges!");
        }

        System.out.println();
    }

    @Override
    public void addEdge(String vertexFrom, String vertexTo, int weight) {
        int indexFrom = listOfVertices.indexOf(vertexFrom);
        if (indexFrom == -1) {
            System.out.println("No such vertex: " + vertexFrom + "!");
            return;
        }
        int indexTo = listOfVertices.indexOf(vertexTo);
        if (indexTo == -1) {
            System.out.println("No such vertex: " + vertexTo + "!");
            return;
        }
        int current = adjacencyMatrix.get(indexFrom).get(indexTo);
        adjacencyMatrix.get(indexFrom).set(indexTo, current + 1);
    }

    @Override
    public void addVertex(String vertexName) {
        if (listOfVertices.contains(vertexName)) {
            System.out.println("This vertex is already exists!");
            return;
        }
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
        if (!listOfVertices.contains(vertexName)) {
            System.out.println("No such vertex: " + vertexName + "!");
            return null;
        }

        ArrayList<String> adjacentVertices = new ArrayList<String>();
        int indexV = listOfVertices.indexOf(vertexName);

        for (int i = 0; i < totalVertices; i++) {
            if (adjacencyMatrix.get(indexV).get(i) > 0) {
                adjacentVertices.add(listOfVertices.get(i));
            }
        }

        return adjacentVertices;
    }

    @Override
    public void readGraphFromFile(File file) {

    }

    @Override
    public void removeEdge(String vertexFrom, String vertexTo, int weight) {
        int indexFrom = listOfVertices.indexOf(vertexFrom);
        if (indexFrom == -1) {
            System.out.println("No such vertex: " + vertexFrom + "!");
            return;
        }
        int indexTo = listOfVertices.indexOf(vertexTo);
        if (indexTo == -1) {
            System.out.println("No such vertex: " + vertexTo + "!");
            return;
        }
        int current = adjacencyMatrix.get(indexFrom).get(indexTo);
        if (current == 0) {
            System.out.println("No such edge: " + vertexFrom + " " + vertexTo + " " + weight);
            return;
        }
        adjacencyMatrix.get(indexFrom).set(indexTo, current - 1);
    }

    @Override
    public void removeVertex(String vertexName) {
        int indexV = listOfVertices.indexOf(vertexName);
        if (indexV == -1) {
            System.out.println("No such vertex: " + vertexName);
            return;
        }

        listOfVertices.remove(vertexName);

        for (int i = 0; i < totalVertices; i++) {
            adjacencyMatrix.get(i).remove(indexV);
        }

        adjacencyMatrix.remove(indexV);
    }
}
