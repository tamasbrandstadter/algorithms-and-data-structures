package tree.ternarysearch;

public class Node {
    private char c;
    private Node left;
    private Node middle;
    private Node right;
    private int value;

    public Node(char c) {
        this.c = c;
    }

    public char getCharacter() {
        return c;
    }

    public void setCharacter(char c) {
        this.c = c;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getMiddle() {
        return middle;
    }

    public void setMiddle(Node middle) {
        this.middle = middle;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
