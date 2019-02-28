package graphs.scc.kosajarusharir;

import java.util.Stack;

public class DepthFirstOrder {
    private Stack<Vertex> stack;

    public DepthFirstOrder(Graph g) {
        stack = new Stack<>();
        for (Vertex v : g.getVertices()) {
            if (!v.isVisited()) {
                dfs(v);
            }
        }
    }

    private void dfs(Vertex vertex) {
        vertex.setVisited(true);

        for (Vertex v : vertex.getVertices()) {
            if (!v.isVisited()) {
                dfs(v);
            }
        }

        stack.push(vertex);
    }

    public Stack<Vertex> getStack() {
        return stack;
    }

}
