package holczer.udemy;

public interface AVL<T> {
  void insert(T data);
  void traverse();
  AVLTreeNode<T> delete(T data);
}
