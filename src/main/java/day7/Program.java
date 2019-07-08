package day7;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Program {
    private List<Program> children;
    private int weight;
    private String name;

    public static void main(String[] args) throws URISyntaxException, IOException {
        Path p = Paths.get(Program.class.getClassLoader().getResource("day7.txt").toURI());
        Map<String, String> programs = Program.intoMap(Files.readAllLines(p).toArray(new String[0]));
        String rootName = findRootProgram(programs);
        System.out.println("rootName = " + rootName);
        System.out.println("correct Weight = " + new Program(programs, rootName).getCorrectWeightOfUnbalancedProgram());
    }

    public static Map<String, String> intoMap(String[] lines) {
        Map<String, String> programs = new HashMap<>();
        for (String line : lines) {
            programs.put(line.substring(0, line.indexOf(" ")), line.substring(line.indexOf(" ") + 1));
        }
        return programs;
    }

    public static String findRootProgram(Map<String, String> programs) {
        for (String k : programs.keySet()) {
            boolean contains = false;
            for (String v : programs.values()) {
                if (v.contains(k)) { // TODO contains may break if substring
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                return k;
            }
        }
        throw new IllegalStateException("No root program found");
    }

    public static String[] getChildrenFromValue(String val) {
        if (val.contains("->")) {
            return val.substring(val.indexOf("-> ") + 3).split(", ");
        }
        return new String[0];
    }

    public Program(Map<String, String> programs, String name) {
        String val = programs.get(name);
        this.name = name;
        this.weight = Integer.parseInt(val.substring(1, val.indexOf(")")));
        this.children = new ArrayList<>();
        for (String child : getChildrenFromValue(val)) {
            this.children.add(new Program(programs, child));
        }
    }

    public List<Program> getChildren() {
        return children;
    }

    public int getWeight() {
        return weight;
    }

    public int getWeight(boolean recursive) {
        if (!recursive) return getWeight();
        int collectiveWeight = weight;
        for (Program c : children) {
            collectiveWeight += c.getWeight(true);
        }
        return collectiveWeight;
    }

    public String getName() {
        return name;
    }

    public int getCorrectWeightOfUnbalancedProgram() {
        if (isBalanced()) return 0;
        if (children.stream().allMatch(Program::isBalanced)) {
            int firstWeight = 1;
            int otherWeight = 0;
            int incorrectId = 0;
            for (int i = 1; i < children.size(); i++) {
                if (children.get(i).getWeight(true) == children.get(0).getWeight(true)) {
                    firstWeight++;
                } else {
                    otherWeight++;
                }
                if (i >= 2 && firstWeight == 1) break;
                if (i >= 2 && otherWeight == 1) {
                    incorrectId = i;
                    break;
                }
            }
            int correctId = incorrectId == 0 ? 1 : 0;

            return children.get(incorrectId).getWeight() + (children.get(correctId).getWeight(true) -
                    children.get(incorrectId).getWeight(true));
        }
        for (Program c : children) {
            if (!c.isBalanced()) {
                return c.getCorrectWeightOfUnbalancedProgram();
            }
        }
        throw new IllegalStateException("Should not happen");
    }

    public boolean isBalanced() {
        if (children.size() < 2) return true;
        int previousWeight = children.get(0).getWeight(true);
        for (Program c : children) {
            if (c.getWeight(true) != previousWeight) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s (%s / %s, %s) -> %s",
                name,
                weight,
                getWeight(true),
                isBalanced() ? "balanced" : "not balanced",
                Arrays.toString(children.stream().map(Program::getName).toArray()));
    }
}
