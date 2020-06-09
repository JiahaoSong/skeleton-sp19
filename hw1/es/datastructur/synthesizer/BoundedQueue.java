package es.datastructur.synthesizer;

import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T>
{
    /** Returns the size of the buffer */
    int capacity();

    /** Returns number of items currently in the folder */
    int fillCount();

    /** Adds item x to the end */
    void enqueue(T x);

    /** Deletes and returns item from the front */
    T dequeue();

    /** Return (but do not pick) item from the front */
    T peek();

    /** Returns an iterator of bounded queue */
    @Override
    Iterator<T> iterator();

    /** Default methods */
    default boolean isEmpty()
    {
        return fillCount() == 0;
    }

    default boolean isFull()
    {
        return fillCount() == capacity();
    }
}
