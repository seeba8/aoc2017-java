import day3.Spiral;
import org.junit.Test;
import utils.Point;

import static org.junit.Assert.assertEquals;

public class Day3Test {
    @Test
    public void testRing() {
        assertEquals(0, Spiral.getRing(1));
        assertEquals(1, Spiral.getRing(9));
        assertEquals(1, Spiral.getRing(8));
        assertEquals(2, Spiral.getRing(10));
        assertEquals(2, Spiral.getRing(23));
    }

    @Test
    public void testOffset() {
        assertEquals(0, Spiral.getOffsetFromAxis(2));
        assertEquals(0, Spiral.getOffsetFromAxis(6));
        assertEquals(1, Spiral.getOffsetFromAxis(9));
        assertEquals(2, Spiral.getOffsetFromAxis(13));
        assertEquals(0, Spiral.getOffsetFromAxis(23));
    }

    @Test
    public void testNumberOfSteps() {
        assertEquals(0,Spiral.getNumberOfSteps(1));
        assertEquals(3,Spiral.getNumberOfSteps(12));
        assertEquals(2,Spiral.getNumberOfSteps(23));
        assertEquals(31,Spiral.getNumberOfSteps(1024));
    }

    @Test
    public void testPart1() {
        assertEquals(552, Spiral.getNumberOfSteps(325489));
    }

    @Test
    public void testPart1ViaPosition(){
        Point<Long> p = Spiral.getPosition(325489);
        assertEquals(552,Math.abs(p.x)+Math.abs(p.y) );
    }

    @Test
    public void testPosition() {
        assertEquals(new Point<Long>(0L, 0L), Spiral.getPosition(1));
        assertEquals(new Point<Long>(1L, 0L), Spiral.getPosition(2));
        assertEquals(new Point<Long>(1L, 1L), Spiral.getPosition(3));
        assertEquals(new Point<Long>(-1L, 1L), Spiral.getPosition(5));
        assertEquals(new Point<Long>(-1L, -1L), Spiral.getPosition(7));
        assertEquals(new Point<Long>(1L, -1L), Spiral.getPosition(9));
        assertEquals(new Point<Long>(0L, 2L), Spiral.getPosition(15));
        assertEquals(new Point<Long>(-2L, 0L), Spiral.getPosition(19));
        assertEquals(new Point<Long>(-2L, -2L), Spiral.getPosition(21));
        assertEquals(new Point<Long>(2L, -1L), Spiral.getPosition(10));
    }

    @Test
    public void testFirstValueLargerThan() {
        assertEquals(4, Spiral.getFirstValueLargerThan(3));
        assertEquals(122, Spiral.getFirstValueLargerThan(66));
        assertEquals(806, Spiral.getFirstValueLargerThan(747));
    }
}
