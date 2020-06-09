import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    /** Test isPalindrome when two characters are same */
    @Test
    public void testIsPalindrome()
    {
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("noon"));
        assertTrue(palindrome.isPalindrome("repaper"));

        assertFalse(palindrome.isPalindrome("cool"));
        assertFalse(palindrome.isPalindrome("restaurant"));
    }

    /** Test isPalindrome when two characters are off by one */
    @Test
    public void testIsPalindromeOBO()
    {
        CharacterComparator cc = new OffByOne();

        assertTrue(palindrome.isPalindrome("", cc));
        assertTrue(palindrome.isPalindrome("a", cc));
        assertTrue(palindrome.isPalindrome("mon", cc));
        assertTrue(palindrome.isPalindrome("qepaqdr", cc));

        assertFalse(palindrome.isPalindrome("cool", cc));
        assertFalse(palindrome.isPalindrome("restaurant", cc));
    }

    /** Test isPalindrome when two characters are off by N */
    @Test
    public void testIsPalindromeOBN()
    {
        CharacterComparator cc = new OffByN(5);

        assertTrue(palindrome.isPalindrome("", cc));
        assertTrue(palindrome.isPalindrome("a", cc));
        assertTrue(palindrome.isPalindrome("aof", cc));
        assertTrue(palindrome.isPalindrome("abfmkgf", cc));

        assertFalse(palindrome.isPalindrome("cooling", cc));
        assertFalse(palindrome.isPalindrome("restaurant", cc));
    }
}
// Uncomment this class once you've created your Palindrome class.