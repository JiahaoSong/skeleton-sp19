import javax.lang.model.type.ArrayType;
import java.util.Objects;
import java.util.Random;

public class ArrayDeque<Item>
{
    private Item[] items_;
    private int size_;
    private int next_first_;
    private int next_last_;

    public static final int MIN_CAPACITY = 8;
    public static final double USAGE_FACTOR_THRESH = 0.01;
    public static final int START_POS = 3;
    public static final int END_POS = 4;

    /** Default constructor */
    public ArrayDeque()
    {
        items_ = (Item[]) new Object[MIN_CAPACITY];
        size_ = 0;
        next_first_ = START_POS;
        next_last_ = END_POS;
    }

    /** One parameter constructor */
    public ArrayDeque(Item item)
    {
        items_ = (Item[]) new Object[8];
        next_first_ = START_POS;
        next_last_ = END_POS;

        addLast(item);
        size_ = 1;
    }

    /** Copy constructor */
    public ArrayDeque(ArrayDeque<Item> other)
    {
        // TODO
        size_ = other.size();
        items_ = (Item[]) new Object[other.items_.length];
        next_first_ = other.next_first_;
        next_last_ = other.next_last_;
        for (int i = 0; i < other.items_.length; i++)
        {
            items_[i] = other.items_[i];
        }

    }

    /** Lets index step forward / backward depending on the value of step */
    private int circularStep(int index, int step)
    {
        return (index + step) % items_.length;
    }

    /** Resizes the length of our array to capacity */
    public void resize(int capacity)
    {
        Item[] temp = (Item[]) new Object[capacity];
        int front = circularStep(next_first_, 1);
        int rear = circularStep(next_last_, -1);
        int p = front;
        for (int i = 0; i < size(); i++)
        {
            temp[i] = items_[p];
            p = circularStep(p, 1);
        }
        items_ = temp;
        next_first_ = items_.length - 1;
        next_last_ = size_;
    }

    /** Checks whether the items array is empty */
    public boolean isEmpty()
    {
        return size_ == 0;
    }

    /** Gets the size of the array */
    public int size()
    {
        return size_;
    }

    /** Adds the new item at the front of the array
     * Resize the array if it is already full */
    public void addFirst(Item item)
    {
        // TODO: resizing
        items_[next_first_] = item;
        size_++;
        next_first_ = circularStep(next_first_, -1);

        if (next_first_ == next_last_)
        {
            resize(size_ * 2);
        }
    }

    /** Adds the new item at the back of the array
     * Resize the array if it is already full */
    public void addLast(Item item)
    {
        // TODO: resizing
        items_[next_last_] = item;
        size_++;
        next_last_ = circularStep(next_last_, 1);

        if (next_first_ == next_last_)
        {
            resize(size_ * 2);
        }
    }

    /** Removes and returns the first item in the array */
    public Item removeFirst()
    {
        // TODO: resizing
        int first_pos = circularStep(next_first_, 1);
        Item first_item = items_[first_pos];
        items_[first_pos] = null;
        next_first_ = first_pos;
        size_--;

        if (((double) size_ / items_.length) < USAGE_FACTOR_THRESH)
        {
            resize(size_);
        }

        return first_item;
    }

    /** Removes and returns the last item in the array */
    public Item removeLast()
    {
        // TODO: resizing
        int last_pos = circularStep(next_last_, -1);
        Item last_item = items_[last_pos];
        items_[last_pos] = null;
        next_last_ = last_pos;
        size_--;

        if (size_ / items_.length < USAGE_FACTOR_THRESH)
        {
            resize(size_);
        }

        return last_item;
    }

    /** Returns the index-th item in the array
     * NOTE: the INVARIANT behind this tricky "get" is
     * users should get the index-th item according to the adding history.
     * That is, think about how user would see their array in real time!
     * not in our circular array!*/
    public Item get(int index)
    {
        int front = circularStep(next_first_, 1);
        int tar_pos = circularStep(front, index);
        return items_[tar_pos];
    }
}
