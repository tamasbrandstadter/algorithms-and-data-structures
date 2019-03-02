package graphs.maxflow;

public class Edge {
    private Vertex u;
    private Vertex v;
    private final double capacity;
    private double flow;

    public Edge(Vertex u, Vertex v, double capacity) {
        this.u = u;
        this.v = v;
        this.capacity = capacity;
    }

    public Edge(Edge e) {
        u = e.getU();
        v = e.getV();
        capacity = e.getCapacity();
        flow = e.getFlow();
    }

    public Vertex getOther(Vertex vertex) {
        if (vertex == u) {
            return v;
        }
        return u;
    }

    // for creating edges in residual network
    public double getResidualFlow(Vertex vertex) {
        if (vertex == u) {
            // backward edge
            return flow;
        }
        // forward edge
        return capacity - flow;
    }

    public void addResidualFlow(Vertex vertex, double deltaFlow) {
        if (vertex == u) {
            // backward edge
            flow = flow - deltaFlow;
        } else {
            // forward edge
            flow = flow + deltaFlow;
        }
    }

    public Vertex getU() {
        return u;
    }

    public void setU(Vertex u) {
        this.u = u;
    }

    public Vertex getV() {
        return v;
    }

    public void setV(Vertex v) {
        this.v = v;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getFlow() {
        return flow;
    }

    public void setFlow(double flow) {
        this.flow = flow;
    }

}
