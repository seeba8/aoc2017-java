package day7;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Program {
    private List<Program> children;
    private int weight;
    private String name;

    public static void main(String[] args) throws URISyntaxException, IOException {
        Path p = Paths.get(Program.class.getClassLoader().getResource("day7.txt").toURI());
        Map<String, String> programs = Program.intoMap(Files.readAllLines(p).toArray(new String[0]));
        String rootName = findRootProgram(programs);
        System.out.println("rootName = " + rootName);

    }

    public static Map<String, String> intoMap(String[] lines) {
        Map<String, String> programs = new HashMap<>();
        for(String line : lines) {
            programs.put(line.substring(0,line.indexOf(" ")), line.substring(line.indexOf(" ") + 1));
        }
        return programs;
    }

    public static String findRootProgram(Map<String, String> programs) {
        for (String k : programs.keySet()) {
            boolean contains = false;
            for (String v: programs.values()) {
                if(v.contains(k)) { // TODO contains may break if substring
                    contains = true;
                    break;
                }
            }
            if(!contains) {
                return k;
            }
        }
        throw new IllegalStateException("No root program found");
    }

    public static String[] getChildrenFromValue(String val) {
        if(val.contains("->")) {
            return val.substring(val.indexOf("-> ")+3).split(", ");
        }
        return new String[0];
    }

    public Program(Map<String, String> programs, String name) {
        String val = programs.get(name);
        this.name = name;
        this.weight = Integer.parseInt(val.substring(1,val.indexOf(")")));
        this.children = new ArrayList<>();
        for(String child : getChildrenFromValue(val)) {
            this.children.add(new Program(programs, child));
        }
    }

    public List<Program> getChildren() {
        return children;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    /*@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if(!Program.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Program other = (Program) obj;
        return other.name.equals(this.name) &&
                other.weight == this.weight &&
                other.children.equals(this.children);
    }

    @Override
    public int hashCode() {
        return (this.name.hashCode() + this.weight + this.children.hashCode()) % Integer.MAX_VALUE;
    }*/

}
