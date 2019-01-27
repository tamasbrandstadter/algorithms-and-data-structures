package stack;

import queue.Node;

import java.util.EmptyStackException;

public class NodeStack<T extends Comparable<T>> {
    private Node<T> root;
    private int size;

    public void push(T data) {
        size++;
        if (root == null) {
            this.root = new Node<>(data);
        } else {
            Node<T> oldRoot = root;
            root = new Node<>(data);
            root.setNext(oldRoot);
        }
    }

    public T pop() {
        if (!isEmpty()) {
            size--;
            T data = root.getData();
            root = root.getNext();
            return data;
        } else {
            throw new EmptyStackException();
        }

    }

    public T peek() {
        if (!isEmpty()) {
            return root.getData();
        } else {
            throw new EmptyStackException();
        }
    }

    public boolean isEmpty() {
        return root == null && size == 0;
    }

    public int size() {
        return size;
    }

    public void print() {
        if (!isEmpty()) {
            Node<T> current = root;
            while (current != null) {
                System.out.println(current.toString());
            }
        } else {
            throw new EmptyStackException();
        }
    }

}
