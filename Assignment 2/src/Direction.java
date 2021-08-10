/**
 * A class that manages direction
 */

public class Direction {

    /** An integer that represent left */
    public static final int LEFT = 0;
    /** An integer that represent up */
    public static final int UP = 1;
    /** An integer that represent right */
    public static final int RIGHT = 2;
    /** An integer that represent down */
    public static final int DOWN = 3;

    /**
     * Returns a 90 degree counter clock wise and 270 degree
     * clock wise direction based on the given direction
     *
     * @param direction An integer between 0 and 3
     */
    public static int rotateCounter(int direction) {
        if (direction == UP || direction == RIGHT || direction == DOWN) {
            return direction - 1;
        }
        return DOWN;
    }

    /**
     * Returns a 90 degree close wise direction based on the
     * given direction
     *
     * @param direction An integer between 0 and 3
     */
    public static int rotateClockWise(int direction) {
        if (direction == RIGHT || direction == UP || direction == LEFT) {
            return direction + 1;
        }
        else {
            return LEFT;
        }
    }

    /**
     * Returns a 180 degree clockwise direction based on the
     * given direction
     *
     * @param direction An integer between 0 and 3
     */
    public static int rotateOneEighty(int direction) {
        if (direction == LEFT || direction == UP) {
            return direction + 2;
        }
        else {
            return direction - 2;
        }
    }
}
