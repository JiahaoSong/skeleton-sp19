/** Performs some basic linked list tests. */
public class LinkedListDequeTest {
	
	/* Utility method for printing out empty checks. */
	public static boolean checkEmpty(boolean expected, boolean actual) {
		if (expected != actual) {
			System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Utility method for printing out empty checks. */
	public static boolean checkSize(int expected, int actual) {
		if (expected != actual) {
			System.out.println("size() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Utility method for deep copy constructor. */
	public static boolean checkDeepCopy(LinkedListDeque<Integer> origin, LinkedListDeque<Integer> copied)
	{
		if (origin.size() != copied.size())
		{
			return false;
		}
		int deque_size = origin.size();

		for (int i = 0; i < deque_size; i++)
		{
			if (!(origin.get(i).equals(copied.get(i))))
			{
				return false;
			}
		}

		return true;
	}

	/* Prints a nice message based on whether a test passed. 
	 * The \n means newline. */
	public static void printTestStatus(boolean passed) {
		if (passed) {
			System.out.println("Test passed!\n");
		} else {
			System.out.println("Test failed!\n");
		}
	}

	/** Adds a few things to the list, checking isEmpty() and size() are correct, 
	  * finally printing the results. 
	  *
	  * && is the "and" operation. */
	public static void addIsEmptySizeTest() {
		System.out.println("Running add/isEmpty/Size test.");
		System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
		LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
		passed = checkSize(1, lld1.size()) && passed;
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.addLast("middle");
		passed = checkSize(2, lld1.size()) && passed;

		lld1.addLast("back");
		passed = checkSize(3, lld1.size()) && passed;

		System.out.println("Printing out deque: ");
		lld1.printDeque();

		printTestStatus(passed);
	}

	/** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
	public static void addRemoveTest() {

		System.out.println("Running add/remove test.");

		System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
		LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty 
		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty 
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		int val1 = lld1.removeFirst();
		// should be empty 
		passed = checkEmpty(true, lld1.isEmpty()) && passed;
		System.out.println(val1);

		lld1.addLast(15);
		// should not be empty
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		int val2 = lld1.removeLast();
		// should be empty
		passed = checkEmpty(true, lld1.isEmpty()) && passed;
		lld1.addFirst(10);
		lld1.addFirst(15);
		lld1.addFirst(20);
		lld1.addLast(30);
		lld1.addLast(40);
		lld1.addFirst(50);
		lld1.removeLast();
		lld1.removeFirst();

		printTestStatus(passed);
	}

	public static void addCopyTest()
	{
		System.out.println("Running copy constructor test.");

		System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
		LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
		// should be empty
		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst(10);
		lld1.addFirst(15);
		lld1.addFirst(20);
		lld1.addLast(30);
		lld1.addLast(40);
		lld1.addFirst(50);
		// should not be empty
		passed = (checkEmpty(false, lld1.isEmpty()) && passed);

		LinkedListDeque<Integer> lld2 = new LinkedListDeque<>(lld1);
		passed = (checkDeepCopy(lld1, lld2) && passed);
		printTestStatus(passed);

	}
	public static void main(String[] args) {
		System.out.println("Running tests.\n");
		addIsEmptySizeTest();
		addRemoveTest();
		addCopyTest();
	}
} 