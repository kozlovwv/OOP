package ru.nsu.kozlov;

import java.util.ArrayList;

/**
 * implementation of graph's interface.
 *
 * @param <V> type of vertices
 * @param <W> type of edge's weight
 */
public interface Graph<V, W extends Number> {
    void addEdge(V vertexFrom, V vertexTo, W weight);

    void removeEdge(V vertexFrom, V vertexTo, W weight);

    void addVertex(V vertexName);

    void removeVertex(V vertexName);

    ArrayList<V> getAdjacentVertices(V vertexName);

    void readGraphFromFile(String fileName, Converter<V> vertexConvert,
                                            Converter<W> weightConvert);

    int getTotalVertices();

    ArrayList<V> getListOfVertices();
}