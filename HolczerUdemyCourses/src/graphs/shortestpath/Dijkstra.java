package graphs.shortestpath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {

    public void computePaths(Vertex source) {
        source.setDistance(0);
        PriorityQueue<Vertex> queue = new PriorityQueue<>();
        queue.add(source);

        while (!queue.isEmpty()) {
            Vertex vertex = queue.poll();
            for (Edge edge : vertex.getAdjacencies()) {
                Vertex v = edge.getV();
                double tempDistance = vertex.getDistance() + edge.getWeight();
                if (tempDistance < v.getDistance()) {
                    queue.remove(v);
                    v.setDistance(tempDistance);
                    v.setPredecessor(vertex);
                    queue.add(v);
                }
            }
        }
    }

    public List<Vertex> getShortestPath(Vertex source) {
        List<Vertex> shortestPath = new ArrayList<>();
        Vertex v = source;
        while (v != null) {
            shortestPath.add(v);
            v = v.getPredecessor();
        }

        Collections.reverse(shortestPath);
        return shortestPath;
    }

}
