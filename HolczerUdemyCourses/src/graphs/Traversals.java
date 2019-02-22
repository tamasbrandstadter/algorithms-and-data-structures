package graphs;

import java.util.LinkedList;
import java.util.Queue;

public class Traversals {

    // running time complexity: O(V+E) - linear running time
    // memory complexity: O(n)
    // typical application: web-crawler
    public static void bfs(Vertex vertex) {
        vertex.setVisited(true);
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(vertex);

        while (!queue.isEmpty()) {
            for (Vertex v : queue.remove().getNeighbours()) {
                if (!v.isVisited()) {
                    v.setVisited(true);
                    queue.add(v);
                }
            }
        }
    }

    // running time complexity: O(V+E) - linear running time
    // memory complexity: O(log n)
    // typical applications: topological ordering, Kosajaru algorithm, cycle detection (DAG or not)
    public static void dfs(Vertex vertex) {
        vertex.setVisited(true);
        System.out.println("Vertex value: " + vertex.getValue());

        for (Vertex v : vertex.getNeighbours()) {
            if (!v.isVisited()) {
                dfs(v);
            }
        }
    }

}
