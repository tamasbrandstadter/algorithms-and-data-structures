import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  private Percolation percolation;
  private double[] tresholds;

  public PercolationStats(int n, int trials) {
    if (n <= 0 || trials <= 0) {
      throw new IllegalArgumentException("N and trials must be greater than 0");
    }

    this.tresholds = new double[trials];

    for (int i = 0; i < trials; i++) {
      this.percolation = new Percolation(n);
      while (!percolation.percolates()) {
        int uniform = StdRandom.uniform(n) + 1;
        int uniform2 = StdRandom.uniform(n) + 1;
        if (!percolation.isOpen(uniform, uniform2)) {
          percolation.open(uniform, uniform2);
        }
      }
      tresholds[i] = (double) percolation.numberOfOpenSites() / (n * n);
    }
  }

  public double mean() {
    return StdStats.mean(tresholds);
  }

  public double stddev() {
    return StdStats.stddev(tresholds);
  }

  public double confidenceLo() {
    return mean() - 1.96 * stddev() / Math.sqrt(tresholds.length);
  }

  public double confidenceHi() {
    return mean() + 1.96 * stddev() / Math.sqrt(tresholds.length);
  }

  public static void main(String[] args) {
    PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

    StdOut.println("mean = " + percolationStats.mean());
    StdOut.println("stddev = " + percolationStats.stddev());

    StdOut.printf("95%% confidence interval = [ %f , %f ]", percolationStats.confidenceLo(), percolationStats.confidenceHi());
  }

}
