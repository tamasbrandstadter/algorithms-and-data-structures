import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.IntStream;

public class SimpleGraph {
  private LinkedList[] adjacents;
  private boolean isDirected;
  private int vertexCount;
  private int index;

  public SimpleGraph(int vertexCount, boolean isDirected) {
    this.vertexCount = vertexCount;
    this.isDirected = isDirected;
    this.adjacents = new LinkedList[vertexCount];
    this.index = 0;
  }

  public void addVertex(String vertexName) {
    if (index == vertexCount) {
      System.out.println("Cannot add more vertices.");
    } else {
      LinkedList<Vertex> list = new LinkedList<>();
      list.add(new Vertex(vertexName));
      this.adjacents[index++] = list;
    }
  }

  public void addEdge(String sourceVertex, String destinationVertex) {
    IntStream.range(0, adjacents.length).forEachOrdered(i -> {
      if (((Vertex) adjacents[i].getFirst()).getName().equals(sourceVertex)) {
        adjacents[i].add(new Vertex(destinationVertex));
      }
    });
  }

  public void print() {
    Arrays.stream(adjacents).forEach(System.out::println);
  }

  public LinkedList[] getAdjacents() {
    return adjacents;
  }
}
