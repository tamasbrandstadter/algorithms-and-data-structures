package tree.prefix;

import static tree.prefix.Trie.ALPHABET_SIZE;

public class Node {
    private Node[] children;
    private String character;
    private int value;
    private boolean isLeaf;
    private boolean visited;

    public Node(String character) {
        this.children = new Node[ALPHABET_SIZE];
        this.character = character;
    }

    public void setNode(int index, Node node, int value) {
        node.setValue(value);
        children[index] = node;
    }

    public Node[] getChildren() {
        return children;
    }

    public void setChildren(Node[] children) {
        this.children = children;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

}
