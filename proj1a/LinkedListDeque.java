public class LinkedListDeque<T> {
    private class Node
    {
        public T _item;
        public Node _next;
        public Node _prev;

        public Node(T item, Node next, Node prev)
        {
            _item = item;
            _next = next;
            _prev = prev;
        }
    }

    /** NOTE: _sentinel._next is always the first item in the deque;
     * _sentinel._prev is always the last item in the deque.
     */
    private Node _sentinel;
    private int _size;

    /** Default constructor */
    public LinkedListDeque()
    {
        _sentinel = new Node(null, null, null);
        _sentinel._prev = _sentinel;
        _sentinel._next = _sentinel;
        _size = 0;
    }

    /** One parameter constructor */
    public LinkedListDeque(T item)
    {
        _sentinel = new Node(null, null, null);
        _sentinel._next = new Node(item, _sentinel, _sentinel);
        _sentinel._prev = _sentinel._next;
        _size = 1;
    }

    /** Copy constructor */
    public LinkedListDeque(LinkedListDeque<T> other)
    {
        _sentinel = new Node(null, null, null);
        _sentinel._prev = _sentinel;
        _sentinel._next = _sentinel;

        if (other == null)
        {
            return;
        }

        for (int i = 0; i < other.size(); i++)
        {
            addLast(other.get(i));
        }
//        /** Not recommended because addLast is much more simpler to understand
//         * and hides the naked recursions */
//        Node p = other._sentinel._next;
//        while (p != _sentinel)
//        {
//            addLast(p._item);
//            p = p._next;
//        }
        _size = other._size;
    }

    /** Returns the size of the linked list deque */
    public int size()
    {
        return _size;
    }

    /** Checks whether the linked list deque is empty */
    public boolean isEmpty()
    {
        return (_size == 0);
    }

    /** Adds a new item at the front of linked list deque */
    public void addFirst(T item)
    {
        /** NOTE: draw the picture of adding a double-linked node
         * and it really matters!
         * CONSIDER THREE SIDES (sentinel node, the new node and the previous first node)
         */
        _sentinel._next._prev = new Node(item, _sentinel._next, _sentinel);
        _sentinel._next = _sentinel._next._prev;
        _size++;
    }

    /** Add a new item at the last of linked list deque */
    public void addLast(T item)
    {
        /** NOTE: draw the picture of adding a double-linked node
         * and it really matters!
         * CONSIDER THREE SIDES (sentinel node, the new node and the previous last node)
         */
        _sentinel._prev._next = new Node(item, _sentinel, _sentinel._prev);
        _sentinel._prev = _sentinel._prev._next;
        _size++;
    }

    /** Removes the item at the front of linked list deque */
    public T removeFirst()
    {
        /** NOTE: draw the picture of adding a double-linked node
         * and it really matters!
         * CONSIDER THREE SIDES (sentinel node, the new node and the previous first node)
         */
        if (_size == 0)
        {
            return null;
        }
        T first_item = _sentinel._next._item;
        _sentinel._next = _sentinel._next._next;
        _sentinel._next._prev = _sentinel;

        _size--;
        return first_item;
    }

    /** Removes the item at the last of linked list deque */
    public T removeLast()
    {
        /** NOTE: draw the picture of adding a double-linked node
         * and it really matters!
         * CONSIDER THREE SIDES (sentinel node, the new node and the previous last node)
         */
        if (_size == 0)
        {
            return null;
        }
        T last_item = _sentinel._prev._item;
        _sentinel._prev = _sentinel._prev._prev;
        _sentinel._prev._next = _sentinel;
        _size--;
        return last_item;
    }

    /** Returns the index-th item of the linked list deque */
    public T get(int index)
    {
        Node p = _sentinel._next;

        for (int i = 0; i < index; i++)
        {
            p = p._next;
        }

        return p._item;
    }

    /** Helper function of getRecursive */
    public T getRecursiveAux(int index, Node rest)
    {
        if (index == 0)
        {
            return rest._item;
        }

        return getRecursiveAux(index - 1, rest._next);
    }

    /** Returns the index-th item of the linked list deque recursively */
    public T getRecursive(int index)
    {
        return getRecursiveAux(index, _sentinel._next);
    }

    /** Prints items in the linked list deque, separated by space
     * and ends with a new line
     */
    public void printDeque()
    {
        Node p = _sentinel._next;
        while (p != _sentinel)
        {
            System.out.print(p._item);
            System.out.print(" ");
            p = p._next;
        }
        System.out.println();
    }



}
