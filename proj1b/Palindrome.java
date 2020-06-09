public class Palindrome {
    public Deque<Character> wordToDeque(String word)
    {
        LinkedListDeque<Character> deque = new LinkedListDeque<>();

        for (int i = 0; i < word.length(); i++)
        {
            deque.addLast(word.charAt(i));
        }

        return deque;
    }

    private boolean isPalindrome(Deque<Character> word_deque)
    {
        if (word_deque.isEmpty())
        {
            return true;
        }

        Character left = word_deque.removeFirst();
        if (word_deque.isEmpty())
        {
            return true;
        }
        Character right = word_deque.removeLast();
        if (word_deque.isEmpty())
        {
            return true;
        }

        return (left == right) && isPalindrome(word_deque);
    }

    public boolean isPalindrome(String word)
    {
        return isPalindrome(wordToDeque(word));
    }

    private boolean isPalindrome(Deque<Character> word_deque, CharacterComparator cc)
    {
        if (word_deque.isEmpty())
        {
            return true;
        }
        char left = word_deque.removeFirst();
        if (word_deque.isEmpty())
        {
            return true;
        }
        char right = word_deque.removeLast();
        if (word_deque.isEmpty())
        {
            return true;
        }
        return cc.equalChars(left, right) && isPalindrome(word_deque, cc);
    }

    public boolean isPalindrome(String word, CharacterComparator cc)
    {
        return isPalindrome(wordToDeque(word), cc);
    }
}
