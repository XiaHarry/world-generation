package byow.lab12;
import org.junit.Test;
import static org.junit.Assert.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    public static TERenderer ter = new TERenderer();
//        ter.initialize(longestLength, longestLength);
    //TETile[][] hexagon = new TETile[longestLength][longestLength];

    public static void addHexagon(int s, int startX, int startY, TETile[][] hexagon) {
        int longestLength = s + (s - 1) * 2;
        int tracker = 0;

//        TETile[][] hexagon = new TETile[longestLength][longestLength];

        //upper
        for (int i = 0; i < s; i++) {
            //print out a
            for (int g = 0; g < s + tracker * 2; g++) {
                int space = (longestLength - s) / 2 - tracker;
                hexagon[g + space + startX][i + startY] = Tileset.TREE;
            }
            tracker += 1;
        }

        //lower
        for (int i = 0; i < s; i++) {
            tracker -= 1;
            //print out a
            for (int g = 0; g < s + tracker * 2; g++) {
                int space = (longestLength - s) / 2 - tracker;
                hexagon[g + space + startX][i + s + startY] = Tileset.WATER;
            }
        }
    }

    public static void drawTesselation(int s) {
        int longestLength = (s + (s - 1) * 2) * 3 + s * 2;
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        int s = 3;
        int longestLength = s + (s - 1) * 2;
        ter.initialize(3 * longestLength + 2 * s, s * 10);
        TETile[][] hexagon = new TETile[3 * longestLength + 2 * s][s * 10];
        for (int x = 0; x < 3 * longestLength + 2 * s; x++) {
            for (int y = 0; y < s * 10; y++) {
                hexagon[x][y] = Tileset.NOTHING;
            }
        }
        HexWorld.addHexagon(s, 10, 10, hexagon);
        ter.renderFrame(hexagon);
    }
}
