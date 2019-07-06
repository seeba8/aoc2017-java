import day4.PassphraseChecker;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day4Test {
    private PassphraseChecker check;

    @Before
    public void initObjects() {
        check = new PassphraseChecker();
    }
    @Test
    public void testValidPassphrase() {
        assertTrue(check.isValid("aa bb cc dd ee"));
    }

    @Test
    public void testInvalidPassphrase() {
        assertFalse(check.isValid("aa bb cc dd aa"));
    }

    @Test
    public void testValidPassphraseWithSubstring() {
        assertTrue(check.isValid("aa bb cc dd aaa"));
    }

    @Test
    public void testNoAnagram() {
        assertTrue(check.checkNoAnagram("abcde fghij"));
        assertTrue(check.checkNoAnagram("a ab abc abd abf abj"));
        assertTrue(check.checkNoAnagram("iiii oiii ooii oooi oooo"));
    }

    @Test
    public void testContainsAnagram() {
        assertFalse(check.checkNoAnagram("abcde xyz ecdab"));
        assertFalse(check.checkNoAnagram("oiii ioii iioi iiio"));
    }


}
