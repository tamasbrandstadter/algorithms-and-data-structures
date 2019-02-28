package graphs.mst.prim.eager;

import java.util.List;
import java.util.PriorityQueue;

// same description as lazy version
public class Prim {
    private List<Vertex> vertices;
    private PriorityQueue<Vertex> queue;

    public Prim(Graph g) {
        vertices = g.getVertices();
        queue = new PriorityQueue<>();
    }

    // eager
    public void mst() {
        for (Vertex v : vertices) {
            if (!v.isVisited()) {
                mst(v);
            }
        }

        // show elements
        for (Vertex v : vertices) {
            if (v.getMin() != null) {
                Edge min = v.getMin();
                System.out.println("Edge: " + min.getU() + "-" + min.getV());
            }
        }
    }

    private void mst(Vertex vertex) {
        vertex.setDistance(0);
        queue.add(vertex);
        while (!queue.isEmpty()) {
            Vertex v = queue.remove();
            scanVertex(v);
        }
    }

    private void scanVertex(Vertex vertex) {
        // visit the vertex
        vertex.setVisited(true);

        // visit every neighbour
        for (Edge edge : vertex.getEdges()) {
            Vertex v = edge.getV();
            // exclude already visited
            if (v.isVisited()) {
                continue;
            }
            // check for shorter path
            if (edge.getWeight() < v.getDistance()) {
                // if so, update parameters
                v.setDistance(edge.getWeight());
                v.setMin(edge);
                // update queue - this is different compared to lazy implementation
                if (queue.contains(v)) {
                    queue.remove(v);
                }
                queue.add(v);
            }
        }
    }

}
