package bearmaps;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private List<Node> items;
    private int size;

    private class Node
    {
        private T item;
        private double priority;

        public Node(T i, double p)
        {
            item = i;
            priority = p;
        }
    }

    public ArrayHeapMinPQ()
    {
        items = new ArrayList<>();
        items.add(null); // Dummy node
        size = 0;
    }

    private int parent(int i)
    {
        return i / 2;
    }

    private int left(int i)
    {
        return 2 * i;
    }

    private int right(int i)
    {
        return 2 * i + 1;
    }

    private int minIdx(int p, int q)
    {
        if (p > size())
        {
            return q;
        }
        if (q > size())
        {
            return p;
        }
        if (items.get(p).priority > items.get(q).priority)
        {
            return q;
        }
        return p;
    }

    private void swap(int p, int q)
    {
        Node tmp = items.get(p);
        items.set(p, items.get(q));
        items.set(q, tmp);
    }

    private void swim(int i)
    {
        if (i == 0 || i == 1)
        {
            return;
        }
        int parentIdx = parent(i);
        if (minIdx(parentIdx, i) == i)
        {
            swap(parentIdx, i);
            swim(parentIdx);
        }
    }

    private void sink(int i)
    {
        if (i > size() || left(i) > size())
        {
            return;
        }
        int minChild = minIdx(left(i), right(i));
        if (minIdx(minChild, i) == minChild)
        {
            swap(minChild, i);
            sink(minChild);
        }
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item))
        {
            throw new IllegalArgumentException("Item already in the PQ.");
        }

        items.add(size() + 1, new Node(item, priority));
        size++;
        swim(size);
    }

    private boolean contains(T item, int i)
    {
        if (i > size())
        {
            return false;
        }
        if (items.get(i).item.equals(item))
        {
            return true;
        }
        return contains(item, left(i)) || contains(item, right(i));
    }

    @Override
    public boolean contains(T item) {
        return contains(item, 1);
    }

    @Override
    public T getSmallest() {
        if (size() == 0)
        {
            throw new NoSuchElementException("There is no items in the PQ.");
        }
        return items.get(1).item;
    }

    @Override
    public T removeSmallest() {
        T returnItem = getSmallest();
        swap(1, size);
        items.set(size, null);
        size--;
        sink(1);
        return returnItem;
    }

    @Override
    public int size() {
        return size;
    }

    /** Given an item, found the index of it in the PQ */
    private int search(T item, int i)
    {
        if (i > size())
        {
            return -1;
        }
        if (item.equals(items.get(i).item))
        {
            return i;
        }
        int leftResult = search(item, left(i));
        if (leftResult != -1)
        {
            return leftResult;
        }
        return search(item, right(i));
    }
    @Override
    public void changePriority(T item, double priority) {
        int idx = search(item, 1);
        if (idx == -1)
        {
            throw new NoSuchElementException("Item is not the PQ.");
        }
        // Change its priority
        items.get(idx).priority = priority;
        // Maintain the property of heap
        swim(idx);
        sink(idx);
    }

    public void print()
    {
        Object[] arr = new Object[size() + 1];
        for (int i = 1; i < size() + 1; i++)
        {
            arr[i] = items.get(i).priority;
        }
        PrintHeapDemo.printFancyHeapDrawing(arr);
    }
}
