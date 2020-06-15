import org.junit.Test;
import static org.junit.Assert.*;

public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y)
    {
        return (Math.abs(y - x) == 1);
    }
}
