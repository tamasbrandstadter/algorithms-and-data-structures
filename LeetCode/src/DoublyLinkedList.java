public class DoublyLinkedList {
  private int val;
  private ListNode first;
  private ListNode last;

  private boolean isEmpty() {
    return first == null;
  }

  public void insertFirst(int val) {
    ListNode node = new ListNode();
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
    ListNode node = new ListNode();
    node.val = val;

    if (isEmpty()) {
      first = node;
    } else {
      last.next = node;
      node.previous = last;
    }

    last = node;
  }

  public ListNode deleteFirst() {
    ListNode temp = first;

    if (first.next == null) {
      last = null;
    } else {
      first.next.previous = null;
    }

    first = first.next;

    return temp;
  }

  public ListNode deleteLast() {
    ListNode temp = last;

    if (first.next == null) {
      first = null;
    } else {
      last.previous.next = null;
    }

    last = last.previous;

    return temp;
  }

  public boolean insertAfter(int key, int val) {
    ListNode current = first;

    while (current.val != key) {
      current = current.next;
      if (current == null) {
        return false;
      }
    }

    ListNode node = new ListNode();
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

  public ListNode deleteKey(int key) {
    ListNode current = first;

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
    ListNode current = first;
    while (current != null) {
      current.displayNode();
      current = current.next;
    }
  }
  
  public void displayBackward() {
    ListNode current = last;
    while (current != null) {
      current.displayNode();
      current = current.previous;
    }
  }

}
