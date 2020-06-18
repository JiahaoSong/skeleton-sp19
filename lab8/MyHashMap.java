import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Iterator;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private List<List<Item>> buckets_;
    private double load_factor_;
    private int size_;
    private int bucket_capacity_;
    private Set<K> key_set_;

    private class Item
    {
        public K key_;
        public V val_;

        public Item(K k, V v)
        {
            key_ = k;
            val_ = v;
        }
    }

    public MyHashMap()
    {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize)
    {
        this(initialSize, 0.75);
    }

    public MyHashMap(int initialSize, double loadFactor)
    {
        bucket_capacity_ = initialSize;
        buckets_ = new ArrayList<>(bucket_capacity_);
        for (int i = 0; i < bucket_capacity_; i++)
        {
            buckets_.add(new LinkedList<>());
        }
        key_set_ = new HashSet<>();
        load_factor_ = loadFactor;
        size_ = 0;
    }

    private int getIndex(K key)
    {
        return Math.floorMod(key.hashCode(), bucket_capacity_);
    }

    private void resize(int capacity)
    {
        bucket_capacity_ = capacity;
        List<List<Item>> new_buckets = new ArrayList<>();
        for (int i = 0; i < capacity; i++)
        {
            new_buckets.add(new LinkedList<>());
        }
        for (List<Item> bucket : new_buckets)
        {
            for (Item item : bucket)
            {
                int idx = getIndex(item.key_);
                new_buckets.get(idx).add(new Item(item.key_, item.val_));
            }
        }
        buckets_ = new_buckets;
    }

    /** Removes all of the mappings from this map. */
    public void clear()
    {
        for (List<Item> b : buckets_)
        {
            b.clear();
        }
        key_set_ = new HashSet<>();
        size_ = 0;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key)
    {
        return get(key) != null;
    }

    private Item getItem(K key)
    {
        int idx = getIndex(key);
        for (Item item : buckets_.get(idx))
        {
            if (item.key_.equals(key))
            {
                return item;
            }
        }
        return null;
    }
    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key)
    {
        Item item = getItem(key);
        if (item != null)
        {
            return item.val_;
        }
        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    public int size()
    {
        return size_;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    public void put(K key, V value)
    {
        if (containsKey(key))
        {
            Item item = getItem(key);
            item.val_ = value;
        }
        else
        {
            int idx = getIndex(key);
            buckets_.get(idx).add(new Item(key, value));
            key_set_.add(key);
            size_++;
            if (size() / bucket_capacity_ == load_factor_)
            {
                resize(bucket_capacity_ * 2);
            }
        }
    }

    /** Returns a Set view of the keys contained in this map. */
    public Set<K> keySet()
    {
        return key_set_;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key)
    {
        if (! containsKey(key))
        {
            return null;
        }
        int idx = getIndex(key);
        int tar_idx = -1;
        for (int i = 0; i < buckets_.get(idx).size(); i++)
        {
            if (buckets_.get(idx).get(i).key_.equals(key))
            {
                tar_idx = i;
                break;
            }
        }
        V val = buckets_.get(idx).get(tar_idx).val_;
        buckets_.get(idx).remove(tar_idx);
        size_--;
        return val;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value)
    {
        if (! containsKey(key))
        {
            return null;
        }
        int idx = getIndex(key);
        int tar_idx = -1;
        for (int i = 0; i < buckets_.get(idx).size(); i++)
        {
            Item item = buckets_.get(idx).get(i);
            if (item.key_.equals(key) && item.val_.equals(value))
            {
                tar_idx = i;
                break;
            }
        }
        buckets_.get(idx).remove(tar_idx);
        size_--;
        return value;
    }


    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
