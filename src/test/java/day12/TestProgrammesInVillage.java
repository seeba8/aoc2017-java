package day12;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class TestProgrammesInVillage {
    @Test
    public void testCountRecursive() {
        Programme root = new Programme();
        Programme child1 = new Programme();
        root.addChild(child1);
        Programme grandchild1 = new Programme();
        Programme grandchild2 = new Programme();
        child1.addChild(grandchild1);
        child1.addChild(grandchild2);
        assertEquals(4, root.countChildren());
    }

    @Test
    public void testCountRecursiveReoccurring() {
        Programme[] programmes = new Programme[7];
        for (int i = 0; i < 7; i++) {
            programmes[i] = new Programme();
        }
        programmes[0].addChild(programmes[2]);
        programmes[1].addChild(programmes[1]);
        programmes[2].addChild(programmes[0]);
        programmes[2].addChild(programmes[3]);
        programmes[2].addChild(programmes[4]);
        programmes[3].addChild(programmes[2]);
        programmes[3].addChild(programmes[4]);
        programmes[4].addChild(programmes[2]);
        programmes[4].addChild(programmes[3]);
        programmes[4].addChild(programmes[6]);
        programmes[5].addChild(programmes[6]);
        programmes[6].addChild(programmes[4]);
        programmes[6].addChild(programmes[5]);
        assertEquals(6, programmes[0].countChildren());
    }

    @Test
    public void testGroupCount() {
        Village v = new Village(new int[][]{
                {2},
                {1},
                {0, 3, 4},
                {2, 4},
                {2, 3, 6},
                {6},
                {4, 5}
        });
        assertEquals(2, v.getGroupCount());
    }
}
