package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] is_open;
    private WeightedQuickUnionUF percolate_monitor;
    /** Special trick for the efficiency on isFull() and percolate() without backwash,
     * thanks to https://github.com/alexilyenko/Algorithms1/issues/2.
     * NOTE: in the boolean array, the index of the 1 and -1(length - 1)
     * are used to indicate the status of virtual top and virtual bottom separately.*/
    private WeightedQuickUnionUF fullness_monitor;
    private int num_open_sites;
    private int N_;

    public Percolation(int N)
    {
        if (N < 0)
        {
            throw new IllegalArgumentException();
        }
        is_open = new boolean[N * N + 2];
        percolate_monitor = new WeightedQuickUnionUF(N * N + 2);
        fullness_monitor = new WeightedQuickUnionUF(N * N + 1); // ignore the last item (virtual bottom)
        N_ = N;
    }

    public int coordinate1D(int row, int col)
    {
        return N_ * row + col + 1;
    }

    private void validate(int row, int col)
    {
        if (row < 0 || row > N_ ||
                col < 0 || col > N_)
        {
            throw new IndexOutOfBoundsException();
        }
    }

    public void open(int row, int col)
    {
        validate(row, col);
        if (isOpen(row, col))
        {
            return;
        }
        int idx = coordinate1D(row, col);
        is_open[idx] = true;
        num_open_sites++;
        // Virtual top/bottom check
        // top
        if (row == 0)
        {
            fullness_monitor.union(0, idx);
            percolate_monitor.union(0, idx);
        }
        if (row == N_ - 1)
        {
            percolate_monitor.union(idx, is_open.length - 1);
        }
        // Up
        if (row > 0)
        {
            int idx_up = coordinate1D(row - 1, col);
            if (is_open[idx_up])
            {
                percolate_monitor.union(idx_up, idx);
                fullness_monitor.union(idx_up, idx);
            }
        }
        // Left
        if (col > 0)
        {
            int idx_left = coordinate1D(row, col - 1);
            if (is_open[idx_left])
            {
                percolate_monitor.union(idx_left, idx);
                fullness_monitor.union(idx_left, idx);
            }
        }
        // Right
        if (col < N_ - 1)
        {
            int idx_right = coordinate1D(row, col + 1);
            if (is_open[idx_right])
            {
                percolate_monitor.union(idx_right, idx);
                fullness_monitor.union(idx_right, idx);
            }
        }
        // Down
        if (row < N_ - 1)
        {
            int idx_down = coordinate1D(row + 1, col);
            if (is_open[idx_down])
            {
                percolate_monitor.union(idx_down, idx);
                fullness_monitor.union(idx_down, idx);
            }
        }
    }

    public boolean isOpen(int row, int col)
    {
        validate(row, col);
        return is_open[coordinate1D(row, col)];
    }

    public boolean isFull(int row, int col)
    {
        validate(row, col);
        int idx = coordinate1D(row, col);

        //
        return fullness_monitor.find(idx) == fullness_monitor.find(0);
    }

    public boolean percolates()
    {
        return percolate_monitor.find(0) == percolate_monitor.find(is_open.length - 1);
    }

    public int numberOfOpenSites()
    {
        return num_open_sites;
    }
}
