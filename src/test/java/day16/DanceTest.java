package day16;

import org.junit.Test;
import static org.junit.Assert.*;
public class DanceTest {

    @Test
    public void testDanceExample() {
        Dance d = new Dance(5);
        assertEquals("abcde", d.toString());
        d.parseInstruction("s1");
        assertEquals("eabcd", d.toString());
        d.parseInstruction("x3/4");
        assertEquals("eabdc", d.toString());
        d.parseInstruction("pe/b");
        assertEquals("baedc", d.toString());
    }
}
