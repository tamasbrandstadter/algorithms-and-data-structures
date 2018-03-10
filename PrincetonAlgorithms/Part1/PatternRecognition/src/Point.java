import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {
  private final int x;
  private final int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void draw() {
    StdDraw.point(x, y);
  }

  public void drawTo(Point that) {
    StdDraw.line(this.x, this.y, that.x, that.y);
  }

  public String toString() {
    return "(" + x + ", " + y + ")";
  }

  public int compareTo(Point that) {
    if (this.y < that.y) {
      return -1;
    } else if (this.y > that.y) {
      return +1;
    } else {
      return Integer.compare(this.x, that.x);
    }
  }

  public double slopeTo(Point that) {
    if (this.y == that.y && this.x != that.x) {
      return +0.0;
    }
    if (this.x == that.x && this.y != that.y) {
      return Double.POSITIVE_INFINITY;
    }
    if (this.compareTo(that) == 0) {
      return Double.NEGATIVE_INFINITY;
    }

    return ((double) (that.y - this.y)) / (that.x - this.x);
  }

  public Comparator<Point> slopeOrder() {
    return Comparator.comparingDouble(this::slopeTo);
  }

}
