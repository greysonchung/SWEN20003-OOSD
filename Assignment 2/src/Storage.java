/**
 * A Storage Actor
 */

public abstract class Storage extends StationActor {

    private int fruitCount = 0;

    /**
     * Creates a new storage actor.
     *
     * @param filename The image of the actor
     * @param type The type of the actor
     * @param x X coordinate of the actor's position
     * @param y Y coordinate of the actor's position
     * @param world The world that the actor belongs to
     */
    public Storage(String filename, String type, int x, int y, ShadowLife world) {
        super(filename, type, x, y, world);
    }

    /**
     * Increase the number of fruitCount by one
     */
    public void increaseFruitNum() {
        fruitCount++;
    }

    /**
     * Decrease the number of fruitCount by one
     */
    public void decreaseFruitNum() {
        fruitCount--;
    }

    /**
     * Returns the number of fruitCount
     */
    public int getFruitCount() {
        return fruitCount;
    }

    /**
     * Overrides the render() method in Actor class in order to display
     * the numbers of fruitCount as well as draw image of the actor
     */
    @Override
    public void render() {
        font.drawString(String.valueOf(getFruitCount()), getX(), getY());
        image.drawFromTopLeft(getX(), getY());
    }
}
