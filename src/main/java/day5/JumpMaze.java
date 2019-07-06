package day5;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JumpMaze {
    private int[] instructions;
    private int position = 0;
    private boolean isPartTwo = false;

    public static void main(String[] args) throws URISyntaxException, IOException {
        Path p = Paths.get(JumpMaze.class.getClassLoader().getResource("day5.txt").toURI());
        int[] instructions;
        try (Stream<String> stream = Files.lines(p)) {
            instructions = stream.mapToInt(Integer::parseInt).toArray();
            System.out.println("Number of jumps: " + new JumpMaze(instructions.clone()).execute());
        }
        System.out.println("Number of jumps: " + new JumpMaze(instructions.clone(), true).execute());

    }

    public void setPosition(int position) {
        this.position = position;
    }

    public JumpMaze(int[] jumps) {
        this.instructions = jumps;
    }
    public JumpMaze(int[] jumps, boolean partTwo) {
        this.instructions = jumps;
        isPartTwo = partTwo;
    }

    public int[] getInstructions() {
        return instructions;
    }

    public int getPosition() {
        return position;
    }

    public int execute() {
        position = 0;
        int numberOfJumps = 0;
        while (isInBounds()) {
            numberOfJumps++;
            this.singleJump();
        }
        return numberOfJumps;
    }

    public boolean isInBounds() {
        return position > -1 && position < instructions.length;
    }

    public void singleJump() {
        int oldPosition = position;
        position += instructions[position];
        updateInstruction(oldPosition);
    }

    private void updateInstruction(int position) {
        if(!isPartTwo) {
            instructions[position] += 1;
        } else {
            instructions[position] += instructions[position] >= 3 ? -1 : 1;
        }
    }


}
