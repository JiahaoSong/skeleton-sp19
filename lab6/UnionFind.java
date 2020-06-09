public class UnionFind {
    // TODO - Add instance variables?
    private int[] parent_;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        // TODO
        parent_ = new int[n];
        for (int i = 0; i < parent_.length; i++)
        {
            parent_[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        // TODO
        if (vertex < 0 || vertex >= parent_.length)
        {
            throw new IllegalArgumentException("Index is not valid.");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        int root = find(v1);
        int total = 0;
        for (int i = 0; i < parent_.length; i++)
        {
            if (find(i) == root)
            {
                total++;
            }
        }

        return total;
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        // TODO
        validate(v1);
        return parent_[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        // TODO
        int root1 = find(v1);
        int root2 = find(v2);

        if (root1 == root2)
        {
            return;
        }

        if (sizeOf(root1) > sizeOf(root2))
        {
            parent_[v2] = root1;
        }
        else
        {
            parent_[v1] = root2;
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        // TODO
        validate(vertex);
        int p = vertex;
        while (parent_[p] != -1)
        {
            p = parent_[vertex];
        }

        return p;
    }

}
