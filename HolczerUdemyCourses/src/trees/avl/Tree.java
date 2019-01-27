package trees.avl;

public interface Tree<T> {
    void insert(T data);

    void traverse();

    Node<T> delete(T data);
}
