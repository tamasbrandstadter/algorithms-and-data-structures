package graphs;

import java.util.List;

public class CycleDetection {

    // running time complexity: O(V+E)
    public boolean hasCycle(List<Vertex> graph) {
        boolean hasCycle = false;
        for (Vertex v : graph) {
            hasCycle = dfs(v, hasCycle);
        }

        return hasCycle;
    }

    public boolean dfs(Vertex vertex, boolean hasCycle) {
        vertex.setVisited(false);
        vertex.setBeingVisited(true);

        for (Vertex v : vertex.getNeighbours()) {
            if (v.isBeingVisited()) {
                System.out.println("Backward edge found from vertex to neighbour: " + vertex.getValue() + "->" + v.getValue());
                return true;
            }
            if (!v.isVisited()) {
                v.setVisited(true);
                dfs(v, hasCycle);
            }
        }

        vertex.setBeingVisited(false);
        vertex.setVisited(true);

        return hasCycle;
    }

    public static void main(String[] args) {
        Vertex v = new Vertex(11);
        Vertex v2 = new Vertex(22);
        Vertex v3 = new Vertex(33);
        Vertex v4 = new Vertex(44);
        Vertex v5 = new Vertex(55);
        Vertex v6 = new Vertex(66);
        v.addNeighbour(v2);
        v2.addNeighbour(v3);
        v3.addNeighbour(v);
        v4.addNeighbour(v);
        v4.addNeighbour(v5);
        v5.addNeighbour(v6);
        v6.addNeighbour(v4);

        List<Vertex> graph = List.of(v, v2, v3, v4, v5, v6);

        CycleDetection cycleDetection = new CycleDetection();
        System.out.println(cycleDetection.hasCycle(graph));
    }

}
