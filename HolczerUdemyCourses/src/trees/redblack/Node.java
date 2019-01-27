package trees.redblack;

import static trees.redblack.Color.RED;

public class Node {
    private int data;
    private Color color;
    private Node left;
    private Node right;
    private Node parent;

    public Node(int data) {
        this.data = data;
        this.color = RED;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Node{" +
            "data=" + data +
            '}';
    }

}
