package es.datastructur.synthesizer;
import java.util.Iterator;
import java.util.List;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /** Private class ARBIterator */
    private class ARBIterator implements Iterator<T>
    {
        private int wis_pos;

        public ARBIterator()
        {
            wis_pos = first;
        }

        @Override
        public boolean hasNext()
        {
            return (! (wis_pos == last));
        }

        @Override
        public T next() {
            T item = null;
            if (hasNext())
            {
                item = rb[wis_pos];
                wis_pos = (wis_pos + 1) % capacity();;
            }
            return item;
        }
    }
    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        fillCount = 0;
        first = 0;
        last = 0;
        rb = (T[]) new Object[capacity];
    }

    /** Returns the size of the buffer */
    @Override
    public int capacity()
    {
        return rb.length;
    }

    /** Returns number of items currently in the folder */
    @Override
    public int fillCount()
    {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull())
        {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last = (last + 1) % capacity();
        fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (isEmpty())
        {
            throw new RuntimeException("Ring buffer underflow");
        }
        T return_item = rb[first];
        rb[first] = null;
        first = (first + 1) % capacity();
        fillCount--;

        return return_item;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        return rb[first];
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.

    @Override
    public Iterator<T> iterator()
    {
        return new ARBIterator();
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("[");
        for (int i = first; i != (last - 1) % capacity(); i = (i + 1) % capacity())
        {
            sb.append(rb[i].toString());
            sb.append(", ");
        }
        sb.append(rb[(last - 1) % capacity()]);
        sb.append("]");
        return sb.toString();
    }
}
    // TODO: Remove all comments that say TODO when you're done.
