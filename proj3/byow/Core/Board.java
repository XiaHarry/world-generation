package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.*;

public class Board {
    public static final Integer dimensionX = 100;
    public static final Integer dimensionY = 50;
    //union of rooms
    public static WeightedQuickUnionUF coordinates = new WeightedQuickUnionUF(dimensionX * dimensionY);
    //room coordinates
    public static HashMap<ArrayList<Integer>, ArrayList<Integer>> rectangles = new HashMap<>();

    //check if coordinates are valid
    public static boolean checkValid(Integer startX, Integer startY, Integer w, Integer h) {
        //calculate all the points for this space and check union with rectan...
        for (int i = startX; i < startX + w; i++) {
            for (int j = startY; j < startY + h; j++) {
                if (i >= dimensionX - 2) {
                    return false;
                }
                if (j >= dimensionY - 2) {
                    return false;
                }
                for (Map.Entry<ArrayList<Integer>, ArrayList<Integer>> entry: rectangles.entrySet()) {
                    if (coordinates.find(dimensionX * j + i) == coordinates.find(entry.getKey().get(1) * dimensionX + entry.getKey().get(0))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //connects every grid in the room
    public static void quickUnionRoom(int startX, int startY, int length, int height) {
        for (int x = startX; x < startX + length; x++) {
            for (int y = startY; y < startY + height; y++) {
                coordinates.union(startY * dimensionX + startX, y * dimensionX + x);
            }
        }
    }

    public static void generateHallway() {

    }

    public static Integer positions(TreeMap<String, ArrayList<Integer>> rectangles, int x1, int x2, int y1, int y2) {
        ArrayList<Integer> start = new ArrayList<>(2);
        start.add(x1);
        start.add(y1);
        ArrayList<Integer> start2 = new ArrayList<>(2);
        start2.add(x2);
        start2.add(y2);

        ArrayList<Integer> lengthHeight = rectangles.get(start);
        ArrayList<Integer> lengthHeight2 = rectangles.get(start);

        //Bottom left case
        if (start.get(0) < start2.get(0) && start.get(1) < start2.get(1)) {

            //connect the top of room 1 to left of room 2
            // 1. Gets Top-mid of room 1
            int HallwayStartX = start.get(0) + lengthHeight.get(0)/2;
            int HallwayStartY =  start.get(1) + lengthHeight.get(1) - 1;

            //2. Gets Left-mid of room 2
            int HallwayEndX = start2.get(0);
            int HallwayEndY =  start2.get(1) + lengthHeight2.get(1)/2;
            return -2;
        }
        //Bottom right case
        if (start2.get(0) < start.get(0) && start.get(1) < start2.get(1)) {
            //connect the top of room 1 to right of room 2
            // 1. Gets Top-mid of room 1
            int HallwayStartX = start.get(0) + lengthHeight.get(0)/2;
            int HallwayStartY =  start.get(1) + lengthHeight.get(1) - 1;

            //2. Gets Right-mid of room 2
            int HallwayEndX = start2.get(0) + lengthHeight2.get(0) - 1;
            int HallwayEndY =  start2.get(1) + lengthHeight2.get(1)/2;
            return -1;
        }
        //Top left case
        if (start.get(0) < start2.get(0) && start.get(1) > start2.get(1)) {
            //connect the bottom of room 1 to right of room 2
            // 1. Gets Bottom-mid of room 1
            int HallwayStartX = start.get(0) + lengthHeight.get(0)/2;
            int HallwayStartY =  start.get(1);

            //2. Gets Right-mid of room 2
            int HallwayEndX = start2.get(0) + lengthHeight2.get(0) - 1;
            int HallwayEndY =  start2.get(1) + lengthHeight2.get(1)/2;
            return -1;
        }
        //Top right case
        if (start2.get(0) < start.get(0) && start.get(1) > start2.get(1)) {
            //connect the left-mid of room 1 to top-mid of room 2
            // 1. Gets left-mid of room 1
            int HallwayStartX = start.get(0);
            int HallwayStartY =  start.get(1) + lengthHeight.get(1)/2;

            //2. Gets Top-mid of room 2
            int HallwayEndX = start2.get(0) + lengthHeight2.get(0) - 1;
            int HallwayEndY =  start.get(1) + lengthHeight.get(1) - 1;

            return 1;
        }
        // Vertically aligned
        if (start2.get(0)==start.get(0)) {
            return 2;
        }
        //Parallel
        if (start2.get(1)==start.get(1)) {
            return 3;
        }
        System.out.println(lengthHeight.get(0));
        return null;
    }

    //draws one room
    public static TETile[][] drawRoom(TETile[][] tile, Integer startX, Integer startY, Integer height, Integer length) {
        //draw horizontal
        for (int i = 0; i <= length + 1; i++) {
            tile[i + startX][startY] = Tileset.WALL;
            tile[i + startX][startY + height + 1] = Tileset.WALL;
        }

        //draw vertical
        for (int w = 0; w <= height; w++) {
            tile[startX][w + startY] = Tileset.WALL;
            tile[startX + length + 1][w + startY] = Tileset.WALL;
        }
        return tile;
    }

    public static TETile[][] fillNothing(TETile[][] tile) {
        for (int x = 0; x < dimensionX; x++) {
            for (int y = 0; y < dimensionY; y++) {
                tile[x][y] = Tileset.NOTHING;
            }
        }
        return tile;
    }

    private static Integer substituteZero(Integer number) {
        if (number == 0) {
            return 5;
        }
        return number;
    }

    //generate room based on three digits
    public static TETile[][] generateRoom(TETile[][] tile, Integer seed) {

        tile = fillNothing(tile);

        coordinates = new WeightedQuickUnionUF(dimensionX * dimensionY);

        rectangles = new HashMap<>();

        Random random = new Random(seed);

        Integer number = random.nextInt(9);
        if (number < 5) {
            number += 5;
        }

        //repeatedly generate rooms
        for (int f = 1; f < number; f++) {
            Integer startX =  substituteZero(random.nextInt(100));
            Integer startY = substituteZero(random.nextInt(50));
            Integer length = substituteZero(random.nextInt(9));
            Integer height = substituteZero(random.nextInt(9));

            while (!checkValid(startX, startY, length, height)) {
                startX =  substituteZero(random.nextInt(99));
//            System.out.println("startX: " + startX);
                startY = substituteZero(random.nextInt(49));
//            System.out.println("startY: " + startY);
                length = substituteZero(random.nextInt(9));
//            System.out.println(length);
                height = substituteZero(random.nextInt(9));
            }

            tile = drawRoom(tile, startX, startY, height, length);

            //add to rectangles
            ArrayList<Integer> start = new ArrayList<>();
            start.add(startX);
            start.add(startY);
            ArrayList<Integer> dimension = new ArrayList<>();
            dimension.add(length);
            dimension.add(height);
            rectangles.put(start, dimension);

            //add to quickunion
            quickUnionRoom(startX, startY, length, height);
        }

        return tile;
    }
}
