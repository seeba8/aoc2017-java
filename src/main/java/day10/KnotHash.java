package day10;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KnotHash {
    private int[] numbers;
    private int[] lengths;
    private int position = 0;
    private int skip = 0;
    private final int SIZE;

    public KnotHash(int[] lengths) {
        this(256, lengths);
    }

    KnotHash(int size, int[] lengths) {
        SIZE = size;
        numbers = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            numbers[i] = i;
        }
        this.lengths = lengths;
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        Path p = Paths.get(KnotHash.class.getClassLoader().getResource("day10.txt").toURI());
        String input = Files.readAllLines(p).get(0);
        KnotHash kh = KnotHash.fromNumberInput(input);
        System.out.println("kh.hash() = " + kh.hash());
        kh = KnotHash.fromAsciiInput(input);
        System.out.println("kh.fullHash() = " + kh.fullHash());
    }

    int getPosition() {
        return position;
    }

    int getSkip() {
        return skip;
    }

    public int hash() {
        for (int length : lengths) {
            if (length > SIZE) throw new IllegalStateException(
                    String.format("Length (%s) may not be bigger than size (%s)", length, SIZE));
            hashStep(length);
        }
        return numbers[0] * numbers[1];
    }

    public String fullHash() {
        int[] dense = getDenseHash();
        return Arrays.stream(dense)
                .mapToObj(i -> String.format("%02x", i))
                .reduce(String::concat)
                .orElseThrow(IllegalStateException::new);
    }

    public static KnotHash fromNumberInput(String input) {
        return new KnotHash(Arrays.stream(input.split(",")).mapToInt(Integer::parseInt).toArray());
    }

    public static KnotHash fromAsciiInput(String input) {
        List<Integer> vals = new ArrayList<>();
        for (byte c : input.getBytes(StandardCharsets.US_ASCII)) {
            vals.add((int) c);
        }
        vals.addAll(Arrays.asList(17, 31, 73, 47, 23));
        return new KnotHash(vals.stream().mapToInt(Integer::intValue).toArray());
    }

    public int[] getDenseHash() {
        for (int i = 0; i < 64; i++) {
            hash();
        }
        int[] dense = new int[16];
        for (int i = 0; i < 16; i++) {
            dense[i] = numbers[16 * i];
            for (int j = 1; j < 16; j++) {
                dense[i] ^= numbers[16 * i + j];
            }
        }
        return dense;
    }

    void hashStep(int length) {
        int[] buf = numbers.clone();
        for (int i = 0; i < length; i++) {
            numbers[(position + i) % SIZE] = buf[(position + length - i - 1) % SIZE];
        }
        position = (position + length + skip) % SIZE;
        skip = (skip + 1) % SIZE;
    }

    int[] getSparseHash() {
        return numbers;
    }
}
