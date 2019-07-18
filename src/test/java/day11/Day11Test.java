package day11;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class Day11Test {
    @Test
    public void testDistance() {
        HexGridPoint h = new HexGridPoint();
        h.go(new Direction[]{
                Direction.NE, Direction.NE, Direction.NE
        });
        assertEquals(3, h.getDistance());

        h = new HexGridPoint();
        h.go(new Direction[]{
                Direction.NE, Direction.NE, Direction.SW, Direction.SW
        });
        assertEquals(0, h.getDistance());

        h = new HexGridPoint();
        h.go(new Direction[]{
                Direction.NE, Direction.NE, Direction.S, Direction.S
        });
        assertEquals(2, h.getDistance());

        h = new HexGridPoint();
        h.go(new Direction[]{
                Direction.SE, Direction.SW, Direction.SE, Direction.SW, Direction.SW
        });
        assertEquals(3, h.getDistance());
    }


}
