package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testEnqueueDequeuePeek() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        arb.enqueue(5);
        arb.enqueue(4);
        arb.enqueue(3);
        arb.enqueue(2);
        arb.enqueue(5);
        arb.enqueue(4);
        arb.enqueue(3);
        arb.enqueue(2);
        assertEquals(arb.fillCount(), 8);

        int x = arb.dequeue();
        assertEquals(x, 5);
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        assertEquals(arb.fillCount(), 4);

        int x2 = arb.peek();
        assertEquals(x2, 5);
    }

    @Test
    public void testIteration()
    {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        arb.enqueue(5);
        arb.enqueue(4);
        arb.enqueue(3);
        arb.enqueue(2);
        arb.enqueue(5);
        arb.enqueue(4);
        arb.enqueue(3);
        arb.enqueue(2);

        for (int x : arb)
        {
            System.out.println(x);
        }
    }

    @Test
    public void testToString()
    {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        arb.enqueue(5);
        arb.enqueue(4);
        arb.enqueue(3);
        arb.enqueue(2);
        arb.enqueue(5);
        arb.enqueue(4);
        arb.enqueue(3);
        arb.enqueue(2);

        System.out.println(arb);
    }
}
