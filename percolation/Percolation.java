/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    // creates n-by-n grid, with all sites initially blocked

    private int[][] grid;
    private boolean[][] isOpenSite;
    private int N;
    private WeightedQuickUnionUF uf;
    private int numberOfOpenSites;

    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
        N = n;
        grid = new int[n][n];
        isOpenSite = new boolean[n][n];
        uf = new WeightedQuickUnionUF(n * n + 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = i * n + j + 1;
                isOpenSite[i][j] = false;
                if (i == 0) {
                    uf.union(0, j + 1);
                }

            }
        }

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > N || col < 1 || col > N)
            throw new IllegalArgumentException();
        if (!isOpen(row, col)) {
            // edge cases
            isOpenSite[row - 1][col - 1] = true;
            numberOfOpenSites++;
            if (row == 1) {
                // down
                if (isOpen(row + 1, col)) {
                    uf.union(grid[row - 1][col - 1], grid[row][col - 1]);
                }
                // right side
                if (col != N && isOpen(row, col + 1)) {
                    uf.union(grid[row - 1][col - 1], grid[row][col]);
                }
                // left side
                if (col != 1 && isOpen(row, col - 1)) {
                    uf.union(grid[row - 1][col - 1], grid[row][col - 2]);
                }
                return;
            }
            if (row == N) {
                // up
                if (isOpen(row - 1, col)) {
                    uf.union(grid[row - 1][col - 1], grid[row - 2][col - 1]);
                }
                // right side
                if (col != N && isOpen(row, col + 1)) {
                    uf.union(grid[row - 1][col - 1], grid[row - 1][col]);
                }
                // left side
                if (col != 1 && isOpen(row, col - 1)) {
                    uf.union(grid[row - 1][col - 1], grid[row - 1][col - 2]);
                }
                return;
            }
            // down
            if (isOpen(row + 1, col)) {
                uf.union(grid[row - 1][col - 1], grid[row][col - 1]);

            }
            // up
            if (isOpen(row - 1, col)) {
                uf.union(grid[row - 1][col - 1], grid[row - 2][col - 1]);

            }
            // right
            if (col != N && isOpen(row, col + 1)) {
                uf.union(grid[row - 1][col - 1], grid[row - 1][col]);

            }
            // left
            if (col != 1 && isOpen(row, col - 1)) {
                uf.union(grid[row - 1][col - 1], grid[row - 1][col - 2]);

            }
        }


    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > N || col < 1 || col > N)
            throw new IllegalArgumentException();
        return isOpenSite[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > N || col < 1 || col > N)
            throw new IllegalArgumentException();
        return uf.connected(0, grid[row - 1][col - 1]) && isOpen(row, col);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        boolean isPercolate = false;
        for (int k = 1; k <= N; k++) {
            if (isOpen(N, k) && uf.connected(0, (N - 1) * N + k)) {
                isPercolate = true;
                break;
            }
        }
        return isPercolate;
    }

    // test client (optional)
    public static void main(String[] args) {
        // new Percolation(5);
        // int n = 5;

    }
}
