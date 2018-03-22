import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Board {
  private Board twin;
  private final int[][] blocks;
  private final int n;
  private int hammingDistance;
  private int manhattanDistance;
  private int coordinateX;
  private int coordinateY;

  public Board(int[][] blocks) {
    this.n = blocks.length;

    for (int i = 0; i < blocks.length; i++) {
      for (int j = 0; j < blocks[i].length; j++) {
        if (blocks[i][j] == 0) {
          this.coordinateX = i;
          this.coordinateY = j;
          continue;
        }

        if (blocks[i][j] != i * n + j + 1) {
          this.hammingDistance++;
        }

        int number = blocks[i][j] - 1;
        int goalICoordinate = number / n;
        int goalJCoordinate = blocks[i][j] - goalICoordinate * n - 1;

        this.manhattanDistance += Math.abs(goalICoordinate - i);
        this.manhattanDistance += Math.abs(goalJCoordinate - j);
      }
    }

    this.blocks = blocks;
  }

  public int dimension() {
    return n;
  }

  public int hamming() {
    return hammingDistance;
  }

  public int manhattan() {
    return manhattanDistance;
  }

  public boolean isGoal() {
    return manhattanDistance == 0;
  }

  public Board twin() {
    if (twin == null) {
      int uniform = StdRandom.uniform(n);
      int uniform2 = StdRandom.uniform(n);

      while (blocks[uniform][uniform2] == 0) {
        uniform = StdRandom.uniform(n);
        uniform2 = StdRandom.uniform(n);
      }

      int uniform3 = StdRandom.uniform(n);
      int uniform4 = StdRandom.uniform(n);

      while (blocks[uniform3][uniform4] == 0) {
        uniform3 = StdRandom.uniform(n);
        uniform4 = StdRandom.uniform(n);
      }

      int[][] swapped = swap(uniform, uniform2, uniform3, uniform4);

      twin = new Board(swapped);
    }

    return twin;
  }

  public Iterable<Board> neighbors() {
    Stack<Board> stack = new Stack<>();

    if (coordinateX > 0) {
      int[][] swapped = swap(coordinateX, coordinateY, coordinateX - 1, coordinateY);
      stack.push(new Board(swapped));
    }

    if (coordinateX < n - 1) {
      int[][] swapped = swap(coordinateX, coordinateY, coordinateX + 1, coordinateY);
      stack.push(new Board(swapped));
    }

    if (coordinateY < n - 1) {
      int[][] swapped = swap(coordinateX, coordinateY, coordinateX, coordinateY + 1);
      stack.push(new Board(swapped));
    }

    if (coordinateY > 0) {
      int[][] swapped = swap(coordinateX, coordinateY, coordinateX, coordinateY - 1);
      stack.push(new Board(swapped));
    }

    return stack;
  }

  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append(n).append("\n");

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        s.append(String.format("%2d ", blocks[i][j]));
      }
      s.append("\n");
    }

    return s.toString();
  }

  public boolean equals(Object y) {
    if (y == this) {
      return true;
    }
    if (y == null) {
      return false;
    }
    if (y.getClass() != this.getClass()) {
      return false;
    }

    Board that = (Board) y;

    return IntStream.range(0, that.blocks.length).allMatch(k -> Arrays.equals(this.blocks[k], that.blocks[k]));
  }

  private int[][] swap(int i, int j, int k, int m) {
    int[][] swappedBlocks = Arrays.stream(blocks)
        .map(block -> Arrays.copyOf(block, blocks.length))
        .toArray(int[][]::new);

    int temp = swappedBlocks[i][j];
    swappedBlocks[i][j] = swappedBlocks[k][m];
    swappedBlocks[k][m] = temp;

    return swappedBlocks;
  }

}
