package leetcode.problem;

public class TreeNode {
  private int value;
  private TreeNode left;
  private TreeNode right;

  public TreeNode(int value) {
    this.value = value;
  }

  public void insert(int data) {
    if (data == value) {
      return;
    }

    if (value > data) {
      if (left == null) {
        left = new TreeNode(data);
      } else {
        left.insert(data);
      }
    } else {
      if (right == null) {
        right = new TreeNode(data);
      } else {
        right.insert(data);
      }
    }
  }

  public TreeNode get(int data) {
    if (value == data) {
      return this;
    }

    if (value > data) {
      if (left != null) {
        return left.get(data);
      }
    } else {
      if (right != null) {
        return right.get(data);
      }
    }

    return null;
  }

  public int getMin() {
    if (left.getLeft() == null) {
      return left.getValue();
    } else {
      return left.getMin();
    }
  }

  public int getMax() {
    if (right.getRight() == null) {
      return right.getValue();
    } else {
      return right.getMax();
    }
  }

  //from smallest to highest (sorted)
  public void traverseInOrder() {
    if (left != null) {
      left.traverseInOrder();
    }
    System.out.println(value + " ");
    if (right != null) {
      right.traverseInOrder();
    }
  }

  //root -> left subtree -> right subtree
  public void traversePreOrder(){
    System.out.println(value + " ");
    if (left != null) {
      left.traversePreOrder();
    }
    if (right != null) {
      right.traversePreOrder();
    }
  }

  //from leaves to root, left and right
  public void traversePostOrder(){
    if (left != null) {
      left.traversePostOrder();
    }
    if (right != null) {
      right.traversePostOrder();
    }
    System.out.println(value + " ");
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public TreeNode getLeft() {
    return left;
  }

  public void setLeft(TreeNode left) {
    this.left = left;
  }

  public TreeNode getRight() {
    return right;
  }

  public void setRight(TreeNode right) {
    this.right = right;
  }

  @Override
  public String toString() {
    return "Value of node is: " + value;
  }

}
