package day8;


import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class CPU {
    private Map<String, Integer> registers;
    private String[] instructions;

    public CPU(String[] instructions) {
        this.instructions = instructions;
        registers = new HashMap<>();
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        Path p = Paths.get(CPU.class.getClassLoader().getResource("day8.txt").toURI());
        String[] input = Files.readAllLines(p).toArray(new String[]{});
        CPU c = new CPU(input);
        c.executeInstructions();
        System.out.println("Largest register value: " + c.getHighestRegisterValue());
        System.out.println("c.getHighestEverValue() = " + c.getHighestEverValue());
    }

    private void resetRegisters() {
        registers = new HashMap<>();
    }

    public void executeInstructions() {
        for (String instr : instructions) {
            executeInstruction(instr);
        }
    }

    public void executeInstruction(String instr) {
        // Syntax: register command value "if" register comparison value
        //           0        1       2           4         5        6
        String[] blocks = instr.split("\\s");
        int factor = blocks[1].equals("inc") ? 1 : -1;
        if (parseCondition(blocks)) {
            registers.merge(blocks[0], factor * Integer.parseInt(blocks[2]), Integer::sum);
        }
    }

    public int getRegisterValue(String key) {
        return registers.getOrDefault(key, 0);
    }

    private boolean parseCondition(String[] blocks) {
        int registerValue = registers.getOrDefault(blocks[4], 0);
        int other = Integer.parseInt(blocks[6]);
        switch (blocks[5]) {
            case "==":
                return registerValue == other;
            case ">":
                return registerValue > other;
            case "<":
                return registerValue < other;
            case ">=":
                return registerValue >= other;
            case "<=":
                return registerValue <= other;
            case "!=":
                return registerValue != other;
            default:
                throw new IllegalStateException("Unknown operand: " + blocks[5]);
        }
    }

    public int getHighestRegisterValue() {
        return registers.values().stream()
                .max(Comparator.comparing(Integer::valueOf))
                .orElse(0);
    }

    public int getHighestEverValue() {
        resetRegisters();
        int maxValue = 0;
        for (String instr : instructions) {
            executeInstruction(instr);
            maxValue = Math.max(maxValue, getHighestRegisterValue());
        }
        return maxValue;
    }
}
