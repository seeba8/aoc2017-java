import day8.CPU;
import org.junit.Test;
import static org.junit.Assert.*;
public class Day8Test {
    @Test
    public void testParseSingleInstruction() {
        CPU c = new CPU(new String[]{});
        c.executeInstruction("a inc 1 if b < 5");
        assertEquals(1, c.getRegisterValue("a"));
    }

    @Test
    public void testParseAllInstructions() {
        CPU c = new CPU(new String[]{
             "b inc 5 if a > 1",
             "a inc 1 if b < 5",
             "c dec -10 if a >= 1",
             "c inc -20 if c == 10"
        });
        c.executeInstructions();
        assertEquals(1, c.getRegisterValue("a"));
        assertEquals(-10, c.getRegisterValue("c"));
        assertEquals(0, c.getRegisterValue("b"));
    }

    @Test
    public void getLargestValue() {
        CPU c = new CPU(new String[]{
             "b inc 5 if a > 1",
             "a inc 1 if b < 5",
             "c dec -10 if a >= 1",
             "c inc -20 if c == 10"
        });
        c.executeInstructions();
        assertEquals(1, c.getHighestRegisterValue());
    }

    @Test
    public void getLargestEverValue() {
        CPU c = new CPU(new String[]{
             "b inc 5 if a > 1",
             "a inc 1 if b < 5",
             "c dec -10 if a >= 1",
             "c inc -20 if c == 10"
        });
        assertEquals(10, c.getHighestEverValue());
    }
}
