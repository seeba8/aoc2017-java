package day4;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class StartDay4 {
    public static void main(String[] args) throws IOException, URISyntaxException {
        long part1, part2;
        PassphraseChecker check = new PassphraseChecker();
        Path p = Paths.get(StartDay4.class.getClassLoader().getResource("day4.txt").toURI());
        try (Stream<String> stream = Files.lines(p)) {
            part1 = stream.filter(check::isValid).count();
        }
        try (Stream<String> stream = Files.lines(p)) {
            part2 = stream.filter(check::checkNoAnagram).count();
        }
        System.out.println("Part 1 = " + part1);
        System.out.println("Part 2 = " + part2);
    }

}
