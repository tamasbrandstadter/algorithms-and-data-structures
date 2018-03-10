import java.util.Arrays;

public class BruteCollinearPoints {
  private final LineSegment[] segments;
  private int size;

  public BruteCollinearPoints(Point[] points) {
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

    Point[] sortedPoints = Arrays.copyOf(points, points.length);
    Arrays.sort(sortedPoints);
    this.segments = new LineSegment[points.length];

    for (int i = 0; i < sortedPoints.length - 3; i++) {
      for (int j = i + 1; j < sortedPoints.length - 2; j++) {
        for (int k = j + 1; k < sortedPoints.length - 1; k++) {
          for (int p = k + 1; p < sortedPoints.length; p++) {
            if (sortedPoints[i].slopeTo(sortedPoints[j]) == sortedPoints[i].slopeTo(sortedPoints[k])
                && sortedPoints[i].slopeTo(sortedPoints[j]) == sortedPoints[i].slopeTo(sortedPoints[p])) {
              this.segments[size++] = new LineSegment(sortedPoints[i], sortedPoints[p]);
            }
          }
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
