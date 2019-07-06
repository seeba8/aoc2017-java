package utils;

public class Point<T> {
    public T x;
    public T y;
    public Point(T x, T y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj) return true;
        if (this.getClass() != obj.getClass()) return false;
        Point p2 = (Point) obj;
        return p2.x == this.x && p2.y == this.y;
    }

    @Override
    public int hashCode() {
        return String.format("x %s y %s", this.x, this.y).hashCode();
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", this.x, this.y);
    }

}
