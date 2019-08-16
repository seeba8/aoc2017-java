package day18;

import org.junit.Assert;
import org.junit.Test;

public class DuetTest {
    @Test
    public void testDuetCompiler() {
        DuetCompiler d = DuetCompiler.fromInstructionsArray(new String[]{
                "set a 1",
                "add a 2",
                "mul a a",
                "mod a 5",
                "snd a",
                "set a 0",
                "rcv a",
                "jgz a -1",
                "set a 1",
                "jgz a -2"
        });
        Assert.assertEquals(4,d.execute());
    }
}
