import org.junit.Test;

public class SpeedTestAList {
	public static void main(String[] args) {
		ArrayDeque L = new ArrayDeque<Integer>();
		int i = 0;
		while (i < 10000000) {
			L.addLast(i);
			i = i + 1;
		}
	}

	@Test
	public void addAddRemoveTest()
	{
		ArrayDeque L = new ArrayDeque<Integer>();
		L.addLast(5);
		L.addLast(4);
		L.addFirst(3);
		L.addFirst(1);
		L.addLast(5);
		L.addLast(4);
		L.addFirst(3);
		L.addFirst(1);
		L.addLast(1);

		L.removeFirst();
		L.removeLast();
	}

	@Test
	public void addCopyTest()
	{
		ArrayDeque L = new ArrayDeque<Integer>();
		L.addLast(5);
		L.addLast(4);
		L.addFirst(3);
		L.addFirst(1);
		L.addLast(5);
		L.addLast(4);
		L.addFirst(3);
		L.addFirst(1);
		L.addLast(1);

		ArrayDeque L1 = new ArrayDeque<Integer>(L);
		L1.addLast(1000);
		L1.removeFirst();
	}
} 