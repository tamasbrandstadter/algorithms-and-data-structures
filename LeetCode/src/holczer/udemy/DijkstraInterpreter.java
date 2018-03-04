package holczer.udemy;

import java.util.Stack;

public class DijkstraInterpreter {
  Stack<String> operators;
  Stack<Double> operands;

  public DijkstraInterpreter() {
    this.operators = new Stack<>();
    this.operands = new Stack<>();
  }

  public double evaluate(String expression) {
    String[] parts = expression.split(" ");

    for (String part : parts) {

      if (part.equals("(")) {
        continue;
      } else if (part.equals("*") || part.equals("+")) {
        operators.push(part);
      } else if (part.equals(")")) {
        if (operators.pop().equals("+")) {
          operands.push(operands.pop() + operands.pop());
        } else {
          operands.push(operands.pop() * operands.pop());
        }
      } else {
        operands.push(Double.parseDouble(part));
      }
    }

    return operands.pop();
  }

}
