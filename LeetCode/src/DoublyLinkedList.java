public class DoublyLinkedList {
  private int val;
  private DoublyLinkedListNode first;
  private DoublyLinkedListNode last;

  private boolean isEmpty() {
    return first == null;
  }

  public void insertFirst(int val) {
    DoublyLinkedListNode node = new DoublyLinkedListNode();
    node.val = val;

    if (isEmpty()) {
      last = node;
    } else {
      first.previous = node;
    }

    node.next = first;
    first = node;
  }

  public void insertLast(int val) {
    DoublyLinkedListNode node = new DoublyLinkedListNode();
    node.val = val;

    if (isEmpty()) {
      first = node;
    } else {
      last.next = node;
      node.previous = last;
    }

    last = node;
  }

  public DoublyLinkedListNode deleteFirst() {
    DoublyLinkedListNode temp = first;

    if (first.next == null) {
      last = null;
    } else {
      first.next.previous = null;
    }

    first = first.next;

    return temp;
  }

  public DoublyLinkedListNode deleteLast() {
    DoublyLinkedListNode temp = last;

    if (first.next == null) {
      first = null;
    } else {
      last.previous.next = null;
    }

    last = last.previous;

    return temp;
  }

  public boolean insertAfter(int key, int val) {
    DoublyLinkedListNode current = first;

    while (current.val != key) {
      current = current.next;
      if (current == null) {
        return false;
      }
    }

    DoublyLinkedListNode node = new DoublyLinkedListNode();
    node.val = val;

    if (current == last) {
      current.next = null;
      last = node;
    } else {
      node.next = current.next;
      current.next.previous = node;
    }

    node.previous = current;
    current.next = node;

    return true;
  }

  public DoublyLinkedListNode deleteKey(int key) {
    DoublyLinkedListNode current = first;

    while (current.val != key) {
      current = current.next;
      if (current == null) {
        return null;
      }
    }

    if (current == first) {
      first = current.next;
      current.next.previous = null;
    } else {
      current.previous.next = current.next;
    }

    if (current == last) {
      last = current.previous;
    } else {
      current.next.previous = current.previous;
    }

    return current;

  }

  public void displayForward() {
    DoublyLinkedListNode current = first;
    while (current != null) {
      current.displayNode();
      current = current.next;
    }
  }
  
  public void displayBackward() {
    DoublyLinkedListNode current = last;
    while (current != null) {
      current.displayNode();
      current = current.previous;
    }
  }

}
