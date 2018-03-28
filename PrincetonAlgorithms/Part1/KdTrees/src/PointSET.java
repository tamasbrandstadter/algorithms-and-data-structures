import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;

import java.util.TreeSet;

public class PointSET {
  private TreeSet<Point2D> points;

  public PointSET() {
    this.points = new TreeSet<>();
  }

  public boolean isEmpty() {
    return points.isEmpty();
  }

  public int size() {
    return points.size();
  }

  public void insert(Point2D p) {
    if (p == null) {
      throw new IllegalArgumentException();
    }

    points.add(p);
  }

  public boolean contains(Point2D p) {
    if (p == null) {
      throw new IllegalArgumentException();
    }

    return points.contains(p);
  }

  public void draw() {
    points.forEach(Point2D::draw);
  }

  public Iterable<Point2D> range(RectHV rect) {
    if (rect == null) {
      throw new IllegalArgumentException();
    }

    Stack<Point2D> insidePoints = new Stack<>();

    points.forEach(point -> {
      if (rect.contains(point)) {
        insidePoints.push(point);
      }
    });

    return insidePoints;
  }

  public Point2D nearest(Point2D p) {
    if (p == null) {
      throw new IllegalArgumentException();
    }

    if (isEmpty()) {
      return null;
    } else {
      TreeSet<Point2D> sorted = new TreeSet<>(p.distanceToOrder());
      sorted.addAll(points);
      return sorted.first();
    }
  }

}
