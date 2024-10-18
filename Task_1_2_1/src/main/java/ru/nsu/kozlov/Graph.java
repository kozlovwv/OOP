package ru.nsu.kozlov;

import java.util.ArrayList;

public interface Graph {
    void addEdge(String vertexFrom, String vertexTo, int weight);
    void removeEdge();
    void addVertex(String vertexName);
    void removeVertex();
    ArrayList<String> getAdjacentVertices(String vertexName);
    void readGraphFromFile();
    //another functions...
}