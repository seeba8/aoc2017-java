package day13;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
public class FirewallTest {
    @Test
    public void testExampleFirewall() {
        Firewall f = new Firewall(new int[]{
                3, 2, 0, 0, 4, 0, 4
        });
        f.simulate();
        assertEquals(24, f.getSeverity());
    }

    @Test
    public void testSimulateWithoutPacket() {
        Firewall f = new Firewall(new int[]{
                3, 2, 0, 0, 4, 0, 4
        });
        f.simulateWithoutPacket(10);
        f.simulate();
        assertEquals(0, f.getSeverity());
    }

    @Test
    public void testNoSmallerDelayWorks() {
        Firewall f = new Firewall(new int[]{
                3, 2, 0, 0, 4, 0, 4
        });
        for (int i = 0; i < 10; i++) {
            f.simulateWithoutPacket(i);
            f.simulate();
            assertNotEquals(String.format("Failed at %s rounds delay", i), 0, f.wasCaught());
            f.reset();
        }
    }

    @Test
    public void findCorrectDelay() {
        Firewall f = new Firewall(new int[]{
                3, 2, 0, 0, 4, 0, 4
        });
        int delay = 0;
        do {
            delay++;
            f.reset();
            f.simulateWithoutPacket(delay);
            f.simulate();
        } while(f.wasCaught());
        assertEquals(10, delay);
    }
}
