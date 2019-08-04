package day17;

import java.util.*;
import java.util.stream.Collectors;

public class Spinlock {
    int currentIndex;
    private int numSteps;
    private Value val;
    //private LinkedList<Integer> values;
    int currentSize;
    Value startVal;

    public Spinlock(int numSteps, int lastValue) {
        this(numSteps, lastValue, false);
    }

    Spinlock(int numSteps, int lastValue, boolean noSpin) {
        currentIndex = 0;
        this.numSteps = numSteps;
        //values = new LinkedList<>();
        //values.add(0);
        val = new Value(0);
        startVal = val;
        currentSize = 1;
        if(!noSpin) spin(numSteps, lastValue);
    }

    private void spin(int numSteps, int lastValue) {
        for (int iteration = 0; iteration < lastValue; iteration++) {
            if(iteration % 100_000 == 0) System.out.println("iteration = " + iteration);
            setNewIndex();
            insertValue(iteration+1);
        }
    }

    void setNewIndex() {
        currentIndex = (currentIndex + numSteps) % currentSize;
        val = val.nthSuccessor(numSteps);
    }

    void insertValue(int v) {
        //System.arraycopy(values,currentIndex+1,values, currentIndex+2,(currentSize-currentIndex-1) );
        //values.add(currentIndex+1, v);
        Value newVal = new Value(v);
        newVal.successor = val.successor;
        val.successor = newVal;
        val = newVal;
        //values[currentIndex+1] = v;
        currentIndex += 1;
        currentSize += 1;
    }

    int[] getValues() {
        //return Arrays.copyOfRange(values, 0, currentSize);
        int[] res = new int[currentSize];
        int i = 0;
        Value nextVal = startVal;
        do{
            res[i] = nextVal.v;
            nextVal = nextVal.successor;
            i++;
        } while(nextVal != startVal);
        return res;
        //return values.stream().mapToInt(Integer::intValue).toArray();
    }
    
    int getValueAfter(int value) {
        /*List<Integer> listOfValues = Arrays.stream(values).boxed().collect(Collectors.toList());
        int pos = listOfValues.indexOf(value);
        if(value < 0) throw new ArrayIndexOutOfBoundsException(String.format("Value %s not in list", value));
        return listOfValues.get((pos+1)%listOfValues.size());*/
        //return values.get(values.indexOf(value)+1);
        return startVal.successor.v;
    }

    public static void main(String[] args) {
        Spinlock spinlock = new Spinlock(369, 2017);
        System.out.println("Value after 2017 = " + spinlock.getValues()[spinlock.currentIndex+1]);
        
        spinlock = new Spinlock(369, 50_000_000);
        System.out.println("spinlock.getValueAfter(0) = " + spinlock.getValueAfter(0));
    }
}
