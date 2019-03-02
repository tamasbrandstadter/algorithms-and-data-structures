package graphs.maxflow;

import java.util.ArrayList;
import java.util.List;

public class FlowNetwork {
    private int vertices;
    private int edges;
    private List<List<Edge>> adjacencies;

    public FlowNetwork(int vertices) {
        this.vertices = vertices;
        adjacencies = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            List<Edge> edges = new ArrayList<>();
            adjacencies.add(edges);
        }
    }

    public void addEdge(Edge e) {
        Vertex u = e.getU();
        Vertex v = e.getV();
        adjacencies.get(u.getId()).add(e);
        adjacencies.get(v.getId()).add(e);
        edges++;
    }

    public List<Edge> getAdjacencies(Vertex v) {
        return adjacencies.get(v.getId());
    }

    public int getVertices() {
        return vertices;
    }

    public int getEdges() {
        return edges;
    }

}
