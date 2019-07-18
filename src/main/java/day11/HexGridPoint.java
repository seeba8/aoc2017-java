package day11;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class HexGridPoint {
    /**
     * Cubic representation taken from https://www.redblobgames.com/grids/hexagons/
     *
     *         +Y        -Z
     *          \      /
     *           \---/
     *  -X <--- /     \   ---> +X
     *          \    /
     *          /---\
     *        /      \
     *      +Z       -Y
     */
    private int x = 0, y = 0, z = 0;

    public int getDistance() {
        return (Math.abs(x) + Math.abs(y) + Math.abs(z)) / 2;
    }

    public HexGridPoint() {
    }

    public void go(Direction[] directions) {
        for(Direction d : directions) {
            go(d);
        }
    }

    public void go(Direction d) {
        switch (d) {
            case N:
                y += 1;
                z -= 1;
                break;
            case NE:
                x += 1;
                z -= 1;
                break;
            case SE:
                x += 1;
                y -= 1;
                break;
            case S:
                y -= 1;
                z += 1;
                break;
            case SW:
                x -= 1;
                z += 1;
                break;
            case NW:
                x -= 1;
                y += 1;
        }
    }
    
    public int getMaxDistance(Direction[] directions) {
        int maxDistance = 0;
        for(Direction d: directions) {
            go(d);
            maxDistance = Math.max(getDistance(), maxDistance);
        }
        return maxDistance;
    }

    public static Direction[] getDirectionsFromString(String directions, String separator) {
        return Arrays.stream(directions.split(separator))
                .map(HexGridPoint::parseDirection)
                .toArray(Direction[]::new);
    }

    public static Direction parseDirection(String s) {
        switch (s.toLowerCase()) {
            case "n": return Direction.N;
            case "ne": return Direction.NE;
            case "se": return Direction.SE;
            case "s": return Direction.S;
            case "sw": return Direction.SW;
            case "nw": return Direction.NW;
            default: throw new IllegalStateException("unknown direction!");
        }
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        Path p = Paths.get(HexGridPoint.class.getClassLoader().getResource("day11.txt").toURI());
        String input = Files.readAllLines(p).get(0);
        HexGridPoint h = new HexGridPoint();
        Direction[] directions = getDirectionsFromString(input, ",");
        h.go(directions);
        System.out.println("h.getDistance() = " + h.getDistance());
        
        h = new HexGridPoint();
        System.out.println("h.getMaxDistance(directions) = " + h.getMaxDistance(directions));
        
    }

}

enum Direction {
    N, NE, SE, S, SW, NW
}
