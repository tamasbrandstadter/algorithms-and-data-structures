import java.util.stream.IntStream;

public class Graph {
  private Vertex[] vertices;
  private int index;
  private int vertexCount;
  private boolean isDirected;

  public Graph(int vertexCount, boolean isDirected) {
    this.vertexCount = vertexCount;
    this.isDirected = isDirected;
    this.vertices = new Vertex[vertexCount];
    this.index = 0;
  }

  public void addVertex(String name) {
    if (index == vertexCount) {
      System.out.println("Graph is full, cannot add more vertices.");
    } else {
      this.vertices[index++] = new Vertex(name, null);
    }
  }

  public void addEdge(String sourceVertex, String destinationVertex) {
    int sourceIndex = getIndexByName(sourceVertex);
    int destIndex = getIndexByName(destinationVertex);

    this.vertices[sourceIndex].setAdjacent(new ListNode(destIndex, vertices[sourceIndex].getAdjacent()));
    if (!isDirected) {
      this.vertices[destIndex].setAdjacent(new ListNode(sourceIndex, vertices[destIndex].getAdjacent()));
    }
  }

  private int getIndexByName(String name) {
    return IntStream.range(0, vertices.length)
        .filter(i -> vertices[i].getName().equals(name))
        .findFirst()
        .orElse(-1);
  }

  public void printGraph() {
    int i = 0;
    while (i < vertices.length) {
      System.out.print(vertices[i].getName());
      ListNode adjacent = vertices[i].getAdjacent();
      while (adjacent != null) {
        System.out.print(" ---> " + vertices[adjacent.val].getName());
        adjacent = adjacent.next;
      }
      System.out.println();
      i++;
    }
  }

}
