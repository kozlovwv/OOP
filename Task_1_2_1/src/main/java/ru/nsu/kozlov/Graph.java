package ru.nsu.kozlov;

import java.util.ArrayList;

public interface Graph<V, W> {
    void addEdge(V vertexFrom, V vertexTo, W weight);
    void removeEdge(V vertexFrom, V vertexTo, W weight);
    void addVertex(V vertexName);
    void removeVertex(V vertexName);
    ArrayList<V> getAdjacentVertices(V vertexName);
    void readGraphFromFile(String fileName, Converter<V> vertexConvert,
                                            Converter<W> weightConverter);
}