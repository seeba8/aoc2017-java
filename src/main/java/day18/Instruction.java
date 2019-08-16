package day18;

public class Instruction {
    InstructionType type;
    String parameter1;
    String parameter2;

    public Instruction(InstructionType type, String parameter1, String parameter2) {
        this.type = type;
        this.parameter1 = parameter1;
        this.parameter2 = parameter2;
    }

    public Instruction(InstructionType type, String parameter) {
        this(type, parameter, "");
    }


}
