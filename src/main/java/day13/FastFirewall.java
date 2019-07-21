package day13;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FastFirewall {
    int[] scannerRange;

    public static int getScannerPosition(int picosecond, int range) {
        if (range == 0) return -1;
        return Math.abs(((picosecond + range - 2) % ((range - 1) * 2)) - (range - 2));
    }

    public boolean isCaught(int delay) {
        for (int i = 0; i < scannerRange.length; i++) {
            if (getScannerPosition(i + delay, scannerRange[i]) == 0) return true;
        }
        return false;
    }

    public int getSeverity(int delay) {
        int severity = 0;
        for (int i = 0; i < scannerRange.length; i++) {
            if (getScannerPosition(i + delay, scannerRange[i]) == 0) severity += i * scannerRange[i];
        }
        return severity;
    }

    public static FastFirewall fromStringArray(String[] lines) {
        String lastLine = lines[lines.length - 1];
        int firewallSize = Integer.parseInt(lastLine.substring(0, lastLine.indexOf(":"))) + 1;
        int[] scanners = new int[firewallSize];
        Arrays.fill(scanners, 0);
        for (String line : lines) {
            int[] vals = Arrays.stream(line.split(": ")).mapToInt(Integer::parseInt).toArray();
            scanners[vals[0]] = vals[1];
        }
        FastFirewall f = new FastFirewall();
        f.scannerRange = scanners;
        return f;
    }

    public static void main(String[] args) {
        try {
            Path p = Paths.get(FastFirewall.class.getClassLoader().getResource("day13.txt").toURI());
            List<String> lines = Files.readAllLines(p);
            FastFirewall f = FastFirewall.fromStringArray(lines.toArray(new String[0]));

            System.out.println("Severity = " + f.getSeverity(0));

            
            int i = 0;
            while(f.isCaught(i)) {
                i++;
            }
            System.out.println("i = " + i);
        } catch (NullPointerException npe) {
            System.out.println("Path not found!");
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}

