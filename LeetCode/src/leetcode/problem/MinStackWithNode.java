package leetcode.problem;

public class MinStackWithNode {
  private MinStackNode head;

  public void push(int x) {
    head = head == null ? new MinStackNode(x, x, null) : new MinStackNode(x, Math.min(x, head.min), head);
  }

  public void pop() {
    head = head.next;
  }

  public int top() {
    return head.val;
  }

  public int getMin() {
    return head.min;
  }

  private class MinStackNode {
    private int val;
    private int min;
    private MinStackNode next;

    private MinStackNode(int val, int min, MinStackNode next) {
      this.min = min;
      this.val = val;
      this.next = next;
    }

    private MinStackNode(int val, int min) {
      this.min = min;
      this.val = val;
      this.next = null;
    }
  }

}
