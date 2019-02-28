package graphs.scc.kosajarusharir;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private int id;
    private int componentId;
    private boolean visited;
    private String name;
    private List<Vertex> vertices;

    public Vertex(int id, String name) {
        this.id = id;
        this.name = name;
        vertices = new ArrayList<>();
    }

    public void addVertex(Vertex v) {
        vertices.add(v);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
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

    public List<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }

}
