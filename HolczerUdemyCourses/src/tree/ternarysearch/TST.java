package tree.ternarysearch;

public class TST {
    private Node root;

    public void put(String key, int value) {
        root = put(root, key, value, 0);
    }

    public Integer get(String key) {
        Node node = get(root, key, 0);

        if (node == null) {
            return null;
        }

        return node.getValue();
    }

    private Node put(Node node, String key, int value, int index) {
        char c = key.charAt(index);

        if (node == null) {
            node = new Node(c);
        }
        if (c < node.getCharacter()) {
            node.setLeft(put(node.getLeft(), key, value, index));
        } else if (c > node.getCharacter()) {
            node.setRight(put(node.getRight(), key, value, index));
        } else if (index < key.length() - 1){
            node.setMiddle(put(node.getMiddle(), key, value, index + 1));
        } else {
            node.setValue(value);
        }

        return node;
    }

    // running time complexity is sub-linear in case of search misses
    private Node get(Node node, String key, int index) {
        if (node == null) {
            return null;
        }

        char c = key.charAt(index);

        if (c < node.getCharacter()) {
            return get(node.getLeft(), key, index);
        } else if (c > node.getCharacter()) {
            return get(node.getRight(), key, index);
        } else if (index < key.length() - 1) {
            return get(node.getMiddle(), key, index + 1);
        } else {
            return node;
        }
    }

}
