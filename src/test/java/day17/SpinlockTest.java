package day17;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SpinlockTest {

    @Test
    public void testSetNewIndex() {
        Spinlock s = new Spinlock(3, 2017, true);
        //assertEquals(0, s.setNewIndex());
        s.insertValue(1);
        assertEquals(1, s.currentIndex);
        assertArrayEquals(new int[]{0, 1}, s.getValues());

        s.setNewIndex();
        //assertEquals(0,s.setNewIndex());
        s.insertValue(2);
        assertArrayEquals(new int[]{0,2,1}, s.getValues());

        s.setNewIndex();
       // assertEquals(1, s.setNewIndex());
        s.insertValue(3);
        assertArrayEquals(new int[]{0,2,3,1}, s.getValues());
    }

    @Test
    public void testSpin() {
        Spinlock s = new Spinlock(3, 2017);
        int[] vals = s.getValues();
        assertEquals(638, vals[s.currentIndex+1]);
        assertArrayEquals(new int[]{1512, 1134, 151, 2017, 638, 1513, 851}, Arrays.copyOfRange(vals,
                s.currentIndex-3, s.currentIndex+4));
    }
}
