import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;

public class SAP {
  private final Digraph digraph;
  private int shortest;

  public SAP(Digraph G) {
    if (G == null) {
      throw new IllegalArgumentException();
    }

    this.digraph = new Digraph(G);
  }

  public int length(int v, int w) {
    if (v < 0 || w < 0 || v > digraph.V() - 1 || w > digraph.V() - 1) {
      throw new IllegalArgumentException();
    }

    return ancestor(v, w) == -1 ? -1 : shortest;
  }

  public int ancestor(int v, int w) {
    if (v < 0 || w < 0 || v > digraph.V() - 1 || w > digraph.V() - 1) {
      throw new IllegalArgumentException();
    }

    BreadthFirstDirectedPaths breadthFirstDirectedPathsV = new BreadthFirstDirectedPaths(digraph, v);
    BreadthFirstDirectedPaths breadthFirstDirectedPathsW = new BreadthFirstDirectedPaths(digraph, w);

    shortest = Integer.MAX_VALUE;
    int ancestor = -1;

    for (int i = 0; i < digraph.V(); i++) {
      if (breadthFirstDirectedPathsV.hasPathTo(i) && breadthFirstDirectedPathsW.hasPathTo(i)) {
        int distance = breadthFirstDirectedPathsV.distTo(i) + breadthFirstDirectedPathsW.distTo(i);
        if (distance < shortest) {
          ancestor = i;
          shortest = distance;
        }
      }
    }

    return ancestor;
  }

  public int length(Iterable<Integer> v, Iterable<Integer> w) {
    if (v == null || w == null) {
      throw new IllegalArgumentException();
    }

    return ancestor(v, w) == -1 ? -1 : shortest;
  }

  public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
    if (v == null || w == null) {
      throw new IllegalArgumentException();
    }

    BreadthFirstDirectedPaths breadthFirstDirectedPathsV = new BreadthFirstDirectedPaths(digraph, v);
    BreadthFirstDirectedPaths breadthFirstDirectedPathsW = new BreadthFirstDirectedPaths(digraph, w);

    shortest = Integer.MAX_VALUE;
    int ancestor = -1;

    for (int i = 0; i < digraph.V(); i++) {
      if (breadthFirstDirectedPathsV.hasPathTo(i) && breadthFirstDirectedPathsW.hasPathTo(i)) {
        int distance = breadthFirstDirectedPathsV.distTo(i) + breadthFirstDirectedPathsW.distTo(i);
        if (distance < shortest) {
          ancestor = i;
          shortest = distance;
        }
      }
    }

    return ancestor;
  }

}
