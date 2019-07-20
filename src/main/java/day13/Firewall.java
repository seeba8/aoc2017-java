package day13;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Firewall {
    private Map<Integer, SecurityScanner> securityScanners = new HashMap<>();
    private Packet packet = new Packet();
    private List<Integer> infractions = new ArrayList<>();
    private int highestLayer = 0;

    public Firewall(int[] scanners) {
        for (int i = 0; i < scanners.length; i++) {
            if(scanners[i] > 0) {
                addSecurityScanner(new SecurityScanner(i, scanners[i]));
            }
        }
    }

    private Firewall() { }

    public void simulate() {
        for (int i = 0; i <= highestLayer; i++) {
            tick();
        }
    }

    public int getSeverity() {
        return infractions.stream().map(i -> i * securityScanners.get(i).getRange()).mapToInt(Integer::intValue).sum();
    }

    public boolean wasCaught() {
        return infractions.size() > 0;
    }

    public void tick() {
        packet.move();
        if(securityScanners.containsKey(packet.getPosition()) &&
        securityScanners.get(packet.getPosition()).getPosition() == 0) {
            infractions.add(packet.getPosition());
        }
        moveScanners();
    }

    private void moveScanners() {
        securityScanners.forEach((t, u) -> u.move());
    }

    public void simulateWithoutPacket(int rounds) {
        for (int i = 0; i < rounds; i++) {
            moveScanners();
        }
    }


    public void addSecurityScanner(SecurityScanner scanner) {
        highestLayer = Math.max(scanner.getDepth(), highestLayer);
        securityScanners.put(scanner.getDepth(), scanner);
    }

    public void reset() {
        packet = new Packet();
        securityScanners.forEach((integer, scanner) -> scanner.reset());
        infractions.clear();
    }

    public static Firewall fromInput(String[] lines) {
        Firewall f = new Firewall();
        for (String line : lines) {
            int[] args = Arrays.stream(line.split(": ")).mapToInt(Integer::parseInt).toArray();
            f.addSecurityScanner(new SecurityScanner(args[0], args[1]));
        }
        return f;
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        Path p;
        try {
            p = Paths.get(Firewall.class.getClassLoader().getResource("day13.txt").toURI());
        } catch (NullPointerException npe) {
            System.out.println("Path not found!");
            return;
        }

        List<String> lines = Files.readAllLines(p);
        Firewall f = Firewall.fromInput(lines.toArray(new String[0]));
        f.simulate();
        System.out.println("f.getSeverity() = " + f.getSeverity());
        int delay = 0;
        do {
            delay++;
            if(delay % 1000 == 0) System.out.println("delay = " + delay);
            f.reset();
            f.simulateWithoutPacket(delay);
            f.simulate();
        } while(f.wasCaught());

        System.out.println("delay = " + delay);
    }


}
