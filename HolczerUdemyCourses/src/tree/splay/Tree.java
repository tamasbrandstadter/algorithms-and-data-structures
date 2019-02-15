package tree.splay;

public interface Tree<T extends Comparable<T>> {
    void insert(T data);
    Node<T> find(T data);
    T getMin();
    T getMax();
    void inOrderTraversal();
}

