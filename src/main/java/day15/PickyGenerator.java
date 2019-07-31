package day15;

public class PickyGenerator extends Generator {
    int criterion = 1;
    public PickyGenerator(long startValue, int factor, int criterion) {
        super(startValue, factor);
        this.criterion = criterion;
    }

    @Override
    public Long next() {
        long value;
        do {
            value = super.next();
        } while(value % criterion != 0);
        return value;
    }
}
