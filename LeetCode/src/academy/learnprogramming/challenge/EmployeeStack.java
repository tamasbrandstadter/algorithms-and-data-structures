package academy.learnprogramming.challenge;

import java.util.EmptyStackException;

public class EmployeeStack {
  private Employee[] employees;
  private int top;
  private int maxSize;

  public EmployeeStack(int maxSize) {
    this.maxSize = maxSize;
    this.employees = new Employee[maxSize];
    this.top = 0;
  }

  public void push(Employee employee) {
    if (!isFull()) {
      this.employees[top++] = employee;
    }
  }

  public Employee pop() {
    if (!isEmpty()) {
      Employee employee = this.employees[--top];
      this.employees[top] = null;
      return employee;
    } else {
      throw new EmptyStackException();
    }
  }

  public Employee peek() {
    return this.employees[top - 1];
  }

  public boolean isFull() {
    return top == maxSize;
  }

  public boolean isEmpty() {
    return top == 0;
  }

  public int size() {
    return top;
  }

}
