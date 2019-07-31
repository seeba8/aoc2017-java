package day15;

import java.util.Iterator;

public class Generator implements Iterator<Long> {
    private final int DIVISOR = 2_147_483_647;
    private final int FACTOR;
    private long lastValue;
    public Generator(long startValue, int factor) {
        FACTOR = factor;
        lastValue = startValue;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Long next() {
        lastValue *= FACTOR;
        lastValue =  lastValue % DIVISOR;
        return lastValue;
    }
}
