package graphs;

import java.util.List;
import java.util.Stack;

/* Topological ordering of a directed graph is the linear ordering of its vertices such that for every directed edge uv
 * from vertex u to vertex v, u comes before v in the ordering (only if the graph is a DAG).
 * Any DAG has at least one topological order.
 * Running time complexity: O(V+E) - linear
 * Applications: - decide whether Hamiltonian path exists in a given graph
 *               - dependency management
 *               - continuous deployment
 *               - constructing syllabus
 */
public class TopologicalSort {
    private Stack<Vertex> stack;

    public TopologicalSort(List<Vertex> graph) {
        this.stack = new Stack<>();
        for (Vertex v : graph) {
            if (!v.isVisited()) {
                dfs(v);
            }
        }
    }

    private void dfs(Vertex vertex) {
        vertex.setVisited(true);

        for (Vertex v : vertex.getNeighbours()) {
            if (!v.isVisited()) {
                dfs(v);
            }
        }

        stack.push(vertex);
    }

    public Stack<Vertex> getStack() {
        return stack;
    }

    public static void main(String[] args) {
        Vertex vertex = new Vertex(0);
        Vertex vertex1 = new Vertex(1);
        Vertex vertex2 = new Vertex(2);
        Vertex vertex3 = new Vertex(3);
        Vertex vertex4 = new Vertex(4);
        vertex2.addNeighbour(vertex3);
        vertex3.addNeighbour(vertex1);
        vertex4.addNeighbour(vertex);
        vertex4.addNeighbour(vertex1);
        List<Vertex> graph = List.of(vertex, vertex1, vertex2, vertex3, vertex4);

        TopologicalSort topologicalSort = new TopologicalSort(graph);
        for (Vertex v : topologicalSort.getStack()) {
            System.out.println(v);
        }
    }

}
