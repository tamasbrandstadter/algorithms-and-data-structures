public class NodeQueue<T extends Comparable<T>> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    public void enqueue(T data) {
        size++;
        if (isEmpty()) {
            first = new Node<>(data);
            last = first;
        } else {
            Node<T> oldLast = last;
            last = new Node<>(data);
            oldLast.setNext(last);
        }
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException();
        } else {
            size--;
            T oldFirstData = first.getData();
            first = first.getNext();
            if (isEmpty()) {
                last = null;
            }
            return oldFirstData;
        }
    }

    public int size() {
        return size;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException();
        } else {
            return first.getData();
        }
    }

    public boolean isEmpty() {
        return first == null;
    }
}
