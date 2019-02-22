package graphs;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private int value;
    private boolean visited;
    private boolean beingVisited;
    private List<Vertex> neighbours;

    public Vertex(int value) {
        this.value = value;
        this.neighbours = new ArrayList<>();
    }

    public void addNeighbour(Vertex v) {
        neighbours.add(v);
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public List<Vertex> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<Vertex> neighbours) {
        this.neighbours = neighbours;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isBeingVisited() {
        return beingVisited;
    }

    public void setBeingVisited(boolean beingVisited) {
        this.beingVisited = beingVisited;
    }

    @Override
    public String toString() {
        return "Vertex{value=" + value + '}';
    }

}
