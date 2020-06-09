import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;
    private int size;
    private V removed_num;

    private class Node
    {
        public K key;
        public V val;
        public Node left;
        public Node right;

        public Node(K k, V v, Node l, Node r)
        {
            key = k;
            val = v;
            left = l;
            right = r;
        }
    }

    public BSTMap()
    {
        root = null;
        size = 0;
    }
//
//    public BSTMap(K k, V v)
//    {
//        root = new Node(k, v, null, null);
//        size = 1;
//    }

    private Node search(K k, Node node)
    {
        if (node == null)
        {
            return null;
        }
        if (node.key.equals(k))
        {
            return node;
        }

        int cmp = node.key.compareTo(k);
        if (cmp < 0)
        {
            return search(k, node.right);
        }
        return search(k, node.left);
    }

    private Node insert(K k, V v, Node node)
    {
        if (node == null)
        {
            return new Node(k, v, null, null);
        }
        int cmp = node.key.compareTo(k);
        if (cmp < 0)
        {
            node.right = insert(k, v, node.right);
        }
        else if (cmp > 0)
        {
            node.left = insert(k, v, node.left);
        }
        if (node.key == k)
        {
            node.val = v;
        }
        // Return current code in order to keep recursion generalized
        return node;
    }

    private Node delete(K k, Node node)
    {
        if (node == null)
        {
            return null;
        }
        int cmp = k.compareTo(node.key);
        if (cmp < 0)
        {
            node.left = delete(k, node.left);
        }
        else if (cmp > 0)
        {
            node.right = delete(k, node.right);
        }
        // Found the key (because cmp == 0)
        else
        {
            // Memoize the removed value
            removed_num = node.val;

            if (node.left == null)
            {
                // Left subtree is null (0/1 children case)
                return node.right;
            }
            else if (node.right == null)
            {
                // Right subtree is null (0/1 children case)
                return node.left;
            }
            else
            {
                // Both of them are not null (2 children case)
                // Suppose we always swap current node with its successor
                node.right = swapWithSuccessor(node, node.right);
            }
        }
        return node;
    }

    private Node swapWithSuccessor(Node node, Node right_child)
    {
        // TODO
        if (right_child.left == null)
        {
            node.key = right_child.key;
            return right_child.right; // Because the right child subtree may or may not be null
        }
        right_child.left = swapWithSuccessor(node, right_child.left);
        return right_child;
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear()
    {
        root = null;
        size = 0;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key)
    {
        return search(key, root) != null;
    }

    /** Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key)
    {
        if (! containsKey(key))
        {
            return null;
        }
        return search(key, root).val;
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size()
    {
        return size;
    }

    /** Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value)
    {
        root = insert(key, value, root);
        size++;
    }

    private void keySet(Set<K> key_set, Node node)
    {
        if (node == null)
        {
            return;
        }
        keySet(key_set, node.left);
        key_set.add(node.key);
        keySet(key_set, node.right);
    }
    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet()
    {
        Set<K> key_set = new HashSet<>();
        keySet(key_set, root);

        return key_set;
    }

    /** Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key)
    {
        root = delete(key, root);
        size--;
        return removed_num;
    }

    /** Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator()
    {
        throw new UnsupportedOperationException();
    }

    public void printInOrder(Node node)
    {
        if (node == null)
        {
            return;
        }
        printInOrder(node.left);
        System.out.print(node.key + " ");
        printInOrder(node.right);
    }
    public void printInOrder()
    {
        printInOrder(root);
    }

}
