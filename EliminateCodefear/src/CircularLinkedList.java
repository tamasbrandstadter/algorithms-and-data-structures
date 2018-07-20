public class CircularLinkedList {
    private CircularLinkedListNode first;
    private CircularLinkedListNode last;

    public void insertFirst(int value) {
        CircularLinkedListNode newNode = new CircularLinkedListNode();
        newNode.setVal(value);

        if (isEmpty()) {
            last = newNode;
        }
        newNode.next = first;
        first = newNode;
    }

    public void insertLast(int value) {
        CircularLinkedListNode newNode = new CircularLinkedListNode();
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
        CircularLinkedListNode current = first;
        while (current != null) {
            current.displayNode();
            current = current.next;
        }
    }

    private boolean isEmpty() {
        return first == null;
    }

}
