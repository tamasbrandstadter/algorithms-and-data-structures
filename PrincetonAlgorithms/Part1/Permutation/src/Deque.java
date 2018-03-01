import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
  private Node first;
  private Node last;
  private int size;

  public Deque() {
  }

  public boolean isEmpty() {
    return first == null;
  }

  public int size() {
    return size;
  }

  public void addFirst(Item item) {
    if (item == null) {
      throw new IllegalArgumentException();
    }

    Node newNode = new Node();
    newNode.item = item;
    size++;

    if (isEmpty()) {
      first = newNode;
      last = first;
    } else {
      Node oldFirst = first;
      first.previous = newNode;
      newNode.next = oldFirst;
      first = newNode;
    }
  }

  public void addLast(Item item) {
    if (item == null) {
      throw new IllegalArgumentException();
    }
    size++;
    if (isEmpty()) {
      first = new Node();
      first.item = item;
      last = first;
    } else {
      Node oldLast = last;
      last = new Node();
      last.item = item;
      last.previous = oldLast;
      oldLast.next = last;
    }
  }

  public Item removeFirst() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    size--;

    Item oldFirstItem = first.item;
    first = first.next;
    return oldFirstItem;
  }

  public Item removeLast() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    size--;

    Node oldLast = last;
    Item oldLastItem = last.item;

    last = oldLast.previous;
    last.next = null;
    oldLast = null;

    return oldLastItem;
  }

  @Override
  public Iterator<Item> iterator() {
    return new ListIterator();
  }

  private class Node {
    Item item;
    Node next;
    Node previous;
  }

  private class ListIterator implements Iterator<Item> {
    private Node current = first;

    public boolean hasNext() {
      return current != null;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }

    public Item next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      Item item = current.item;
      current = current.next;
      return item;
    }
  }

}
