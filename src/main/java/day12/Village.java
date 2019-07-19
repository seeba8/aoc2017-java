package day12;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Village {
    private Programme[] programmes;
    public Village(int[][] input) {
        programmes = new Programme[input.length];
        for (int i = 0; i < input.length; i++) {
            programmes[i] = new Programme();
        }
        for(int i = 0; i < input.length; i++) {
            for(int child : input[i]) {
                programmes[i].addChild(programmes[child]);
            }
        }
    }

    Programme[] getProgrammes() {
        return programmes;
    }

    public Programme getProgramme(int i) {
        return programmes[i];
    }

    public int getGroupCount() {
        Set<Set<Programme>> groups = new HashSet<>();
        Set<Programme> remainingProgrammes = new HashSet<>(Arrays.asList(programmes));
        while(remainingProgrammes.size() > 0) {
            Set<Programme> newGroup =  remainingProgrammes.stream().findAny().get().getChildrenRecursive();
            remainingProgrammes.removeAll(newGroup);
            boolean isCompletelyDisjoint = true;
            for (Set<Programme> group : groups) {
                if(!Collections.disjoint(group, newGroup)) {
                    group.addAll(newGroup);
                    isCompletelyDisjoint = false;
                }
            }
            if(isCompletelyDisjoint) {
                groups.add(newGroup);
            }
        }
        return groups.size();
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        Path p = Paths.get(Village.class.getClassLoader().getResource("day12.txt").toURI());
        List<String> input = Files.readAllLines(p);
        int[][] children = new int[input.size()][];
        for (int i = 0; i < input.size(); i++) {
            children[i] = Arrays.stream(input.get(i).substring(input.get(i).indexOf(">")+2).split(", "))
                    .mapToInt(Integer::parseInt).toArray();
        }
        Village v = new Village(children);
        System.out.println("Children = " + v.getProgramme(0).countChildren());
        System.out.println("v.getGroupCount() = " + v.getGroupCount());
    }
}
