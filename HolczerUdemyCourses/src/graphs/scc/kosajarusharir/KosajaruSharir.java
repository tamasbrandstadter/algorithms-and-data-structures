package graphs.scc.kosajarusharir;

/* - Strongly connected components (SCC) in general:
 * - a graph G is strongly connected if we can get from any vertex to any other vertex
 * - undirected graphs are strongly connected by definition
 * - a directed graph is a DAG only is there is no sub-graph that is strongly connected
 * - a directed cycle is strongly connected
 * - every non-trivial SCC contains at least one directed cycle
 * - running time: O(V)
 * - applications: ecology(determine the hierarchy of food needs), software engineering(packages, classes connected to
 *                 each other), astrophysics(finding clusters of stars), recommendation systems(youtube videos)
 */
public class KosajaruSharir {
    // id[v] is equal of the id of the SCC containing the given vertex
    private int[] ids;
    // number of SCC-s
    private int count;
    // track visited vertices
    private boolean[] marked;

    public KosajaruSharir(Graph g) {
        marked = new boolean[g.getVertices().size()];
        ids = new int[g.getVertices().size()];

        // run dfs on transposed version of the original graph
        DepthFirstOrder depthFirstOrder = new DepthFirstOrder(g.transpose());

        // run dfs again, set ids for SCC-s
        for (Vertex v : depthFirstOrder.getStack()) {
            if (!marked[v.getId()]) {
                dfs(v);
                count++;
            }
        }
    }

    private void dfs(Vertex vertex) {
        marked[vertex.getId()] = true;
        ids[vertex.getId()] = count;
        vertex.setComponentId(count);

        for (Vertex v : vertex.getVertices()) {
            if (!marked[v.getId()]) {
                dfs(v);
            }
        }
    }

    public int getCount() {
        return count;
    }

}
