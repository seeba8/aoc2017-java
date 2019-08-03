package day16;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Dance {
    public final int NUM_DANCERS;
    List<Integer> dancerList; // index: position; value = dancer;


    public Dance(int numDancers) {
        NUM_DANCERS = numDancers;
        int[] dancers = IntStream.range(97,97+NUM_DANCERS).toArray();
        dancerList = Arrays.stream(dancers).boxed().collect(Collectors.toList());

    }

    public void spin(int x) {
        Collections.rotate(dancerList, x);
    }

    public void exchange(int a, int b) {
        Collections.swap(dancerList, a, b);
    }

    public void partner(char a, char b) {
        int posA = dancerList.indexOf((int) a);
        Collections.swap(dancerList, dancerList.indexOf((int)a), dancerList.indexOf((int)b));
    }

    @Override
    public String toString() {
        return dancerList.stream()
                .map(i -> Character.toString((char)i.intValue()))
                .reduce(String::concat)
                .orElse("");
    }

    public void parseInstruction(String instr) {
        char type = instr.charAt(0);
        switch(type) {
            case 's':
                spin(Integer.parseInt(instr.substring(1)));
                break;
            case 'x':
                int slash = instr.indexOf("/");
                exchange(Integer.parseInt(instr.substring(1, slash)), Integer.parseInt(instr.substring(slash+1)));
                break;
            case 'p':
                partner(instr.charAt(1), instr.charAt(3));
                break;
            default:
                throw new IllegalArgumentException(String.format("Unknown instruction: %s (%s)", type, instr));
        }
    }

    public String parseInstructions(String line) {
        for(String instr : line.split(",")) {
            parseInstruction(instr);
        }
        return toString();
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        Path p = Paths.get(Dance.class.getClassLoader().getResource("day16.txt").toURI());
        String line = Files.readAllLines(p).get(0);
        Dance d = new Dance(16);
        String position = d.parseInstructions(line);
        System.out.println(position);

        List<String> positions = new ArrayList<>();
        positions.add(position);
        for (int i = 1; i < 1_000_000_000; i++) {
            if(i % 1_000_000 == 0) System.out.println("i = " + i);
            position = d.parseInstructions(line);
            System.out.printf("%03d: %s\n",i,position);
            if(positions.contains(position)) {
                System.out.printf("Seen before at iteration %s, now it's iteration %s\n", positions.indexOf(position), i);
                System.out.println(positions.get(999_999_999 % (i - positions.indexOf(position))));
                break;
            } else {
                positions.add(position);
            }
        }
    }



}
