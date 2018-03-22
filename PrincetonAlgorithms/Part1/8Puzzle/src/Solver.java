import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class Solver {
  private final MinPQ<SearchNode> minPQ;
  private final MinPQ<SearchNode> otherPQ;
  private final Stack<Board> solution;
  private final boolean solvable;

  public Solver(Board initial) {
    if (initial == null) {
      throw new IllegalArgumentException();
    }

    this.solution = new Stack<>();
    this.minPQ = new MinPQ<>(Comparator.comparingInt(searchNode -> searchNode.priority));
    this.otherPQ = new MinPQ<>(Comparator.comparingInt(searchNode -> searchNode.priority));

    for (Board board : initial.twin().neighbors()) {
      otherPQ.insert(new SearchNode(board, 1, new SearchNode(initial, 0, null)));
    }

    for (Board board : initial.neighbors()) {
      minPQ.insert(new SearchNode(board, 1, new SearchNode(initial, 0, null)));
    }

    while (!minPQ.min().board.isGoal() && !otherPQ.min().board.isGoal()) {
      SearchNode searchNode = minPQ.delMin();
      SearchNode otherSearchNode = otherPQ.delMin();

      for (Board board : searchNode.board.neighbors()) {
        if (!board.equals(searchNode.predecessor.board)) {
          minPQ.insert(new SearchNode(board, searchNode.moves + 1, searchNode));
        }
      }

      for (Board board : otherSearchNode.board.neighbors()) {
        if (!board.equals(otherSearchNode.predecessor.board)) {
          otherPQ.insert(new SearchNode(board, otherSearchNode.moves + 1, otherSearchNode));
        }
      }
    }

    this.solvable = minPQ.min().board.isGoal();

    SearchNode searchNode = minPQ.delMin();
    while (searchNode != null) {
      solution.push(searchNode.board);
      searchNode = searchNode.predecessor;
    }

  }

  public boolean isSolvable() {
    return solvable;
  }

  public int moves() {
    if (!isSolvable()) {
      return -1;
    }

    return solution.size() - 1;
  }

  public Iterable<Board> solution() {
    if (!isSolvable()) {
      return null;
    }

    return solution;
  }

  public static void main(String[] args) {
    In in = new In(args[0]);
    int n = in.readInt();
    int[][] blocks = new int[n][n];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        blocks[i][j] = in.readInt();
      }
    }

    Board initial = new Board(blocks);
    Solver solver = new Solver(initial);

    if (!solver.isSolvable()) {
      StdOut.println("No solution possible");
    } else {
      StdOut.println("Minimum number of moves = " + solver.moves());
      for (Board board : solver.solution()) {
        StdOut.println(board);
      }
    }
  }

  private class SearchNode {
    private final SearchNode predecessor;
    private final Board board;
    private final int priority;
    private final int moves;

    public SearchNode(Board board, int moves, SearchNode predecessor) {
      this.moves = moves;
      this.board = board;
      this.priority = board.manhattan() + moves;
      this.predecessor = predecessor;
    }
  }

}


