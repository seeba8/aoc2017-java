package day10;


import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
public class Day10Test {
    @Test
    public void testHashSingleStep() {
        KnotHash kh = new KnotHash(5, new int[]{3,4,1,5});
        kh.hashStep(3);
        assertArrayEquals(new int[]{2,1,0,3,4}, kh.getSparseHash());
        assertEquals(3, kh.getPosition());
        assertEquals(1, kh.getSkip());
    }

    @Test
    public void testHashSingleStepWithWrap() throws IllegalAccessException, NoSuchFieldException {
        KnotHash kh = new KnotHash(5, new int[]{3,4,1,5});
        Field field = KnotHash.class.getDeclaredField("numbers");
        field.setAccessible(true);
        field.set(kh, new int[]{2,1,0,3,4});

        field = KnotHash.class.getDeclaredField("position");
        field.setAccessible(true);
        field.set(kh, 3);

        field = KnotHash.class.getDeclaredField("skip");
        field.setAccessible(true);
        field.set(kh, 1);

        kh.hashStep(4);
        assertArrayEquals(new int[]{4,3,0,1,2}, kh.getSparseHash());
        assertEquals(3, kh.getPosition());
        assertEquals(2, kh.getSkip());
    }

    @Test
    public void testHash() {
        KnotHash kh = new KnotHash(5, new int[]{3, 4, 1, 5});
        assertEquals(12,kh.hash());
        assertArrayEquals(new int[]{3,4,2,1,0}, kh.getSparseHash());
        assertEquals(4, kh.getPosition());
        assertEquals(4, kh.getSkip());
    }

    @Test
    public void testFullHash()  {
        KnotHash kh = KnotHash.fromAsciiInput("");
        assertEquals("a2582a3a0e66e6e86e3812dcb672a272", kh.fullHash());
        kh = KnotHash.fromAsciiInput("AoC 2017");
        assertEquals("33efeb34ea91902bb2f59c9920caa6cd", kh.fullHash());
        kh = KnotHash.fromAsciiInput("1,2,3");
        assertEquals("3efbe78a8d82f29979031a4aa0b16a9d", kh.fullHash());
        kh = KnotHash.fromAsciiInput("1,2,4");
        assertEquals("63960835bcdc130f0b66d7ff4f6a5a8e", kh.fullHash());
    }
}
