import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private int openSites;
  private int size;
  private WeightedQuickUnionUF uf;
  private boolean[][] grid;

  public Percolation(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException("Grid must be at least 1x1");
    }

    this.uf = new WeightedQuickUnionUF(n * n + 2);
    this.openSites = 0;
    this.size = n;
    this.grid = new boolean[n][n];

    for (int i = 1; i <= grid.length; i++) {
      uf.union(0, convertCoordinatesToId(1, i));
      uf.union(n * n + 1, convertCoordinatesToId(n, i));
    }
  }

  public void open(int row, int col) {
    if (size < row || row < 1 || col < 1 || col > size) {
      throw new IllegalArgumentException("Index must be in bounds. Min index is 1, max index is " + size);
    }

    openSites++;

    int current = convertCoordinatesToId(row, col);

    grid[row - 1][col - 1] = true;

    if (col > 1 && isOpen(row, col - 1)) {
      uf.union(current, convertCoordinatesToId(row, col - 1));
    }

    if (row < size && isOpen(row + 1, col)) {
      uf.union(current, convertCoordinatesToId(row + 1, col));
    }

    if (col < size && isOpen(row, col + 1)) {
      uf.union(current, convertCoordinatesToId(row, col + 1));
    }

    if (row > 1 && isOpen(row - 1, col)) {
      uf.union(current, convertCoordinatesToId(row - 1, col));
    }

  }

  public boolean isOpen(int row, int col) {
    return grid[row - 1][col - 1];
  }

  public boolean isFull(int row, int col) {
    return isOpen(row, col) && uf.connected(convertCoordinatesToId(row, col), 0);
  }

  public int numberOfOpenSites() {
    return openSites;
  }

  public boolean percolates() {
    return uf.connected(0, size * size + 1);
  }

  public static void main(String[] args) {

  }

  private int convertCoordinatesToId(int row, int column) {
    return (row - 1) * grid.length + column;
  }

}
