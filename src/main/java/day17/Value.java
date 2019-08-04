package day17;

public class Value {
    int v;
    Value successor;

    public Value(int val) {
        v = val;
        successor = this;
    }

    public Value nthSuccessor(int n) {
        Value obj = this;
        for (int i = 0; i < n; i++) {
            obj = obj.successor;
        }
        return obj;
    }
}
