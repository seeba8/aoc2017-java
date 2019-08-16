package day18;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuetCompiler {
    private Map<String, Long> registers = new HashMap<>(); // mean trick, it needs to be a Long, an Integer is overflown
    private List<Instruction> instructions = new ArrayList<>();
    private int currentInstruction = 0;
    private long lastFrequency = 0;

    public long execute() {
        initialiseRegisters();
        while(currentInstruction > -1 && currentInstruction < instructions.size()) {
            Instruction instr = instructions.get(currentInstruction);
            long x = registers.get(instr.parameter1);
            long y = instr.parameter2.equals("") ? 0 : registers.get(instr.parameter2);
            switch (instr.type) {
                case SND:
                    lastFrequency = x;
                    break;
                case SET:
                    registers.put(instr.parameter1, y);
                    break;
                case ADD:
                    registers.put(instr.parameter1, x + y);
                    break;
                case MUL:
                    registers.put(instr.parameter1, x * y);
                    break;
                case MOD:
                    registers.put(instr.parameter1, x % y);
                    break;
                case RCV:
                    if(registers.get(instr.parameter1) != 0) {
                        return lastFrequency;
                    }
                    break;
                case JGZ:
                    if (registers.get(instr.parameter1) > 0) {
                        currentInstruction += registers.get(instr.parameter2) - 1; // -1 as we add 1 every iteration
                    }
                    break;
                default:
                    throw new IllegalStateException("Should never happen!");
            }
            currentInstruction++;
        }
        return 0;
    }


    private void parseParameter(String parameter) {
        try {
            long val = Integer.parseInt(parameter);
            registers.put(parameter, val);
        } catch (NumberFormatException ex) {
            registers.put(parameter, 0L);
        }
    }

    private void initialiseRegisters() {
        for (Instruction i : instructions) {
            parseParameter(i.parameter1);
            if(!i.parameter2.equals("")) {
                parseParameter(i.parameter2);
            }
        }
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        Path p = Paths.get(DuetCompiler.class.getClassLoader().getResource("day18.txt").toURI());
        String[] line = Files.readAllLines(p).toArray(new String[0]);
        DuetCompiler compiler = DuetCompiler.fromInstructionsArray(line);
        System.out.println("compiler = " + compiler.execute());
    }

    public static DuetCompiler fromInstructionsArray(String[] instructions) {

        DuetCompiler compile = new DuetCompiler();
        for (String instruction : instructions) {
            String[] parts = instruction.split(" ");
            Instruction i = new Instruction(
                    InstructionType.valueOf(parts[0].toUpperCase()),
                    parts[1],
                    parts.length > 2 ? parts[2] : ""
            );
            compile.instructions.add(i);
        }
        return compile;
    }

}
