package ru.nsu.kozlov;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * implementation of topological sorting.
 */
public class TopologicalSort {

    static int[] used;
    static Deque<Integer> stack;

    /**
     * topological sorting.
     *
     * @param graph graph given to be sorted
     * @param <V> type of vertices
     * @param <W> type of edge's weight
     * @return sorted order of vertices
     */
    public static <V, W extends Number> ArrayList<V> topoSort(Graph<V, W> graph) {
        ArrayList<V> sortedArray = new ArrayList<>();

        used = new int[graph.getTotalVertices()];
        stack = new ArrayDeque<>();
        ArrayList<V> allVertices = graph.getListOfVertices();

        for (V vertex : allVertices) {
            if (used[allVertices.indexOf(vertex)] == 0) {
                dfs(vertex, graph, allVertices);
            }
        }

        int index;
        while (!stack.isEmpty()) {
            index = stack.pop();
            sortedArray.add(allVertices.get(index));
        }

        return sortedArray;
    }

    /**
     * 3-colored dfs.
     *
     * @param currVertex current vertex
     * @param graph graph on which dfs is running
     * @param allVertices list of all graph's vertices
     * @param <V> type of vertices
     * @param <W> type of edge's weight
     */
    public static <V, W extends Number> void dfs(V currVertex,
                                                 Graph<V, W> graph, ArrayList<V> allVertices) {
        int index = allVertices.indexOf(currVertex);

        if (used[index] == 2) {
            return;
        }

        if (used[index] == 1) {
            throw new CycleFoundException("Graph can't be sorted because of cycle");
        }

        used[index] = 1; //gray vertex

        ArrayList<V> adjVertices = graph.getAdjacentVertices(currVertex);

        for (V newV : adjVertices) {
            dfs(newV, graph, allVertices);
        }

        used[index] = 2; //black vertex
        stack.push(index);
    }
}
