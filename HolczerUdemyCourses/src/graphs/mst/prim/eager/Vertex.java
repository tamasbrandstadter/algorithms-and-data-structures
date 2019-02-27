package com.company.mst.prim.eager;

import java.util.ArrayList;
import java.util.List;

public class Vertex implements Comparable<Vertex> {
    private boolean visited;
    private String name;
    private Vertex previous;
    // shortest edge to the actual MST from a non MST vertex
    private Edge min;
    // used to detect whether heap is needed to refresh because of a shorter path
    private double distance;
    private List<Edge> edges;

    public Vertex(String name) {
        this.name = name;
        distance = Double.POSITIVE_INFINITY;
        edges = new ArrayList<>();
    }

    public void addEdge(Edge e) {
        edges.add(e);
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vertex getPrevious() {
        return previous;
    }

    public void setPrevious(Vertex previous) {
        this.previous = previous;
    }

    public Edge getMin() {
        return min;
    }

    public void setMin(Edge min) {
        this.min = min;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    @Override
    public int compareTo(Vertex other) {
        return Double.compare(distance, other.getDistance());
    }

}
