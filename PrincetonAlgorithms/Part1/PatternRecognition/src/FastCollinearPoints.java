import java.util.Arrays;

public class FastCollinearPoints {
  private final LineSegment[] segments;
  private int size;

  public FastCollinearPoints(Point[] points) {
    if (points == null) {
      throw new IllegalArgumentException();
    }

    for (Point point : points) {
      if (point == null) {
        throw new IllegalArgumentException();
      }
    }

    for (int i = 0; i < points.length; i++) {
      for (int j = i + 1; j < points.length; j++) {
        if (points[i].compareTo(points[j]) == 0) {
          throw new IllegalArgumentException();
        }
      }
    }

    this.segments = new LineSegment[points.length];
    Point[] copy = Arrays.copyOf(points, points.length);

    for (int i = 0; i < copy.length; i++) {
      Arrays.sort(copy, points[i].slopeOrder());

      for (int j = 1; j < copy.length - 2; j++) {
        if (points[i].slopeTo(copy[j]) == points[i].slopeTo(copy[j + 1]) && points[i].slopeTo(copy[j + 1]) == points[i].slopeTo(copy[j + 2])) {
          this.segments[i] = new LineSegment(points[i], copy[j + 2]);
          size++;
        }
      }
    }
  }

  public int numberOfSegments() {
    return size;
  }

  public LineSegment[] segments() {
    return Arrays.copyOf(segments, size);
  }

}
