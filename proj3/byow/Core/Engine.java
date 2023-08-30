package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import byow.Core.Board;
import edu.princeton.cs.introcs.StdDraw;
import byow.InputDemo.KeyboardInputSource;
import byow.InputDemo.InputSource;

public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
    }

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     *
     * In other words, both of these calls:
     *   - interactWithInputString("n123sss:q")
     *   - interactWithInputString("lww")
     *
     * should yield the exact same world state as:
     *   - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */

    public TETile[][] interactWithInputString(String input) {
        // TODO: Fill out this method so that it run the engine using the input
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.

        //demo input source
//        InputSource inputSource;
//        inputSource = new KeyboardInputSource();

        //process input to get N seed and S
        String first = input.substring(0, 1);
        Integer seed = 0;
        String last = Character.toString(input.charAt(input.length() - 1));

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                seed = seed * 10 + Integer.parseInt(Character.toString(c));
            }
        }

        TETile[][] finalWorldFrame = new TETile[100][50];

        finalWorldFrame = Board.generateRoom(finalWorldFrame, seed);

        return finalWorldFrame;
    }


    public static void main(String[] args) {
        Engine big = new Engine();

        TERenderer ter = new TERenderer();
        ter.initialize(100, 50);
        TETile[][] dog = big.interactWithInputString("n5197880843569031643s");
        dog = big.interactWithInputString("n5197880843569031643s");
        //n2510895245331522769s
        ter.renderFrame(dog);
    }
}
