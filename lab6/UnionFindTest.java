import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UnionFindTest {
    private final int N = 8;
    @Test
    public void testParent()
    {
        UnionFind disjoint_set = new UnionFind(N);
        disjoint_set.union(3, 7);
        assertEquals(7, disjoint_set.parent(3));
    }

    @Test
    public void testSizeOf()
    {
        UnionFind disjoint_set = new UnionFind(N);
        disjoint_set.union(3, 7);
        disjoint_set.union(3, 6);
        disjoint_set.union(3, 1);
        disjoint_set.union(7, 2);

        assertEquals(5, disjoint_set.sizeOf(3));
    }

    @Test
    public void testFind()
    {
        UnionFind disjoint_set = new UnionFind(N);
        disjoint_set.union(3, 7);
        disjoint_set.union(3, 6);
        disjoint_set.union(3, 1);
        disjoint_set.union(7, 2);
        assertEquals(7, disjoint_set.find(1));
    }

    @Test
    public void testConnected()
    {
        UnionFind disjoint_set = new UnionFind(N);
        disjoint_set.union(3, 7);
        disjoint_set.union(3, 6);
        disjoint_set.union(3, 1);
        disjoint_set.union(7, 2);

        assertTrue(disjoint_set.connected(3, 2));
    }
}
