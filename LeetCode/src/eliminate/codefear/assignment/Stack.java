package eliminate.codefear.assignment;

public class Stack {
  private int top;
  private long[] numbers;
  private int maxSize;

  public Stack(int size) {
    this.maxSize = size;
    this.numbers = new long[maxSize];
    this.top = -1;
  }

  public void push(long i) {
    if (isFull()) {
      System.out.println("Stack is full, please pop an element first.");
    } else {
      top++;
      this.numbers[top] = i;
    }
  }

  public long pop() {
    if (isEmpty()) {
      System.out.println("The stack is empty, nothing to pop.");
      return -1;
    } else {
      int oldTop = top;
      top--;
      return numbers[oldTop];
    }
  }

  public long getMin() {
    int limit = top;
    long min = numbers[top];

    for (long i = numbers[limit]; i >= 0; i--) {
      if (numbers[(int) i] < min) {
        min = numbers[(int) i];
      }
    }

    return min;
  }

  public long peak() {
    return numbers[top];
  }

  public boolean isEmpty() {
    return top == -1;
  }

  private boolean isFull() {
    return maxSize - 1 == top;
  }
}
