package day13;

public class Packet {
    private int position = -1;

    public void move() {
        position += 1;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int i) {
        position = i;
    }
}
