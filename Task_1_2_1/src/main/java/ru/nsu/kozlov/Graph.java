package ru.nsu.kozlov;

import java.io.File;
import java.util.ArrayList;

public interface Graph {
    void addEdge(String vertexFrom, String vertexTo, int weight);
    void removeEdge(String vertexFrom, String vertexTo, int weight);
    void addVertex(String vertexName);
    void removeVertex(String vertexName);
    ArrayList<String> getAdjacentVertices(String vertexName);
    void readGraphFromFile(File file);
    //another functions...
}