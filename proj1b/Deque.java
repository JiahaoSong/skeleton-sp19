public interface Deque<Item>
{
    /** Returns the size of the linked list deque */
    int size();


    default boolean isEmpty()
    {
        return size() == 0;
    }

    /** Adds a new item at the front of the deque */
    void addFirst(Item item);

    /** Adds a new item at the back of the deque */
    void addLast(Item item);

    /** Removes the item at the front of the deque */
    Item removeFirst();

    /** Removes the item at the back of the deque */
    Item removeLast();

    /** Prints items in the deque, separated by space
     * and ends with a new line
     */
    void printDeque();

    Item get(int index);

}
