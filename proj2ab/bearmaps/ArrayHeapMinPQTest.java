package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {
    private final int N = 100000;

    @Test
    public void testAdd()
    {
        ExtrinsicMinPQ<String> pq = new ArrayHeapMinPQ<>();
        pq.add("test1", 8.0);
        pq.add("test2", 100.0);
        pq.add("test3", 16.0);
        assertEquals(3, pq.size());
        assertEquals("test1", pq.getSmallest());
    }

    @Test
    public void testContains()
    {
        ExtrinsicMinPQ<String> pq = new ArrayHeapMinPQ<>();
        pq.add("test1", 8.0);
        pq.add("test2", 100.0);
        pq.add("test3", 16.0);
        assertTrue(pq.contains("test1"));
        assertTrue(pq.contains("test2"));
        assertTrue(pq.contains("test3"));
    }

    @Test
    public void testRemoveSmallest()
    {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
//        pq.removeSmallest(); // Null PQ sanity check
        pq.add("test1", 8.0);
        assertEquals("test1", pq.removeSmallest());
        pq.add("test2", 100.0);
        assertEquals("test2", pq.removeSmallest());
        pq.add("test3", 16.0);
        pq.add("test4", -8.0);
        assertEquals("test4", pq.removeSmallest());
        pq.add("test5", 110.0);
        pq.add("test6", 160.0);
        assertEquals(3, pq.size());
    }

    @Test
    public void testPrint()
    {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        pq.add("test1", 8.0);
        pq.add("test2", 100.0);
        pq.add("test3", 16.0);
        pq.add("test4", -8.0);
        pq.add("test5", 110.0);
        pq.add("test6", 160.0);
        pq.print();
    }

    @Test
    public void changePriority()
    {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        pq.add("test1", 8.0);
        pq.add("test2", 100.0);
        pq.add("test3", 16.0);
        pq.add("test4", -8.0);
        pq.add("test5", 110.0);
        pq.add("test6", 160.0);
        pq.print();
        assertEquals("test4", pq.getSmallest());
        pq.changePriority("test5", -200.0);
        assertEquals("test5", pq.getSmallest());
        pq.changePriority("test3", -220.0);
        assertEquals("test3", pq.getSmallest());
    }

    private double randomNum()
    {
        return 2 * Math.random() - 1;
    }

    @Test
    public void speedTest()
    {
        ExtrinsicMinPQ<Integer> naivePQ = new NaiveMinPQ<>();
        ExtrinsicMinPQ<Integer> heapPQ = new ArrayHeapMinPQ<>();

        double p = randomNum();
        for (int i = 0; i < N; i++)
        {
            naivePQ.add(i, p);
            p = randomNum();
        }
        Stopwatch naiveSw = new Stopwatch();
        while (naivePQ.size() != 0)
        {
            naivePQ.removeSmallest();
        }
        System.out.println("Total time elapsed: " + naiveSw.elapsedTime() +  " seconds.");

        p = randomNum();
        for (int i = 0; i < N; i++)
        {
            heapPQ.add(i, p);
            p = randomNum();
        }
        Stopwatch heapSw = new Stopwatch();
        while (heapPQ.size() != 0)
        {
            heapPQ.removeSmallest();
        }
        System.out.println("Total time elapsed: " + heapSw.elapsedTime() +  " seconds.");
    }

}
