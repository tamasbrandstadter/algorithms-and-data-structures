package leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class StackWithQueue {
  private Deque<Integer> queue;

  public StackWithQueue() {
    this.queue = new LinkedList<>();
  }

  public void push(int x) {
    queue.addFirst(x);
  }

  public int pop() {
    if (!queue.isEmpty()) {
      return queue.pollFirst();
    }
    return -1;
  }

  public int top() {
    if (!queue.isEmpty()) {
      return queue.peekFirst();
    }
    return -1;
  }

  public boolean empty() {
    return queue.isEmpty();
  }

}
