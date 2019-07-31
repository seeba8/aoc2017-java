package day15;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PickyGeneratorTest {
    @Test
    public void testGeneratorExampleA() {
        Generator a = new PickyGenerator(65,16_807, 4);
        assertEquals(1352636452, (long)a.next());
        assertEquals(1992081072, (long)a.next());
        assertEquals(530830436, (long)a.next());
        assertEquals(1980017072, (long)a.next());
        assertEquals(740335192, (long)a.next());
    }

    @Test
    public void testGeneratorExampleB() {
        Generator a = new PickyGenerator(8_921,48_271, 8);
        assertEquals(1233683848, (long)a.next());
        assertEquals(862516352, (long)a.next());
        assertEquals(1159784568, (long)a.next());
        assertEquals(1616057672, (long)a.next());
        assertEquals(412269392, (long)a.next());
    }
}
