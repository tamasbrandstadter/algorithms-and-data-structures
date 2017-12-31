package leetcode;

import java.util.Stack;

/*Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
    push(x) -- Push element x onto stack.
    pop() -- Removes the element on top of the stack.
    top() -- Get the top element.
    getMin() -- Retrieve the minimum element in the stack.*/

public class MinStack {
  private int min;
  private Stack<Integer> myStack;

  public MinStack() {
    min = Integer.MAX_VALUE;
    myStack = new Stack<>();
  }

  public void push(int x) {
    if (x <= min) {
      myStack.push(min);
      min = x;
    } else {
      myStack.push(x);
    }
  }

  public void pop() {
    if (myStack.pop() == min) {
      min = myStack.pop();
    }
  }

  public int top() {
    return myStack.peek();
  }

  public int getMin() {
    return min;
  }
}
