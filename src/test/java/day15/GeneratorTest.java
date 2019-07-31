package day15;

import org.junit.Test;
import static org.junit.Assert.*;
public class GeneratorTest {
    @Test
    public void testGeneratorExampleA() {
        Generator a = new Generator(65,16_807);
        assertEquals(1092455, (long)a.next());
        assertEquals(1181022009, (long)a.next());
        assertEquals(245556042, (long)a.next());
        assertEquals(1744312007, (long)a.next());
        assertEquals(1352636452, (long)a.next());
    }

    @Test
    public void testGeneratorExampleB() {
        Generator a = new Generator(8_921,48_271);
        assertEquals(430625591, (long)a.next());
        assertEquals(1233683848, (long)a.next());
        assertEquals(1431495498, (long)a.next());
        assertEquals(137874439, (long)a.next());
        assertEquals(285222916, (long)a.next());
    }


}
