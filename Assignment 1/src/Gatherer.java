import java.util.Random;

public class Gatherer extends Actor {
    private int directionNo;

    private static final int ONE_TILE = 64;
    private static final int randomBound = 4;
    private static final String[] direction = {"left", "right", "up", "down"};

    /**
     * Creates a new instance of Gatherer and randomize an initial direction
     */
    public Gatherer(String filename, int x, int y) {
        super(filename, x, y);
        directionNo = generateDirection();
    }

    /**
     * Set a new direction to each gatherer whenever gets called
     */
    public void setDirection() {
        directionNo = generateDirection();
    }

    /**
     * Randomly return a number between 0 and 3
     */
    private int generateDirection() {
        Random rand = new Random();
        return rand.nextInt(randomBound);
    }

    /**
     * Check which direction the gatherer is going, and update it's
     * coordinate accordingly
     */
    public void move() {
        if (direction[directionNo].equals("left")) {
            setCoordinate(getX() - ONE_TILE, getY());
        }

        if (direction[directionNo].equals("right")) {
            setCoordinate(getX() + ONE_TILE, getY());
        }

        if (direction[directionNo].equals("up")) {
            setCoordinate(getX(), getY() - ONE_TILE);
        }

        if (direction[directionNo].equals("down")) {
            setCoordinate(getX(), getY() + ONE_TILE);
        }
    }

}
