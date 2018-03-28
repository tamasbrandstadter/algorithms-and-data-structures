import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;

import java.util.TreeSet;

public class KdTree {
  private TreeNode root;
  private TreeSet<TreeNode> tree;

  public KdTree() {
    this.tree = new TreeSet<>();
  }

  public boolean isEmpty() {
    return root == null;
  }

  public int size() {
    return tree.size();
  }

  public void insert(Point2D p) {
    if (p == null) {
      throw new IllegalArgumentException();
    }

    if (isEmpty()) {
      root = new TreeNode(p, 1);
      root.rect = new RectHV(0, 0, 1, 1);
      tree.add(root);
    } else {
      insertToPlace(p, root, root.level, root.rect);
    }
  }

  private void insertToPlace(Point2D p, TreeNode root, int level, RectHV rect) {
    if (level % 2 == 1) {
      if (p.x() < root.p.x() && root.lb == null) {
        TreeNode lb = new TreeNode(p, level + 1);
        lb.rect = new RectHV(rect.xmin(), rect.ymin(), root.p.x(), rect.ymax());
        root.lb = lb;
        tree.add(lb);
      } else if (p.x() < root.p.x() && root.lb != null) {
        insertToPlace(p, root.lb, level + 1, root.lb.rect);
      } else {
        if (root.rt == null) {
          TreeNode rt = new TreeNode(p, level + 1);
          rt.rect = new RectHV(root.p.x(), rect.ymin(), rect.xmax(), rect.ymax());
          root.rt = rt;
          tree.add(rt);
        } else {
          insertToPlace(p, root.rt, level + 1, root.rt.rect);
        }
      }
    }

    if (level % 2 == 0) {
      if (p.y() < root.p.y() && root.lb == null) {
        TreeNode lb = new TreeNode(p, level + 1);
        lb.rect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), root.p.y());
        root.lb = lb;
        tree.add(lb);
      } else if (p.y() < root.p.y() && root.lb != null) {
        insertToPlace(p, root.lb, level + 1, root.lb.rect);
      } else {
        if (root.rt == null) {
          TreeNode rt = new TreeNode(p, level + 1);
          rt.rect = new RectHV(rect.xmin(), root.p.y(), rect.xmax(), rect.ymax());
          root.rt = rt;
          tree.add(rt);
        } else {
          insertToPlace(p, root.rt, level + 1, root.rt.rect);
        }
      }
    }
  }

  public boolean contains(Point2D p) {
    if (p == null) {
      throw new IllegalArgumentException();
    }

    return !isEmpty() && (root.p.equals(p) || find(p, root));
  }

  private boolean find(Point2D p, TreeNode root) {
    if (root == null) {
      return false;
    }

    if (p.compareTo(root.p) == 0) {
      return true;
    } else if (root.level % 2 == 1) {
      if (p.x() < root.p.x()) {
        return find(p, root.lb);
      } else {
        return find(p, root.rt);
      }
    } else if (root.level % 2 == 0) {
      if (p.y() < root.p.y()) {
        return find(p, root.lb);
      } else {
        return find(p, root.rt);
      }
    }

    return false;
  }

  public void draw() {
    for (TreeNode node : tree) {
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.setPenRadius(0.01);
      node.p.draw();
      if (node.level % 2 == 1) {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius();
        StdDraw.line(node.p.x(), node.rect.ymin(), node.p.x(), node.rect.ymax());
      } else {
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius();
        StdDraw.line(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.p.y());
      }
    }
  }

  public Iterable<Point2D> range(RectHV rect) {
    if (rect == null) {
      throw new IllegalArgumentException();
    }

    Stack<Point2D> stack = new Stack<>();
    addPoints(rect, stack, root);

    return stack;
  }

  private void addPoints(RectHV rect, Stack<Point2D> stack, TreeNode root) {
    if (root == null) {
      return;
    }
    if (!rect.intersects(root.rect)) {
      return;
    }

    if (rect.contains(root.p)) {
      stack.push(root.p);
    }

    addPoints(rect, stack, root.rt);
    addPoints(rect, stack, root.lb);
  }

  public Point2D nearest(Point2D p) {
    if (p == null) {
      throw new IllegalArgumentException();
    }

    if (isEmpty()) {
      return null;
    }

    Point2D nearest = root.p;
    nearest = findNearest(root, nearest, p);

    return nearest;
  }

  private Point2D findNearest(TreeNode root, Point2D nearest, Point2D query) {
    if (root == null) {
      return nearest;
    }

    if (root.rect.distanceTo(query) > query.distanceTo(nearest)) {
      return nearest;
    }

    if (root.p.distanceTo(query) < query.distanceTo(nearest)) {
      nearest = root.p;
    }

    boolean less = root.level % 2 == 1 ? query.x() < root.p.x() : query.y() < root.p.y();

    if (less) {
      nearest = findNearest(root.lb, nearest, query);
      nearest = findNearest(root.rt, nearest, query);
    } else {
      nearest = findNearest(root.rt, nearest, query);
      nearest = findNearest(root.lb, nearest, query);
    }

    return nearest;
  }

  private static class TreeNode implements Comparable<TreeNode> {
    private Point2D p;
    private RectHV rect;
    private TreeNode lb;
    private TreeNode rt;
    private int level;

    private TreeNode(Point2D p, int level) {
      this.p = p;
      this.level = level;
    }

    @Override
    public int compareTo(TreeNode node) {
      return Integer.compare(this.p.compareTo(node.p), 0);
    }
  }

}
