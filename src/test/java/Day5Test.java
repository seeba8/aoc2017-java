import day5.JumpMaze;

import org.junit.Test;

import static org.junit.Assert.*;


public class Day5Test {
    @Test
    public void testPositionAfterJump() {
        JumpMaze j = new JumpMaze(new int[]{1, 3, 0, 1, -3});
        j.singleJump();
        assertEquals(1, j.getPosition());
    }

    @Test
    public void testInstructionsAfterJump() {
        JumpMaze j = new JumpMaze(new int[]{1, 3, 0, 1, -3});
        j.singleJump();
        assertArrayEquals(new int[]{2,3,0,1,-3}, j.getInstructions());
    }

    @Test
    public void testOutOfRangeCheck() {
        JumpMaze j = new JumpMaze(new int[]{2, 4, 0, 1, -2});
        j.setPosition(1);
        j.singleJump();
        assertFalse(j.isInBounds());
    }

    @Test
    public void testNumberOfSteps() {
        JumpMaze j = new JumpMaze(new int[]{0, 3, 0, 1, -3});
        assertEquals(5, j.execute());

    }

    @Test
    public void testPartTwoNumberOfSteps() {
        JumpMaze j = new JumpMaze(new int[]{0, 3, 0, 1, -3}, true);
        assertEquals(10, j.execute());
    }

    @Test
    public void testPartTwoFinalInstructions() {
        JumpMaze j = new JumpMaze(new int[]{0, 3, 0, 1, -3}, true);
        j.execute();
        assertArrayEquals(new int[]{2, 3, 2, 3, -1}, j.getInstructions());
    }

}
