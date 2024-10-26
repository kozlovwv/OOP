package ru.nsu.kozlov;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class AdjacencyMatrix<V, W extends Number> implements Graph<V, W> {

    private final ArrayList<ArrayList<Integer>> adjacencyMatrix;
    private final ArrayList<V> listOfVertices;
    private int totalVertices;

    public AdjacencyMatrix() {
        listOfVertices = new ArrayList<>();
        adjacencyMatrix = new ArrayList<>();
        totalVertices = 0;
    }

    public int getCell(int i, int j) {
        return adjacencyMatrix.get(i).get(j);
    }

    @Override
    public int getTotalVertices() {
        return totalVertices;
    }

    @Override
    public ArrayList<V> getListOfVertices() {
        return listOfVertices;
    }

    @Override
    public void addEdge(V vertexFrom, V vertexTo, W weight) {
        if (!listOfVertices.contains(vertexFrom)) {
            throw new NoSuchVertexException("No such vertex: " + vertexFrom.toString());
        }
        if (!listOfVertices.contains(vertexTo)) {
            throw new NoSuchVertexException("No such vertex: " + vertexTo.toString());
        }

        int indexFrom = listOfVertices.indexOf(vertexFrom);
        int indexTo = listOfVertices.indexOf(vertexTo);
        int current = adjacencyMatrix.get(indexFrom).get(indexTo);

        adjacencyMatrix.get(indexFrom).set(indexTo, current + 1);
    }

    @Override
    public void addVertex(V vertexName) {
        if (listOfVertices.contains(vertexName)) {
            throw new ExistingVertexException("Such vertex already exists: " + vertexName.toString());
        }
        listOfVertices.add(vertexName);
        for (int i = 0; i < totalVertices; i++) {
            adjacencyMatrix.get(i).add(0);
        }
        adjacencyMatrix.add(new ArrayList<>());
        for (int i = 0; i <= totalVertices; i++) {
            adjacencyMatrix.get(totalVertices).add(0);
        }
        totalVertices++;
    }

    @Override
    public ArrayList<V> getAdjacentVertices(V vertexName) {
        if (!listOfVertices.contains(vertexName)) {
            throw new NoSuchVertexException("No such vertex: " + vertexName.toString());
        }

        ArrayList<V> adjacentVertices = new ArrayList<>();
        int indexV = listOfVertices.indexOf(vertexName);

        for (int i = 0; i < totalVertices; i++) {
            if (adjacencyMatrix.get(indexV).get(i) > 0) {
                adjacentVertices.add(listOfVertices.get(i));
            }
        }

        return adjacentVertices;
    }

    @Override
    public void readGraphFromFile(String fileName, Converter<V> vertexConv,
                                                   Converter<W> weightConv) {
        try {
            Scanner scanner = new Scanner(new File(fileName));

            int n = scanner.nextInt();
            int m = scanner.nextInt();

            String v = "";

            for (int i = 0; i < n; i++) {
                v = scanner.next();
                this.addVertex(vertexConv.convert(v));
            }

            String v1 = "";
            String v2 = "";
            String w  = "";

            for (int i = 0; i < m; i++) {
                v1 = scanner.next();
                v2 = scanner.next();
                w  = scanner.next();
                this.addEdge(vertexConv.convert(v1),
                             vertexConv.convert(v2),
                             weightConv.convert(w));
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
    }

    @Override
    public void removeEdge(V vertexFrom, V vertexTo, W weight) {
        if (!listOfVertices.contains(vertexFrom)) {
            throw new NoSuchVertexException("No such vertex: " + vertexFrom.toString());
        }
        if (!listOfVertices.contains(vertexTo)) {
            throw new NoSuchVertexException("No such vertex: " + vertexTo.toString());
        }

        int indexFrom = listOfVertices.indexOf(vertexFrom);
        int indexTo = listOfVertices.indexOf(vertexTo);
        int current = adjacencyMatrix.get(indexFrom).get(indexTo);

        if (current == 0) {
            throw new NoSuchEdgeException("No such edge: " + vertexFrom.toString() + " " + vertexTo.toString() + " " + weight.toString());
        }

        adjacencyMatrix.get(indexFrom).set(indexTo, current - 1);
    }

    @Override
    public void removeVertex(V vertexName) {
        int indexV = listOfVertices.indexOf(vertexName);

        if (indexV == -1) {
            throw new NoSuchVertexException("No such vertex: " + vertexName.toString());
        }

        listOfVertices.remove(vertexName);

        for (int i = 0; i < totalVertices; i++) {
            adjacencyMatrix.get(i).remove(indexV);
        }

        adjacencyMatrix.remove(indexV);
    }

    @Override
    public String toString() {
        String result = "";
        result += "adjacencyMatrix:\n";

        for (int i = 0; i < totalVertices; i++) {
            result += "[";
            for (int j = 0; j < totalVertices - 1; j++) {
                result = result + adjacencyMatrix.get(i).get(j) + ", ";
            }
            result = result + adjacencyMatrix.get(i).get(totalVertices - 1) + "]\n";
        }

        return result + "listOfVertices = " + listOfVertices + "\ntotalVertices = "
                + totalVertices;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AdjacencyMatrix<?, ?> that = (AdjacencyMatrix<?, ?>) object;
        return totalVertices == that.totalVertices && Objects.equals(adjacencyMatrix, that.adjacencyMatrix) && Objects.equals(listOfVertices, that.listOfVertices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adjacencyMatrix, listOfVertices, totalVertices);
    }
}
