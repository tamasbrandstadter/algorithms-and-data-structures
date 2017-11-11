public class Queue {
  private int[] numbers;
  private int front;
  private int rear;
  private int nElements;
  private int maxSize;

  public Queue(int size) {
    this.maxSize = size;
    this.numbers = new int[maxSize];
    this.nElements = 0;
    this.front = 0;
    this.rear = -1;
  }

  public void insert(int x) {
    if (isFull()) {
      rear = -1;
    }
    rear++;
    numbers[rear] = x;
    nElements++;
  }

  public int remove() {
    int temp = numbers[front];
    front++;
    if (front == maxSize) {
      front = 0;
    }
    nElements--;
    return temp;
  }

  public int peak() {
    return numbers[front];
  }

  public boolean isFull() {
    return this.nElements == maxSize;
  }

  public boolean isEmpty() {
    return this.nElements == 0;
  }

  public void view() {
    System.out.print("[");
    for (int i = 0; i < numbers.length; i++) {
      System.out.print(numbers[i] + " ");
    }
    System.out.print("]");
  }

}
