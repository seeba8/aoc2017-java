package day3;
import utils.Point;

import java.util.HashMap;
import java.util.Map;

public class Spiral {

    public static long getRing(long value) {
        return (int)Math.ceil(Math.sqrt(value)) / 2;
    }

    public static long getOffsetFromAxis(long value) {
        long ring = getRing(value);
        long square = (ring*2+1)*(ring*2+1);
        return Math.min(Math.abs(value-(square-ring)),
                Math.min(Math.abs(value-(square-3*ring)),
                        Math.min(Math.abs(value-(square-5*ring)),
                                Math.abs(value-(square-7*ring)))));
    }

    public static long getNumberOfSteps(long value) {
        return getRing(value) + getOffsetFromAxis(value);
    }

    public static Point<Long> getPosition(long value) {
        Point<Long> pos = new Point<Long>(0L, 0L);
        long ring = getRing(value);
        long square = (ring*2+1)*(ring*2+1);
        int yFactor = 1, xFactor = 1;
        if (value > (square - 3*ring) || value < (square - 7*ring)) {
            yFactor = -1;
        }
        if (value < (square - ring) && value > (square -5*ring)) {
            xFactor = -1;
        }
        if(value > square - 2*ring) {
            pos.y = yFactor * ring;
            pos.x = xFactor * Math.abs(value - (square-ring));
        } else if (value > square - 4*ring) {
            pos.x = xFactor * ring;
            pos.y = yFactor *Math.abs(value - (square - 3*ring));
        } else if (value > square - 6*ring) {
            pos.y = yFactor * ring;
            pos.x = xFactor *Math.abs( value - (square - 5*ring));
        } else {
            pos.x = xFactor * ring;
            pos.y =yFactor *Math.abs( value - (square - 7*ring));
        }

        return pos;
    }

    public static long getFirstValueLargerThan(long value) {
        int x = 1;
        Map<Point<Long>,Long> spiral = new HashMap<Point<Long>,Long>();
        spiral.put(new Point<Long>(0L,0L), 1L);
        while(true) {
            x++;
            Point<Long> p = getPosition(x);
            spiral.put(p,getValueOfNeighbours(p, spiral));
            //System.out.println(spiral.get(p));
            if(spiral.get(p) > value) return spiral.get(p);
        }
    }

    private static Long getValueOfNeighbours(Point<Long> p, Map<Point<Long>,Long> spiral) {
        long s = 0;
        for(int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                s += spiral.getOrDefault(new Point<Long>(p.x + x, p.y + y), 0L);
            }
        }
        return s;
    }
}
