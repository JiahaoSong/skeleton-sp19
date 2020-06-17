package hw2;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestPercolation {
    @Test
    public void testTransform1D()
    {
        Percolation p = new Percolation(5);
        int expected = 13;
        int actual = p.coordinate1D(2, 2);
        assertEquals(expected, actual);
    }

    @Test
    public void testOpen()
    {
        Percolation p = new Percolation(5);
        p.open(0, 0);
        p.open(0, 2);
        p.open(0, 1);

        p.open(3, 0);
        p.open(3, 2);
        p.open(3, 1);
        p.open(4, 0);
        p.open(4, 2);
        p.open(4, 1);

        p.open(4, 4);

        assertTrue(p.isOpen(0, 0));
        assertTrue(p.isOpen(0, 1));
        assertTrue(p.isOpen(0, 2));

        assertEquals(p.numberOfOpenSites(), 10);
    }

    @Test
    public void testPercolate()
    {
        Percolation p = new Percolation(10);
        p.open(3, 6);
        p.open(3, 7);
        p.open(2, 7);
        p.open(2, 6);
        p.open(2, 5);
        p.open(3, 5);
        p.open(4, 5);
        p.open(4, 6);
        p.open(4, 7);
        p.open(4, 8);
        p.open(3, 8);
        p.open(2, 8);
        p.open(2, 9);
        p.open(3, 9);
        p.open(4, 9);
        p.open(1, 9);
        p.open(0, 9);
        p.open(5, 9);
        p.open(6, 9);
        p.open(7, 9);
        p.open(8, 9);
        p.open(9, 9);
        assertTrue(p.percolates());
    }
}
