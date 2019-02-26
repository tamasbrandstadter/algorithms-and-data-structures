package graphs.shortestpath.negativeedge;

import graphs.shortestpath.Edge;
import graphs.shortestpath.Vertex;

import java.util.List;

/* - handles negative edge weights
 * - relaxes all edges at the same time for V - 1 iteration
 * - +1 iteration for detecting cycles, if the cost increases in the Vth iteration -> negative cycle
 * - applications: arbitrage situation on FOREX, longest path problem, paralell job scheduling problem, critical path method
 */
public class BellmanFord {
    private List<Edge> edges;
    private List<Vertex> vertices;

    public BellmanFord(List<Edge> edges, List<Vertex> vertices) {
        this.edges = edges;
        this.vertices = vertices;
    }

    // running time complexity: O(V * E)
    public void computePaths(Vertex source) {
        source.setDistance(0);
        for (int i = 0; i < vertices.size() - 1; i++) {
            for (Edge edge : edges) {
                Vertex u = edge.getU();
                Vertex v = edge.getV();
                if (u.getDistance() == Double.MAX_VALUE) {
                    continue;
                }
                double tempDistance = u.getDistance() + edge.getWeight();
                if (tempDistance < v.getDistance()) {
                    v.setDistance(tempDistance);
                    v.setPredecessor(u);
                }
            }
        }

        // cycle detection
        for (Edge edge : edges) {
            if (edge.getU().getDistance() != Double.MAX_VALUE) {
                if (hasCycle(edge)) {
                    System.out.println("Negative cycle detected in graph.");
                    return;
                }
            }
        }
    }

    public void getShortestPath(Vertex target) {
        if (target.getDistance() == Double.MAX_VALUE) {
            System.out.println("There is no path from the source to the target.");
        }

        Vertex v = target;
        while (v != null) {
            System.out.println(v.getName() + " - ");
            v = v.getPredecessor();
        }
    }

    private boolean hasCycle(Edge e) {
        return e.getU().getDistance() + e.getWeight() < e.getV().getDistance();
    }

}
