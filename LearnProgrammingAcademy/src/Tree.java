public class Tree {
    private TreeNode root;

    public void insert(int value) {
        if (root == null) {
            root = new TreeNode(value);
        } else {
            root.insert(value);
        }
    }

    public void traverseInOrder() {
        if (root != null) {
            root.traverseInOrder();
        }
    }

    public void traversePreOrder() {
        if (root != null) {
            root.traversePreOrder();
        }
    }

    public void traversePostOrder() {
        if (root != null) {
            root.traversePostOrder();
        }
    }

    public TreeNode get(int value) {
        if (root != null) {
            return root.get(value);
        }

        return null;
    }

    public int getMin() {
        if (root != null) {
            return root.getMin();
        }

        return Integer.MIN_VALUE;
    }

    public int getMax() {
        if (root != null) {
            return root.getMax();
        }

        return Integer.MAX_VALUE;
    }

    public void delete(int value) {
        root = delete(root, value);
    }

    private TreeNode delete(TreeNode subTreeRoot, int value) {
        if (subTreeRoot == null) {
            return subTreeRoot;
        }

        if (value < subTreeRoot.getValue()) {
            subTreeRoot.setLeft(delete(subTreeRoot.getLeft(), value));
        } else if (value > subTreeRoot.getValue()) {
            subTreeRoot.setRight(delete(subTreeRoot.getRight(), value));
        } else {
            //Case 1 and 2: node to delete is either a leaf or has only 1 child
            if (subTreeRoot.getLeft() == null) {
                return subTreeRoot.getRight();
            } else if (subTreeRoot.getRight() == null) {
                return subTreeRoot.getLeft();
            }
            //Case 3: node to delete has 2 children
            //Replace the value in subTreeRoot node with the smallest value from the right subtree
            subTreeRoot.setValue(subTreeRoot.getRight().getMin());
            //Delete the node that has the smallest value in the right subtree
            subTreeRoot.setRight(delete(subTreeRoot.getRight(), subTreeRoot.getValue()));
        }

        return subTreeRoot;
    }

}
