package graphs.mst.kruskal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* - edge based algorithm
   - should be used when the graph is sparse
   - applications for MST:
   - big data, analysis, clustering algorithms,
   - minimum cost for telecommunication company for cable laying into new neighborhood
 */
public class Kruskal {

    // running time complexity: O(E log E)
    public List<Edge> mst(List<Vertex> graph, List<Edge> edges) {
        UnionFind uf = new UnionFind(graph);
        Collections.sort(edges);

        List<Edge> mst = new ArrayList<>();
        for (Edge edge : edges) {
            Vertex u = edge.getU();
            Vertex v = edge.getV();
            // in case they are not in the same disjoint set, we add the edge to the MST
            if (uf.find(u.getNode()) != uf.find(v.getNode())) {
                mst.add(edge);
                uf.union(u.getNode(), v.getNode());
            }
        }

        return mst;
    }

}
