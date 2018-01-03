package eliminate.codefear.assignment;

import leetcode.problem.ListNode;

public class Vertex {
  private String name;
  private ListNode adjacent;

  public Vertex(String name, ListNode adjacent) {
    this.name = name;
    this.adjacent = adjacent;
  }

  public Vertex(String name) {
    this.name = name;
    this.adjacent = null;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ListNode getAdjacent() {
    return adjacent;
  }

  public void setAdjacent(ListNode adjacent) {
    this.adjacent = adjacent;
  }

  @Override
  public String toString() {
    return name;
  }
}
