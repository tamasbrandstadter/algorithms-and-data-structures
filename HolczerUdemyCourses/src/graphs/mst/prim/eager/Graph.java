package com.company.mst.prim.eager;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Vertex> vertices;
    private List<Edge> edges;

    public Graph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void addVertex(Vertex v) {
        vertices.add(v);
    }

    // create undirected edge
    public void addEdge(Edge e) {
        Vertex u = e.getU();
        Vertex v = e.getV();
        int i = vertices.indexOf(u);
        int j = vertices.indexOf(v);
        vertices.get(i).addEdge(new Edge(e.getWeight(), u, v));
        vertices.get(j).addEdge(new Edge(e.getWeight(), v, u));
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
