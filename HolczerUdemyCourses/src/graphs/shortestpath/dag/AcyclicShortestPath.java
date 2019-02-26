package graphs.shortestpath.dag;

import graphs.shortestpath.Edge;
import graphs.shortestpath.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/* - compute the topological order of a DAG, then iterate through it and relax all edges from the actual vertex
 * - handles negative edge weights
 * - application: solving Knapsack-problem, seam carving (Avidan-Shamir method)
 */
public class AcyclicShortestPath {

    // running time complexity: O(V + E)
    public void computePaths(List<Vertex> graph, Vertex source, Vertex target) {
        source.setDistance(0);

        TopologicalSort topologicalSort = new TopologicalSort();
        topologicalSort.sort(graph);
        Stack<Vertex> stack = topologicalSort.getTopologicalOrder();

        for (Vertex vertex : stack) {
            for (Edge edge : vertex.getAdjacencies()) {
                Vertex u = edge.getU();
                Vertex v = edge.getV();
                double tempDistance = u.getDistance() + edge.getWeight();
                if (tempDistance < v.getDistance()) {
                    v.setDistance(tempDistance);
                    v.setPredecessor(u);
                }
            }
        }

        if (target.getDistance() == Double.MAX_VALUE) {
            System.out.println("There is no shortest path.");
        } else {
            System.out.println("Target vertex shortest path: " + target.getDistance());
        }
    }

    public List<Vertex> getShortestPath(Vertex target) {
        List<Vertex> shortestPath = new ArrayList<>();
        Vertex v = target;
        while (v != null) {
            shortestPath.add(v);
            v = v.getPredecessor();
        }

        Collections.reverse(shortestPath);
        return shortestPath;
    }

}
