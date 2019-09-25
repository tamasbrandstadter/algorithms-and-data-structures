package problems.cpop;

import static java.lang.Double.compare;

public class Point implements Comparable<Point> {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + this.x + ";" + this.y + ")";
    }

    @Override
    public int compareTo(Point o) {
        return compare(this.getX(), o.getX());
    }

}
