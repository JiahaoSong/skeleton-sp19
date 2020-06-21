import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTrieSet {
    private Node root;

    private static class Node
    {
        private boolean isEnd;
        private Map<Character, Node> next;

        public Node(boolean isEnd)
        {
            this.isEnd = isEnd;
            this.next = new HashMap<>();
        }
    }

    public MyTrieSet()
    {
        root = new Node(false);
    }



    /** Clears all items out of Trie */
    public void clear()
    {
        root = new Node(false);
    }

    boolean contains(String key, Node node, int i)
    {
        if (i == key.length())
        {
            return node.isEnd;
        }
        char c = key.charAt(i);
        if (! node.next.containsKey(c))
        {
            return false;
        }
        return contains(key, node.next.get(c), i + 1);
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    boolean contains(String key)
    {
        return contains(key, root, 0);
    }

    /** Assume the key is not in the trie */
    void add(String key, Node node, int i)
    {
        if (i == key.length())
        {
            node.isEnd = true;
            return;
        }
        char c = key.charAt(i);
        if (! node.next.containsKey(c))
        {
            node.next.put(c, new Node(false));
        }
        add(key, node.next.get(c), i + 1);
    }

    /** Inserts string KEY into Trie */
    void add(String key)
    {
        if (! contains(key))
        {
            add(key, root, 0);
        }
    }

    void keys(String s, List<String> x, Node node)
    {
        if (node == null)
        {
            return;
        }
        if (node.isEnd)
        {
            x.add(s);
        }
        for (Character c : node.next.keySet())
        {
            keys(s + c, x, node.next.get(c));
        }
    }

    private Node findNode(String prefix)
    {
        Node p = root;
        for (int i = 0; i < prefix.length(); i++)
        {
            if (! p.next.containsKey(prefix.charAt(i)))
            {
                return null;
            }
            p = p.next.get(prefix.charAt(i));
        }
        return p;
    }
    /** Returns a list of all words that start with PREFIX */
    List<String> keysWithPrefix(String prefix)
    {
        List<String> listOfKeys = new ArrayList<>();
        Node start = findNode(prefix);
        if (start != null)
        {
            keys(prefix, listOfKeys, start);
        }
        return listOfKeys;
    }

    private void prefixOf(String s, List<String> x, String key, int i, Node node)
    {
        if (node == null)
        {
            return;
        }
        if (node.isEnd)
        {
            x.add(s);
        }
        char c = key.charAt(i);
        if (! node.next.containsKey(c))
        {
            return;
        }
        prefixOf(s + c, x, key, i + 1, node.next.get(c));
    }
    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public String longestPrefixOf(String key)
    {
        List<String> listOfPrefix = new ArrayList<>();
        prefixOf("", listOfPrefix, key, 0, root);
        return listOfPrefix.get(listOfPrefix.size() - 1);
    }

    /** Print all keys */
    public void printKeys()
    {
        System.out.println(keysWithPrefix(""));
    }
}
