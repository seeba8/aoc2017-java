import day6.MemoryBanks;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
public class Day6Test {

    @Test
    public void testGetIndexOfMaxValue() {
        MemoryBanks m = new MemoryBanks(new int[]{0,2,7,0});
        assertEquals(2, m.getBankWithMostBlocks());
    }

    @Test
    public void testRedistributionOfBank() {
        MemoryBanks m = new MemoryBanks(new int[]{0,2,7,0});
        m.distributeBank(2);
        assertArrayEquals(new int[]{2,4,1,2}, m.getBanks());
    }

    @Test
    public void testCompleteDistributionCycles() {
        MemoryBanks m = new MemoryBanks(new int[]{0,2,7,0});
        assertEquals(5, m.distributeBanks());
    }

    @Test
    public void testCompleteDistributionResult() {
        MemoryBanks m = new MemoryBanks(new int[]{0,2,7,0});
        m.distributeBanks();
        assertArrayEquals(new int[]{2,4,1,2}, m.getBanks());
    }

    @Test
    public void testSizeOfLoopWithoutPrecalculation() {
        MemoryBanks m = new MemoryBanks(new int[]{0,2,7,0});
        assertEquals(4, m.getSizeOfLoop());
    }

    @Test
    public void testSizeOfLoopWithPrecalculation() {
        MemoryBanks m = new MemoryBanks(new int[]{0,2,7,0});
        m.distributeBanks();
        assertEquals(4, m.getSizeOfLoop());
    }
}
