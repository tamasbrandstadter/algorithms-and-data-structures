package tree.prefix;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

public class Trie {
    public static final int ALPHABET_SIZE = 26;
    private Node root;
    private int indexOfSingleChild;

    public Trie() {
        this.root = new Node("");
    }

    public void insert(String word, int value) {
        Node temp = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (temp.getChildren()[index] == null) {
                Node node = new Node(valueOf(c));
                temp.setNode(index, node, value);
                temp = node;
            } else {
                temp = temp.getChildren()[index];
            }
        }

        temp.setLeaf(true);
    }

    // O(m) running time complexity - where m is the length of the word
    public boolean search(String word) {
        Node temp = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (temp.getChildren()[index] == null) {
                return false;
            } else {
                temp = temp.getChildren()[index];
            }
        }

        return true;
    }

    public List<String> allWordsWithPrefix(String prefix) {
        Node temp = root;
        List<String> words = new ArrayList<>();

        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            int index = c - 'a';
            temp = temp.getChildren()[index];
        }

        dfs(temp, prefix, words);

        return words;
    }

    public String longestCommonPrefix() {
        StringBuilder lcp = new StringBuilder();
        Node temp = root;

        while (childCount(temp) == 1 && !temp.isLeaf()) {
            temp = temp.getChildren()[indexOfSingleChild];
            lcp.append((char) (indexOfSingleChild + 'a'));
        }

        return lcp.toString();
    }

    private int childCount(Node node) {
        int childrenCount = 0;
        for (int i = 0; i < node.getChildren().length; i++) {
            if (node.getChildren()[i] != null) {
                childrenCount++;
                indexOfSingleChild = i;
            }
        }

        return childrenCount;
    }

    private void dfs(Node node, String prefix, List<String> words) {
        if (node == null) {
            return;
        }
        if (node.isLeaf()) {
            words.add(prefix);
        }

        for (Node child : node.getChildren()) {
            if (child == null) {
                continue;
            }
            dfs(child, prefix + child.getCharacter(), words);
        }
    }

}
