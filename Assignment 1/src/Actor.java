import bagel.Image;
import bagel.util.Point;

public abstract class Actor extends Image {
    private Point coordinate;

    /**
     * Creates a new instance of Actor
     */
    public Actor(String filename, int x, int y) {
        super(filename);
        coordinate = new Point(x, y);
    }

    public double getX() {
        return coordinate.x;
    }

    public double getY() {
        return coordinate.y;
    }

    /**
     * To update the position of a single actor
     */
    protected void setCoordinate(double newX, double newY) {
        coordinate = new Point(newX, newY);
    }
}
