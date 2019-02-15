package tree.redblack;

import static tree.redblack.Color.BLACK;
import static tree.redblack.Color.RED;

public class RedBlackTree implements Tree {
    private Node root;

    public void traverse() {
        if (root != null) {
            traverseInOrder(root);
        }
    }

    public void insert(int newData) {
        Node node = new Node(newData);
        root = insertIntoTree(root, node);
        fixViolations(node);
    }

    private Node insertIntoTree(Node root, Node node) {
        if (root == null) {
            return node;
        }

        if (node.getData() < root.getData()) {
            root.setLeft(insertIntoTree(root.getLeft(), node));
            root.getLeft().setParent(root);
        } else if (node.getData() > root.getData()) {
            root.setRight(insertIntoTree(root.getRight(), node));
            root.getRight().setParent(root);
        }

        return root;
    }

    private void fixViolations(Node node) {
        Node parentNode;
        Node grandParentNode;
        while (node != root && node.getColor() != BLACK && node.getParent().getColor() == RED) {
            parentNode = node.getParent();
            grandParentNode = node.getParent().getParent();
            if (parentNode == grandParentNode.getLeft()) {
                Node uncle = grandParentNode.getRight();
                // given node x's parent is a left child + uncle is red --> only recoloring
                if (uncle != null && uncle.getColor() == RED) {
                    grandParentNode.setColor(RED);
                    parentNode.setColor(BLACK);
                    uncle.setColor(BLACK);
                    node = grandParentNode; // this will be the x after the recoloring because we have to check whether
                    // the properties are violated or not
                } else {
                    if (node == parentNode.getRight()) {
                        leftRotate(parentNode);
                        node = parentNode;
                        parentNode = node.getParent();
                    }
                    rightRotate(grandParentNode);
                    System.out.println("Recoroling " + parentNode + " + " + grandParentNode);
                    Color tempColor = parentNode.getColor();
                    parentNode.setColor(grandParentNode.getColor());
                    grandParentNode.setColor(tempColor);
                    node = parentNode;
                }
            } else {
                Node uncle = grandParentNode.getLeft();
                if (uncle != null && uncle.getColor() == RED) {
                    grandParentNode.setColor(RED);
                    parentNode.setColor(BLACK);
                    uncle.setColor(BLACK);
                    node = grandParentNode;
                } else {
                    if (node == parentNode.getLeft()) {
                        rightRotate(parentNode);
                        node = parentNode;
                        parentNode = node.getParent();
                    }
                    leftRotate(grandParentNode);
                    System.out.println("Recoroling " + parentNode + " + " + grandParentNode);
                    Color tempColor = parentNode.getColor();
                    parentNode.setColor(grandParentNode.getColor());
                    grandParentNode.setColor(tempColor);
                    node = parentNode;
                }
            }
        }
        if (root.getColor() == RED) {
            System.out.println("Recoloring the root to black...");
            root.setColor(BLACK);
        }
    }

    private void rightRotate(Node node) {
        System.out.println("Rotate right on node " + node);
        Node tempLeftNode = node.getLeft();
        node.setLeft(tempLeftNode.getRight());
        if (node.getLeft() != null) {
            node.getLeft().setParent(node);
        }

        tempLeftNode.setParent(node.getParent());
        if (tempLeftNode.getParent() == null) {
            root = tempLeftNode;
        } else if (node == node.getParent().getLeft()) {
            node.getParent().setLeft(tempLeftNode);
        } else {
            node.getParent().setRight(tempLeftNode);
        }

        tempLeftNode.setRight(node);
        node.setParent(tempLeftNode);
    }

    private void leftRotate(Node node) {
        System.out.println("Rotate left on node " + node);
        Node tempRightNode = node.getRight();
        node.setRight(tempRightNode.getLeft());
        if (node.getRight() != null) {
            node.getRight().setParent(node);
        }

        tempRightNode.setParent(node.getParent());
        if (tempRightNode.getParent() == null) {
            root = tempRightNode;
        } else if (node == node.getParent().getLeft()) {
            node.getParent().setLeft(tempRightNode);
        } else {
            node.getParent().setRight(tempRightNode);
        }

        tempRightNode.setLeft(node);
        node.setParent(tempRightNode);
    }

    private void traverseInOrder(Node node) {
        if (node.getLeft() != null) {
            traverseInOrder(node.getLeft());
        }
        System.out.println(node + " with color: " + node.getColor() + " LeftNode: " + node.getLeft() + " - RightNode: " + node.getRight());
        if (node.getRight() != null) {
            traverseInOrder(node.getRight());
        }
    }

}
