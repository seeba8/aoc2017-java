package day15;

public class Judge {
    private static final int FACTOR_A = 16_807;
    private static final int FACTOR_B = 48_271;
    private static final int CRITERION_A = 4;
    private static final int CRITERION_B = 8;
    Generator a;
    Generator b;

    public Judge (long startA, long startB, boolean picky) {
        if(!picky) {
            a = new Generator(startA, FACTOR_A);
            b = new Generator(startB, FACTOR_B);
        } else {
            a = new PickyGenerator(startA, FACTOR_A, CRITERION_A);
            b = new PickyGenerator(startB,FACTOR_B, CRITERION_B);
        }
    }

    public Judge(long startA, long startB) {
        a = new Generator(startA, FACTOR_A);
        b = new Generator(startB, FACTOR_B);
    }

    public int compareResults(int howMany) {
        int numPairs = 0;
        for(int i = 0; i < howMany; i++) {
            final long aValue = a.next();
            final long bValue = b.next();
            if(howMany < 10) System.out.printf("%32s\n%32s\n\n", Long.toBinaryString(aValue),Long.toBinaryString(bValue));
            if((aValue & 0xffff) == (bValue & 0xffff)) {
                numPairs++;
            }
        }
        return numPairs;
    }

    public static void main(String[] args) {
        Judge judge = new Judge(703, 516);
        System.out.println("judge.compareResults(40_000_000) = " + judge.compareResults(40_000_000));

        judge = new Judge(703, 516, true);
        System.out.println("judge.compareResults(5_000_000) = " + judge.compareResults(5_000_000));
    }

}
