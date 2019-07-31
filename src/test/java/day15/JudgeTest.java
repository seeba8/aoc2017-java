package day15;

import org.junit.Test;
import static org.junit.Assert.*;

public class JudgeTest {

    @Test
    public void testBitComparison() {
        assertTrue((245556042 & 0xffff) == (1431495498 & 0xffff));
        assertFalse((1744312007 & 0xffff) == (137874439 & 0xffff));
        assertFalse((1352636452 & 0xffff) == (285222916 & 0xffff));
        assertFalse((1181022009 & 0xffff) == (1233683848 & 0xffff));
        assertFalse((1092455 & 0xffff) == (430625591 & 0xffff));
    }

    @Test
    public void testSimpeJudge() {
        Judge j = new Judge(65, 8_921);
        assertEquals(1, j.compareResults(5));
    }

    @Test
    public void testJudgeGeneratedNumbers() {
        Judge j = new Judge(65L, 8_921L);
        j.a = new Generator(65L, 16_807);
        assertEquals(1092455, (long)j.a.next());
        assertEquals(430625591, (long)j.b.next());
    }

    @Test
    public void testExamplePart1() {
        Judge j = new Judge(65, 8_921);
        assertEquals(588, j.compareResults(40_000_000));
    }
    @Test
    public void testExamplePart2() {
        Judge j = new Judge(65, 8_921, true);
        assertEquals(309, j.compareResults(5_000_000));
    }

    @Test
    public void testPickyGenerators() {
        Judge j = new Judge(65, 8_921, true);
        assertEquals(0, j.compareResults(1055));
        j = new Judge(65, 8_921, true);
        assertEquals(1, j.compareResults(1056));
    }

}
