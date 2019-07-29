package day14;

import day10.KnotHash;

public class Defrag {

    public static void main(String[] args) {
        int popcount = 0;
        String input = "hfdlxzhv";
        for (int i = 0; i < 128; i++) {
            popcount += getPopcount(input + "-" + i);

        }
        System.out.println("popcount = " + popcount);

        int numRegions = getNumberOfRegions(input);
        System.out.println("numRegions = " + numRegions);
        //System.out.println(getBitString(input+"-0"));

    }

    private static int getNumberOfRegions(String input) {
        boolean[][] bitmap = getBitmap(input);
        int numRegions = 0;
        for(int y = 0; y < 128; y++) {
            for(int x = 0; x < 128; x++) {
                if(bitmap[y][x]) {
                    numRegions++;
                    bitmap[y][x] = false;
                    removeNeighbours(bitmap, x, y);
                }
            }
        }
        return numRegions;
    }

    private static void removeNeighbours(boolean[][] bitmap, int x, int y) {
        if(-1 < (y-1) && bitmap[y-1][x]) {
            bitmap[y-1][x] = false;
            removeNeighbours(bitmap, x, y-1);
        }
        if((y+1) < 128 && bitmap[y+1][x]) {
            bitmap[y+1][x] = false;
            removeNeighbours(bitmap, x, y+1);
        }
        if(-1 < (x-1) && bitmap[y][x-1]) {
            bitmap[y][x-1] = false;
            removeNeighbours(bitmap, x-1, y);
        }
        if((x+1) < 128 && bitmap[y][x+1]) {
            bitmap[y][x+1] = false;
            removeNeighbours(bitmap, x+1, y);
        }
    }

    private static boolean[][] getBitmap(String input) {
        boolean[][] bitmap = new boolean[128][128];
        for (int i = 0; i < 128; i++) {
            bitmap[i] = new boolean[128];
            int offset = 0;
            for(int val : KnotHash.fromAsciiInput(input + "-" + i).getDenseHash()) {
//                System.out.println(Arrays.toString(KnotHash.fromAsciiInput(input + "-" + i).getDenseHash()));
                String bitString = String.format("%08d", Integer.parseInt(Integer.toBinaryString(val)));
//                System.out.println(val + " = " + bitString);
                for (int j = 0; j < 8; j++) {
                    bitmap[i][8*offset+j] = bitString.charAt(j) == '1';
                }
                offset++;
            }
        }
        return bitmap;
    }


    static int getPopcount(String input) {
        int popcount = 0;

        String hash = KnotHash.fromAsciiInput(input).fullHash();
        //System.out.println("hash = " + hash);
        //System.out.println(Integer.toBinaryString(Integer.parseInt(hash.substring(0,7), 16)));
        for(char c : hash.toCharArray()) {
            popcount += Integer.bitCount(Integer.parseInt(String.valueOf(c), 16));
        }
        return popcount;
    }
}
