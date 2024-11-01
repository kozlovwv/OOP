package ru.nsu.kozlov;

import java.util.Objects;

/**
 * implementation of Edge.
 *
 * @param <V> type of vertices
 * @param <W> type of edge's weight
 */
public class Edge<V, W extends Number> {
    private final V vertexFrom;
    private final V vertexTo;
    private final W weight;

    /**
     * Edge constructor.
     *
     * @param vf vertexFrom
     * @param vt vertexTo
     * @param w  weight
     */
    public Edge(V vf, V vt, W w) {
        vertexFrom = vf;
        vertexTo = vt;
        weight = w;
    }

    public V getVertexTo() {
        return vertexTo;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Edge<?, ?> edge = (Edge<?, ?>) object;
        return Objects.equals(vertexFrom, edge.vertexFrom)
                && Objects.equals(vertexTo, edge.vertexTo)
                && Objects.equals(weight, edge.weight);
    }

    @Override
    public String toString() {
        return "{" + vertexFrom + ", " + vertexTo + ", " + weight + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertexFrom, vertexTo, weight);
    }
}
