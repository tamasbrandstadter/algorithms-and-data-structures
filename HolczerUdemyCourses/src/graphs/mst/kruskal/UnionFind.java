package com.company.mst.kruskal;

import java.util.ArrayList;
import java.util.List;

/* - data structure to keep track of a set of elements partitioned into a number of disjoint (non-overlapping) subsets
 * - applications: mostly used for Kruskal-algorithm
 */
public class UnionFind {
    private int nodes;
    private int disjointSets;
    // representatives
    private List<Node> rootNodes;

    public UnionFind(List<Vertex> graph) {
        rootNodes = new ArrayList<>(graph.size());
        // running time complexity: O(n)
        for (Vertex v : graph) {
            makeSets(v);
        }
    }

    // running time complexity: amortized O(1)
    public int find(Node node) {
        Node current = node;

        // find the root
        while (current.getParent() != null) {
            current = current.getParent();
        }

        // set references
        Node root = current;
        current = node;

        // path-compression
        while (current != root) {
            Node temp = current.getParent();
            current.setParent(root);
            current = temp;
        }

        return root.getId();
    }

    // running time complexity: O(1)
    public void union(Node node, Node other) {
        int i = find(node);
        int j = find(other);

        if (i == j) {
            return;
        }

        Node root = rootNodes.get(i);
        Node root2 = rootNodes.get(j);

        if (root.getRank() < root2.getRank()) {
            root.setParent(root2);
        } else if (root.getRank() > root2.getRank()) {
            root2.setParent(root);
        } else {
            root2.setParent(root);
            root.setRank(root.getRank() + 1);
        }

        disjointSets--;
    }

    // running time complexity: O(1)
    private void makeSets(Vertex vertex) {
        Node n = new Node(0, rootNodes.size(), null);
        vertex.setNode(n);
        rootNodes.add(n);
        nodes++;
        disjointSets++;
    }

}
