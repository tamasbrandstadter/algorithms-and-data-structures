package graphs.shortestpath;

import java.util.ArrayList;
import java.util.List;

public class Vertex implements Comparable<Vertex> {
    private double distance = Double.MAX_VALUE;
    private boolean visited;
    private String name;
    private Vertex predecessor;
    private List<Edge> adjacencies;

    public Vertex(String name) {
        this.name = name;
        adjacencies = new ArrayList<>();
    }

    public void addEdge(Edge e) {
        adjacencies.add(e);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Edge> getAdjacencies() {
        return adjacencies;
    }

    public void setAdjacencies(List<Edge> adjacencies) {
        this.adjacencies = adjacencies;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Vertex getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Vertex predecessor) {
        this.predecessor = predecessor;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(Vertex other) {
        return Double.compare(distance, other.getDistance());
    }

}
