package ru.nsu.kozlov;

import java.util.Objects;

public class Edge<V, W extends Number> {
    private final V vertexFrom;
    private final V vertexTo;
    private final W weight;

    public Edge(V vF, V vT, W w) {
        vertexFrom = vF;
        vertexTo = vT;
        weight = w;
    }

    public V getVertexTo() {
        return vertexTo;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Edge<?, ?> edge = (Edge<?, ?>) object;
        return Objects.equals(vertexFrom, edge.vertexFrom) && Objects.equals(vertexTo, edge.vertexTo) && Objects.equals(weight, edge.weight);
    }

    @Override
    public String toString() {
        return "{" + vertexFrom +
                ", " + vertexTo +
                ", " + weight +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertexFrom, vertexTo, weight);
    }
}
