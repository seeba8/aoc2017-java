package day6;

import day5.JumpMaze;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class MemoryBanks {
    private int[] banks;
    private Set<Integer> stateHistory = new HashSet<>();

    public static void main(String[] args) throws IOException, URISyntaxException {
        Path p = Paths.get(JumpMaze.class.getClassLoader().getResource("day6.txt").toURI());
        int[] input = Arrays.stream(Files.readAllLines(p).get(0).split("\\t"))
                .mapToInt(Integer::parseInt)
                .toArray();
        // System.out.println("Arrays.toString(input) = " + Arrays.toString(input));
        MemoryBanks m = new MemoryBanks(input);
        System.out.println("redistribution cycles = " + m.distributeBanks());
        System.out.println("size of loop = " + m.getSizeOfLoop());
    }

    public MemoryBanks(int[] banks) {
        this.banks = banks;
    }

    public int[] getBanks() {
        return banks;
    }

    public int getBankWithMostBlocks() {
        int maxIndex = 0, maxValue = 0;
        for(int i = 0; i < banks.length; i++) {
            if(banks[i] > maxValue) {
                maxValue = banks[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public void distributeBank(int id) {
        int val = banks[id];
        banks[id] = 0;
        int offset = 0;
        while(val-- > 0) {
            offset++;
            banks[(id+offset) % banks.length]++;
        }
    }

    public int distributeBanks() {
        while (stateHistory.add(Arrays.hashCode(banks))) {
            distributeBank(getBankWithMostBlocks());
        }
        return stateHistory.size();
    }

    public int getSizeOfLoop() {
        if(stateHistory.size() == 0) distributeBanks();
        int startOfLoopHash = Arrays.hashCode(banks);
        int sizeOfLoop = 0;
        do {
            sizeOfLoop++;
            distributeBank(getBankWithMostBlocks());
        }while (Arrays.hashCode(banks) != startOfLoopHash);
        return sizeOfLoop;
    }
}
