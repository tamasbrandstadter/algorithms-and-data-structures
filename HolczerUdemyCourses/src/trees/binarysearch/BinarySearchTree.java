package trees.binarysearch;

public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {
    private Node<T> root;

    @Override
    public void insert(T data) {
        if (root == null) {
            root = new Node<>(data);
        } else {
            insertToPlace(data, root);
        }
    }

    private void insertToPlace(T data, Node<T> node) {
        if (data.compareTo(node.getData()) < 0) {
            if (node.getLeft() != null) {
                insertToPlace(data, node.getLeft());
            } else {
                node.setLeft(new Node<>(data));
            }
        } else {
            if (node.getRight() != null) {
                insertToPlace(data, node.getRight());
            } else {
                node.setRight(new Node<>(data));
            }
        }
    }

    @Override
    public void delete(T data) {
        if (root == null) {
            throw new IllegalArgumentException();
        } else {
            remove(data, root);
        }
    }

    private Node<T> remove(T data, Node<T> node) {
        if (node == null) {
            return node;
        }

        //searching for the node to delete
        if (data.compareTo(node.getData()) < 0) {
            node.setLeft(remove(data, node.getLeft()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(remove(data, node.getRight()));
        } else {
            //Case 1: found leaf node
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }
            //Case 2: found node with single child
            //removing right child
            if (node.getLeft() == null) {
                Node<T> temp = node.getRight();
                node = null;
                return temp;
                //removing left child
            } else if (node.getRight() == null) {
                Node<T> temp = node.getLeft();
                node = null;
                return temp;
            }
            //Case 3: found node with 2 children
            //find predecessor: rightmost(greatest) node from left subtree
            Node<T> predecessor = getPredecessor(node.getLeft());
            //update reference using previous cases
            node.setData(predecessor.getData());
            node.setLeft(remove(predecessor.getData(), node.getLeft()));
        }
        return node;
    }

    private Node<T> getPredecessor(Node<T> node) {
        if (node.getRight() == null) {
            return node;
        } else {
            return getPredecessor(node.getRight());
        }
    }

    @Override
    public T min() {
        if (root == null) {
            return null;
        } else {
            return getMin(root);
        }
    }

    private T getMin(Node<T> node) {
        if (node.getLeft() == null) {
            return node.getData();
        } else {
            return getMin(node.getLeft());
        }
    }

    @Override
    public T max() {
        if (root == null) {
            return null;
        } else {
            return getMax(root);
        }
    }

    private T getMax(Node<T> node) {
        if (node.getRight() == null) {
            return node.getData();
        } else {
            return getMax(node.getRight());
        }
    }

    @Override
    public void traverse() {
        if (root == null) {
            System.out.println("There aren't any nodes.");
        } else {
            traverseInOrder(root);
        }
    }

    private void traverseInOrder(Node<T> node) {
        if (node == null) {
            return;
        }
        traverseInOrder(node.getLeft());
        System.out.println(node.toString());
        traverseInOrder(node.getRight());
    }

    private void traversePreOrder(Node<T> node) {
        if (node == null) {
            return;
        }
        System.out.println(node.toString());
        traversePreOrder(node.getLeft());
        traversePreOrder(node.getRight());
    }

    private void traversePostOrder(Node<T> node) {
        if (node == null) {
            return;
        }
        traversePostOrder(node.getLeft());
        traversePostOrder(node.getRight());
        System.out.println(node.toString());
    }

}
