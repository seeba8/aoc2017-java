package day13;

import org.junit.Test;

import static org.junit.Assert.*;

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

    @Test
    public void testScannerPosition() {
        assertEquals(0, FastFirewall.getScannerPosition(0, 5));
        assertEquals(1, FastFirewall.getScannerPosition(1, 5));
        assertEquals(2, FastFirewall.getScannerPosition(2, 5));
        assertEquals(3, FastFirewall.getScannerPosition(3, 5));
        assertEquals(4, FastFirewall.getScannerPosition(4, 5));
        assertEquals(3, FastFirewall.getScannerPosition(5, 5));
        assertEquals(2, FastFirewall.getScannerPosition(6, 5));
        assertEquals(1, FastFirewall.getScannerPosition(7, 5));
        assertEquals(0, FastFirewall.getScannerPosition(8, 5));

        assertEquals(0, FastFirewall.getScannerPosition(0, 2));
        assertEquals(1, FastFirewall.getScannerPosition(1, 2));
        assertEquals(0, FastFirewall.getScannerPosition(2, 2));

        assertEquals(0, FastFirewall.getScannerPosition(0, 3));
        assertEquals(1, FastFirewall.getScannerPosition(1, 3));
        assertEquals(2, FastFirewall.getScannerPosition(2, 3));
        assertEquals(1, FastFirewall.getScannerPosition(3, 3));
        assertEquals(0, FastFirewall.getScannerPosition(4, 3));
    }

    @Test
    public void testScannerZeroRange() {
        assertEquals(-1, FastFirewall.getScannerPosition(0, 0));
    }

    @Test
    public void testFastFirewallPart1() {
        FastFirewall f = new FastFirewall();
        f.scannerRange = new int[]{3, 2, 0, 0, 4, 0, 4};
        for (int i = 0; i < 9; i++) {
            assertTrue("is not caught at " + i, f.isCaught(i));
        }
        assertFalse(f.isCaught(10));
        assertEquals(24, f.getSeverity(0));
    }
}
