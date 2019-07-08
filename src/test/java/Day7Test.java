import day7.Program;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class Day7Test {

    @Test
    public void testMapOfProgramsFromString() {
        Map<String, String> expected = new HashMap<>();
        expected.put("fwft", "(72) -> ktlj, cntj, xhth");
        expected.put("ktlj", "(57)");
        assertEquals(expected, Program.intoMap(new String[]{
                "ktlj (57)",
                "fwft (72) -> ktlj, cntj, xhth"
        }));
    }

    @Test
    public void testFindRootElement() {
        Map<String, String> programs = new HashMap<>();
        programs.put("fwft", "(72) -> ktlj, cntj, xhth");
        programs.put("ktlj", "(57)");
        programs.put("tknk", "(41) -> ugml, padx, fwft");

        assertEquals("tknk", Program.findRootProgram(programs));
    }

    @Test
    public void testGetChildrenFromValue() {
        String[] expected = new String[] {"ktlj", "cntj", "xhth"};
        assertArrayEquals(expected, Program.getChildrenFromValue("(72) -> ktlj, cntj, xhth"));
    }

    @Test
    public void testBuildProgramTree() {
        Map<String, String> programs = new HashMap<>();
        programs.put("ugml", "(68) -> gyxo, ebii");
        programs.put("gyxo", "(61)");
        programs.put("ebii", "(61)");
        programs.put("fwft", "(72)");
        programs.put("tknk", "(41) -> ugml, fwft");
        Program p = new Program(programs, "tknk");
        assertEquals("tknk", p.getName());
        assertEquals(41, p.getWeight());
        assertEquals(2, p.getChildren().size());
        assertTrue("ugml,fwft".contains(p.getChildren().get(0).getName()));
        assertTrue("ugml,fwft".contains(p.getChildren().get(1).getName()));
    }

    @Test
    public void testGetCollectiveWeight() {
        Map<String, String> programs = new HashMap<>();
        programs.put("ugml", "(68) -> gyxo, ebii");
        programs.put("gyxo", "(61)");
        programs.put("ebii", "(61)");
        programs.put("fwft", "(72)");
        programs.put("tknk", "(41) -> ugml, fwft");
        Program p = new Program(programs, "tknk");
        assertEquals(303, p.getWeight(true));
    }

    @Test
    public void testGetUnbalancedValue() {
        Map<String, String> programs = new HashMap<>();
        programs.put("pbga", "(66)");
        programs.put("xhth", "(57)");
        programs.put("ebii", "(61)");
        programs.put("havc", "(66)");
        programs.put("ktlj", "(57)");
        programs.put("fwft", "(72) -> ktlj, cntj, xhth");
        programs.put("qoyq", "(66)");
        programs.put("padx", "(45) -> pbga, havc, qoyq");
        programs.put("tknk", "(41) -> ugml, padx, fwft");
        programs.put("jptl", "(61)");
        programs.put("ugml", "(68) -> gyxo, ebii, jptl");
        programs.put("gyxo", "(61)");
        programs.put("cntj", "(57)");
        Program p = new Program(programs, "tknk");
        assertEquals(60, p.getCorrectWeightOfUnbalancedProgram());
    }
    @Test
    public void testGetUnbalancedValueAlternative() {
        Map<String, String> programs = new HashMap<>();
        programs.put("pbga", "(66)");
        programs.put("xhth", "(57)");
        programs.put("ebii", "(61)");
        programs.put("havc", "(66)");
        programs.put("ktlj", "(57)");
        programs.put("fwft", "(72) -> ktlj, cntj, xhth");
        programs.put("qoyq", "(66)");
        programs.put("padx", "(45) -> pbga, havc, qoyq");
        programs.put("tknk", "(41) -> ugml, padx, fwft");
        programs.put("jptl", "(61)");
        programs.put("ugml", "(50) -> gyxo, ebii, jptl");
        programs.put("gyxo", "(61)");
        programs.put("cntj", "(57)");
        Program p = new Program(programs, "tknk");
        assertEquals(60, p.getCorrectWeightOfUnbalancedProgram());
    }
}
