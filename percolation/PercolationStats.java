/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] openSiteCount;
    private int trialCount;
    private double mean;
    private double stddeav;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();
        // perform independent trials on an n-by-n grid
        openSiteCount = new double[trials];
        trialCount = trials;
        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                // StdOut.println(row);
                // StdOut.println(col);
                perc.open(row, col);
            }
            openSiteCount[i] = (perc.numberOfOpenSites() / (double) (n * n));
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        if (mean == 0.0)
            mean = StdStats.mean(openSiteCount);
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        if (stddeav == 0.0)
            stddeav = StdStats.stddev(openSiteCount);
        return stddeav;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return (mean() - 1.96 * stddev() / Math.sqrt(trialCount));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return (mean() + 1.96 * stddev() / Math.sqrt(trialCount));
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats p = new PercolationStats(20, 100);

        StdOut.println(p.mean());
        StdOut.println(p.stddev());
        StdOut.println(p.confidenceLo());
        StdOut.println(p.confidenceHi());
    }
}
