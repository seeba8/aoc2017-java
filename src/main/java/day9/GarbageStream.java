package day9;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GarbageStream {
    private Group root;

    public GarbageStream(String raw) {
        root = new Group();
        char[] chars = raw.toCharArray();
        root.buildStructure(chars, 0);
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        Path p = Paths.get(GarbageStream.class.getClassLoader().getResource("day9.txt").toURI());
        String input = Files.readAllLines(p).get(0);
        GarbageStream s = new GarbageStream(input);
        System.out.println("Score = " + s.getRoot().getScore());
        System.out.println("Garbage = " + s.getRoot().getGarbageSize());
    }

    public Group getRoot() {
        return root;
    }


}
