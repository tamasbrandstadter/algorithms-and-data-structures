package graphs.scc.kosajarusharir;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Vertex> vertices;
    private List<Edge> edges;

    public Graph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public Graph(List<Vertex> vertices, List<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    public Graph transpose() {
        Graph transposed = new Graph();
        List<Vertex> transposedVertices = new ArrayList<>(vertices);
        for (Edge edge : edges) {
            // reverse edges
            transposedVertices.get(transposedVertices.indexOf(edge.getV())).addVertex(edge.getU());
        }
        transposed.setVertices(transposedVertices);
        return transposed;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

}
