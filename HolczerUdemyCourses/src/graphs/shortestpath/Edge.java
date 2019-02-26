package graphs.shortestpath;

public class Edge {
    private double weight;
    private Vertex v;
    private Vertex u;

    public Edge(double weight, Vertex v, Vertex u) {
        this.weight = weight;
        this.v = v;
        this.u = u;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Vertex getV() {
        return v;
    }

    public void setV(Vertex v) {
        this.v = v;
    }

    public Vertex getU() {
        return u;
    }

    public void setU(Vertex u) {
        this.u = u;
    }

}
