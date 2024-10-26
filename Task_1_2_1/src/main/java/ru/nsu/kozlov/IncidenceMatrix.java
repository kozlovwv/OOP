package ru.nsu.kozlov;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class IncidenceMatrix<V, W extends Number> implements Graph<V, W>{

    private int totalVertices;
    private int totalEdges;

    private final ArrayList<ArrayList<Integer>> incidenceMatrix;
    private final ArrayList<V> listOfVertices;
    private final ArrayList<Edge<V, W>> listOfEdges;

    public IncidenceMatrix() {
        totalEdges = 0;
        totalVertices = 0;
        incidenceMatrix = new ArrayList<>();
        listOfVertices = new ArrayList<>();
        listOfEdges = new ArrayList<>();
    }

    public Edge<V, W> getEdge(int i) {
        return listOfEdges.get(i);
    }

    public int getCell(int i, int j) {
        return incidenceMatrix.get(i).get(j);
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

        listOfEdges.add(new Edge<V, W>(vertexFrom, vertexTo, weight));

        int indexFrom = listOfVertices.indexOf(vertexFrom);
        int indexTo = listOfVertices.indexOf(vertexTo);

        for (int i = 0; i < totalVertices; i++) {
            if (i == indexFrom && i == indexTo) {
                incidenceMatrix.get(i).add(2);
            } else if (i == indexFrom) {
                incidenceMatrix.get(i).add(-1);
            } else if (i == indexTo) {
                incidenceMatrix.get(i).add(1);
            } else {
                incidenceMatrix.get(i).add(0);
            }
        }

        totalEdges++;
    }

    @Override
    public void removeEdge(V vertexFrom, V vertexTo, W weight) {
        Edge<V, W> currentEdge = new Edge<>(vertexFrom, vertexTo, weight);
        int indexOfEdge = -1;

        for (int i = 0; i < totalEdges; i++) {
            if (currentEdge.equals(listOfEdges.get(i))) {
                indexOfEdge = i;
                break;
            }
        }

        if (indexOfEdge == -1) {
            throw new NoSuchEdgeException("No such edge: " + vertexFrom.toString() + " " + vertexTo.toString() + " " + weight.toString());
        }

        for (int i = 0; i < totalVertices; i++) {
            incidenceMatrix.get(i).remove(indexOfEdge);
        }

        listOfEdges.remove(indexOfEdge);
        totalEdges--;
    }

    @Override
    public void addVertex(V vertexName) {
        if (listOfVertices.contains(vertexName)) {
            throw new ExistingVertexException("Such vertex already exists: " + vertexName.toString());
        }
        listOfVertices.add(vertexName);
        incidenceMatrix.add(new ArrayList<>());
        for (int i = 0; i < totalEdges; i++) {
            incidenceMatrix.get(totalVertices).add(0);
        }
        totalVertices++;
    }

    @Override
    public void removeVertex(V vertexName) {
        int indexV = listOfVertices.indexOf(vertexName);

        if (indexV == -1) {
            throw new NoSuchVertexException("No such vertex: " + vertexName.toString());
        }

        ArrayList<Integer> currList = incidenceMatrix.get(indexV);

        for (int i = totalEdges - 1; i >= 0; i--) {
            if (currList.get(i) != 0) {
                for (int j = 0; j < totalVertices; j++) {
                    incidenceMatrix.get(j).remove(i);
                }
                listOfEdges.remove(i);
                totalEdges--;
            }
        }

        incidenceMatrix.remove(indexV);
        listOfVertices.remove(vertexName);
        totalVertices--;
    }

    @Override
    public ArrayList<V> getAdjacentVertices(V vertexName) {
        if (!listOfVertices.contains(vertexName)) {
            throw new NoSuchVertexException("No such vertex: " + vertexName.toString());
        }

        ArrayList<V> adjacentVertices = new ArrayList<>();

        int indexV = listOfVertices.indexOf(vertexName);
        ArrayList<Integer> currList = incidenceMatrix.get(indexV);

        for (int i = 0; i < totalEdges; i++) {
            if (currList.get(i) == 2) {
                adjacentVertices.add(listOfVertices.get(i));
            } else if (currList.get(i) == -1) {
                for (int index = 0; index < totalVertices; index++) {
                    if (incidenceMatrix.get(index).get(i) == 1) {
                        adjacentVertices.add(listOfVertices.get(index));
                        break;
                    }
                }
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
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        IncidenceMatrix<?, ?> that = (IncidenceMatrix<?, ?>) object;
        return totalVertices == that.totalVertices && totalEdges == that.totalEdges && Objects.equals(incidenceMatrix, that.incidenceMatrix) && Objects.equals(listOfVertices, that.listOfVertices) && Objects.equals(listOfEdges, that.listOfEdges);
    }

    @Override
    public String toString() {
        String result = "";
        result += "IncidenceMatrix:\n";

        for (int i = 0; i < totalVertices; i++) {
            result += "[";
            for (int j = 0; j < totalEdges - 1; j++) {
                result = result + incidenceMatrix.get(i).get(j) + ", ";
            }
            result = result + incidenceMatrix.get(i).get(totalEdges - 1) + "]\n";
        }

        return result + "totalVertices = " + totalVertices
                + "\nlistOfVertices = " + listOfVertices
                + "\ntotalEdges = " + totalEdges
                + "\nlistOfVertices = " + listOfEdges;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalVertices, totalEdges, incidenceMatrix, listOfVertices, listOfEdges);
    }
}
