import day9.Garbage;
import day9.Group;
import day9.GarbageStream;
import org.junit.Test;
import static org.junit.Assert.*;
public class Day9Test {
    @Test
    public void testScoreRootOnly() {
        Group c = new Group();
        assertEquals(1, c.getScore());
    }

    @Test
    public void testScoreIncludingChildren() {
        Group root = new Group();
        Group middle = new Group();
        root.add(middle);
        assertEquals(3, root.getScore());
        middle.add(new Group());
        assertEquals(6, root.getScore());
        root.add(new Group());
        assertEquals(8, root.getScore());

    }
    @Test
    public void testScoreWithGarbage() {
        Group root = new Group();
        Group middle = new Group();
        root.add(middle);
        root.add(new Garbage());
        assertEquals(3, root.getScore());
        middle.add(new Group());
        middle.add(new Garbage());
        assertEquals(6, root.getScore());
        root.add(new Group());
        root.add(new Garbage());
        assertEquals(8, root.getScore());
    }

    @Test
    public void testStreamParserGroupsOnly() {
        GarbageStream s = new GarbageStream("{}");
        assertEquals(1, s.getRoot().getScore());
        s = new GarbageStream("{{{}}}");
        assertEquals(6, s.getRoot().getScore());
        s = new GarbageStream("{{},{}}");
        assertEquals(5, s.getRoot().getScore());
        s = new GarbageStream("{{{},{},{{}}}}");
        assertEquals(16, s.getRoot().getScore());
    }

    @Test
    public void testStreamParserWithGarbage() {
        GarbageStream s = new GarbageStream("{<a>,<a>,<a>,<a>}");
        assertEquals(1, s.getRoot().getScore());
        s = new GarbageStream("{{<ab>},{<ab>},{<ab>},{<ab>}}");
        assertEquals(9, s.getRoot().getScore());
        s = new GarbageStream("{{<!!>},{<!!>},{<!!>},{<!!>}}");
        assertEquals(9, s.getRoot().getScore());
        s = new GarbageStream("{{<a!>},{<a!>},{<a!>},{<ab>}}");
        assertEquals(3, s.getRoot().getScore());
    }

    @Test
    public void testGarbageSize() {
        GarbageStream s = new GarbageStream("{<>,<>,<>,<>}");
        assertEquals(0, s.getRoot().getGarbageSize());
        s = new GarbageStream("{<a>,<a>,<a>,<a>}");
        assertEquals(4, s.getRoot().getGarbageSize());
        s = new GarbageStream("{{<a!>},{<a!>},{<a!>},{<ab>}}");
        assertEquals(17, s.getRoot().getGarbageSize());
    }
}
