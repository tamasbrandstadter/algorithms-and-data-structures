public class CircularLinkedList {
  private Node first;
  private Node last;

  public void insertFirst(int value) {
    Node newNode = new Node();
    newNode.setVal(value);

    if (isEmpty()) {
      last = newNode;
    }
    newNode.next = first;
    first = newNode;
  }

  public void insertLast(int value) {
    Node newNode = new Node();
    newNode.setVal(value);

    if (isEmpty()) {
      first = newNode;
    } else {
      last.next = newNode;
      last = newNode;
    }
  }

  public int deleteFirst() {
    int temp = first.val;

    if (first.next == null) {
      last = null;
    }

    first = first.next;

    return temp;
  }

  public void displayList() {
    Node current = first;
    while (current != null) {
      current.displayNode();
      current = current.next;
    }
  }

  private boolean isEmpty() {
    return first == null;
  }

}
