package ru.nsu.kozlov;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * implementation of adjacency list.
 * @param <V> type of vertices
 * @param <W> type of edge's weight
 */
public class AdjacencyList<V, W extends Number> implements Graph<V, W> {

    private final ArrayList<ArrayList<Edge<V, W>>> adjacencyList;
    private final ArrayList<V> listOfVertices;

    private int totalVertices;

    /**
     * adjacency list's constructor.
     */
    public AdjacencyList() {
        totalVertices = 0;
        adjacencyList = new ArrayList<>();
        listOfVertices = new ArrayList<>();
    }

    public Edge<V, W> getEdge(int i, int j) {
        return adjacencyList.get(i).get(j);
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

        adjacencyList.get(indexFrom).add(new Edge<>(vertexFrom, vertexTo, weight));
    }

    @Override
    public void removeEdge(V vertexFrom, V vertexTo, W weight) {
        if (!listOfVertices.contains(vertexFrom)) {
            throw new NoSuchVertexException("No such vertex: " + vertexFrom.toString());
        }
        if (!listOfVertices.contains(vertexTo)) {
            throw new NoSuchVertexException("No such vertex: " + vertexTo.toString());
        }

        Edge<V, W> currEdge = new Edge<>(vertexFrom, vertexTo, weight);
        int indexFrom = listOfVertices.indexOf(vertexFrom);
        ArrayList<Edge<V, W>> currList = adjacencyList.get(indexFrom);
        int size = currList.size();

        for (int i = 0; i < size; i++) {
            if (currList.get(i).equals(currEdge)) {
                adjacencyList.get(indexFrom).remove(i);
                break;
            }
        }
    }

    @Override
    public void addVertex(V vertexName) {
        if (listOfVertices.contains(vertexName)) {
            throw new ExistingVertexException("Such vertex already exists: "
                                                + vertexName.toString());
        }

        listOfVertices.add(vertexName);
        totalVertices++;
        adjacencyList.add(new ArrayList<Edge<V, W>>());
    }

    @Override
    public void removeVertex(V vertexName) {
        int indexV = listOfVertices.indexOf(vertexName);

        if (indexV == -1) {
            throw new NoSuchVertexException("No such vertex: " + vertexName.toString());
        }

        adjacencyList.remove(indexV);
        totalVertices--;
        listOfVertices.remove(indexV);

        for (int i = 0; i < totalVertices; i++) {
            int size = adjacencyList.get(i).size();
            for (int j = size - 1; j >= 0; j--) {
                if (adjacencyList.get(i).get(j).getVertexTo().equals(vertexName)) {
                    adjacencyList.get(i).remove(j);
                }
            }
        }
    }

    @Override
    public ArrayList<V> getAdjacentVertices(V vertexName) {
        if (!listOfVertices.contains(vertexName)) {
            throw new NoSuchVertexException("No such vertex: " + vertexName.toString());
        }

        ArrayList<V> adjacentVertices = new ArrayList<>();

        int indexV = listOfVertices.indexOf(vertexName);
        ArrayList<Edge<V, W>> currList = adjacencyList.get(indexV);

        for (Edge<V, W> edge : currList) {
            if (!adjacentVertices.contains(edge.getVertexTo())) {
                adjacentVertices.add(edge.getVertexTo());
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
                w = scanner.next();
                this.addEdge(vertexConv.convert(v1),
                        vertexConv.convert(v2),
                        weightConv.convert(w));
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
    }

    @Override
    public String toString() {
        String result = "";
        result += "adjacencyList:\n";
        int size;

        for (int i = 0; i < totalVertices; i++) {
            result += "[";
            size = adjacencyList.get(i).size();
            if (size == 0) {
                result = result + "No edges from " + listOfVertices.get(i);
            }
            for (int j = 0; j < size - 1; j++) {
                result = result + adjacencyList.get(i).get(j) + ", ";
            }
            if (size > 0) {
                result = result + adjacencyList.get(i).get(size - 1);
            }
            result += "]\n";
        }

        return result + "totalVertices = " + totalVertices
                + "\nlistOfVertices = " + listOfVertices;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        AdjacencyList<?, ?> that = (AdjacencyList<?, ?>) object;
        return totalVertices == that.totalVertices
                                && Objects.equals(adjacencyList, that.adjacencyList)
                                && Objects.equals(listOfVertices, that.listOfVertices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adjacencyList, listOfVertices, totalVertices);
    }
}
