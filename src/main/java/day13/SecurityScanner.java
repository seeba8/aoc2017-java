package day13;

public class SecurityScanner {

    private int depth;
    private int range;
    private int position;
    private boolean movingDown = true;

    public int getPosition() {
        return position;
    }

    public int getDepth() {
        return depth;
    }

    public int getRange() {
        return range;
    }



    public void move() {
        position += movingDown ? 1 : -1;
        if(position == (range-1) || position == 0) {
            movingDown = !movingDown;
        }
    }
    public SecurityScanner(int depth, int range) {
        this.depth = depth;
        this.range = range;
        reset();
    }
    @Override
    public String toString() {
        return String.format("%s: %s/%s", depth, position, range-1);
    }

    public void setPosition(int i) {
        position = i;
    }

    public void reset() {
        this.movingDown = true;
        this.position = 0;
    }
}
