package graphs.scc.tarjan;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Tarjan {
    private Stack<Vertex> stack;
    private List<Vertex> vertices;
    private List<List<Vertex>> connectedComponents;
    private int time;
    private int count;

    public Tarjan(List<Vertex> vertices) {
        this.vertices = vertices;
        stack = new Stack<>();
        connectedComponents = new ArrayList<>();
    }

    // running time: O(V)
    public List<List<Vertex>> getSCC() {
        for (Vertex v : vertices) {
            if (!v.isVisited()) {
                dfs(v);
            }
        }
        return connectedComponents;
    }

    private void dfs(Vertex vertex) {
        vertex.setLowLink(time++);
        vertex.setVisited(true);
        stack.add(vertex);
        boolean isComponentRoot = true;

        for (Vertex v : vertex.getVertices()) {
            if (!v.isVisited()) {
                dfs(v);
            }
            if (vertex.getLowLink() > v.getLowLink()) {
                vertex.setLowLink(v.getLowLink());
                isComponentRoot = false;
            }
        }

        if (isComponentRoot) {
            count++;
            List<Vertex> component = new ArrayList<>();
            while (true) {
                Vertex v = stack.pop();
                component.add(v);
                v.setLowLink(Integer.MAX_VALUE);
                if (v.getName().equals(vertex.getName())) {
                    break;
                }
            }
            connectedComponents.add(component);
        }
    }

}
