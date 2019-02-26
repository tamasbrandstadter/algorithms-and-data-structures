package graphs.shortestpath.dag;

import graphs.shortestpath.Edge;
import graphs.shortestpath.Vertex;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class TopologicalSort {
    private Stack<Vertex> stack;

    public TopologicalSort() {
        this.stack = new Stack<>();
    }

    public void sort(List<Vertex> graph) {
        for (Vertex vertex : graph) {
            if (!vertex.isVisited()) {
                dfs(vertex);
            }
        }
    }

    private void dfs(Vertex vertex) {
        vertex.setVisited(true);

        for (Edge edge : vertex.getAdjacencies()) {
            if (!edge.getV().isVisited()) {
                edge.getV().setVisited(true);
                dfs(edge.getV());
            }
        }

        stack.push(vertex);
    }

    public Stack<Vertex> getTopologicalOrder() {
        Collections.reverse(stack);
        return stack;
    }

}
