package graphs.mst.prim.lazy;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private boolean visited;
    private String name;
    private Vertex previous;
    private List<Edge> edges;

    public Vertex(String name) {
        this.name = name;
        edges = new ArrayList<>();
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

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

}
