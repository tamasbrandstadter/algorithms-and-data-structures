public class Graph {
  private Vertex[] adjacents;
  private int index;
  private boolean isDirected;
  private int vertexCount;

  public Graph(int vertexCount, boolean isDirected) {
    this.vertexCount = vertexCount;
    this.isDirected = isDirected;
    this.adjacents = new Vertex[vertexCount];
    this.index = 0;
  }

  public void addVertex(String name) {
    if (index == vertexCount) {
      System.out.println("Graph is full, cannot add more vertices.");
    } else {
      this.adjacents[index++] = new Vertex(name, null);
    }
  }

  public void addEdge(String sourceVertex, String destinationVertex) {
    int sourceIndex = getIndexByName(sourceVertex);
    int destIndex = getIndexByName(destinationVertex);

    this.adjacents[sourceIndex].setAdjacent(new ListNode(destIndex, adjacents[sourceIndex].getAdjacent()));
    if (!isDirected) {
      this.adjacents[destIndex].setAdjacent(new ListNode(sourceIndex, adjacents[destIndex].getAdjacent()));
    }
  }

  private int getIndexByName(String name) {
    for (int i = 0; i < adjacents.length; i++) {
      if (adjacents[i].getName().equals(name)) {
        return i;
      }
    }
    return -1;
  }

  public void printGraph() {
    int i = 0;
    while (i < adjacents.length) {
      System.out.print(adjacents[i].getName());
      ListNode adjacent = adjacents[i].getAdjacent();
      while (adjacent != null) {
        System.out.print(" ---> " + adjacents[adjacent.val].getName());
        adjacent = adjacent.next;
      }
      System.out.println();
      i++;
    }
  }

}
