package graphs.maxflow;

import java.util.LinkedList;
import java.util.Queue;

public class FordFulkerson {
    // marked[v.getId()] = true -> v in the residual graph
    private boolean[] marked;
    // edgeTo[v] -> edges in the augmenting path
    private Edge[] edgeTo;
    private double maxFlow;

    // Edmonds-Karp implementation
    public FordFulkerson(FlowNetwork network, Vertex s, Vertex t) {
        while (hasAugmentingPath(network, s, t)) {
            double min = Double.POSITIVE_INFINITY;
            for (Vertex v = t; v != s; v = edgeTo[v.getId()].getOther(v)) {
                min = Math.min(min, edgeTo[v.getId()].getResidualFlow(v));
            }

            for (Vertex v = t; v != s; v = edgeTo[v.getId()].getOther(v)) {
                edgeTo[v.getId()].addResidualFlow(v, min);
            }

            maxFlow += min;
        }
    }

    private boolean hasAugmentingPath(FlowNetwork network, Vertex s, Vertex t) {
        edgeTo = new Edge[network.getVertices()];
        marked = new boolean[network.getVertices()];

        Queue<Vertex> queue = new LinkedList<>();
        queue.add(s);
        marked[s.getId()] = true;

        while (!queue.isEmpty() && !marked[t.getId()]) {
            Vertex v = queue.remove();
            for (Edge edge : network.getAdjacencies(v)) {
                Vertex u = edge.getOther(v);
                if (edge.getResidualFlow(u) > 0) {
                    if (!marked[u.getId()]) {
                        edgeTo[u.getId()] = edge;
                        marked[u.getId()] = true;
                        queue.add(u);
                    }
                }
            }
        }

        return marked[t.getId()];
    }

    public boolean isInCut(int index) {
        return marked[index];
    }

    public double getMaxFlow() {
        return maxFlow;
    }

}
