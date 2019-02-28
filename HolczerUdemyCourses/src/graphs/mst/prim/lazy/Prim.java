package graphs.mst.prim.lazy;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/* - vertex based algorithm
 * - lazy and eager version
 * - should be used when the graph is dense
 * - average running time complexity: O(E log E) and space proportional O(E)
 * - worst case running time complexity: O(E log V)
 * - typical applications: optimizing cable/road/pipe/etc. length, k-means clustering, spanning tree protocol
 */
public class Prim {
    private List<Vertex> unvisitedVertices;
    private List<Edge> mst;
    private PriorityQueue<Edge> queue;
    private double fullCost;

    public Prim(List<Vertex> graph) {
        this.unvisitedVertices = graph;
        mst = new ArrayList<>();
        queue = new PriorityQueue<>();
    }

    // lazy - does not update the queue
    public List<Edge> mst(Vertex vertex) {
        // visit the vertex
        vertex.setVisited(true);
        unvisitedVertices.remove(vertex);

        while (!queue.isEmpty()) {
            for (Edge edge : vertex.getEdges()) {
                // avoid adding an edge for an unvisited vertex which would form a cycle
                if (unvisitedVertices.contains(edge.getV())) {
                    // insert every neighbour of the given vertex to queue
                    queue.add(edge);
                }
            }

            // add edge to MST
            Edge min = queue.remove();
            mst.add(min);
            fullCost += min.getWeight();

            // update vertex for next iteration
            vertex = min.getV();

            // visit the next vertex
            vertex.setVisited(true);
            unvisitedVertices.remove(vertex);
        }

        return mst;
    }

    public double getFullCost() {
        return fullCost;
    }
}
