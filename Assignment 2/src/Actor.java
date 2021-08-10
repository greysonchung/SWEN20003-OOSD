import bagel.Font;
import bagel.Image;
//This file contains content from the sample solution for Project 1

/**
 * An actor that lives in ShadowLife
 */
public abstract class Actor {
    private int x;
    private int y;
    private int lastX;
    private int lastY;

    /** Represents the type of the actor */
    public final String type;
    protected final Image image;
    private final ShadowLife world;
    protected final Font font = new Font("res/VeraMono.ttf", 24);

    /**
     * Creates a new actor
     *
     * @param filename The image of the actor
     * @param type The type of the actor
     * @param x X coordinate of the actor's position
     * @param y Y coordinate of the actor's position
     * @param world The world that the actor belongs to
     */
    public Actor(String filename, String type, int x, int y, ShadowLife world) {
        image = new Image(filename);
        this.type = type;
        this.x = x;
        this.y = y;
        this.lastX = x;
        this.lastY = y;
        this.world = world;
    }

    /**
     * @return X coordinate of the actor
     */
    public int getX() {
        return x;
    }

    /**
     * @return Y coordinate of the actor
     */
    public int getY() {
        return y;
    }

    /**
     * @return The reference of the world that the actor belongs to
     */
    public ShadowLife getWorld() {
        return world;
    }

    /**
     * Move the actor back to position of last tick.
     */
    public void previousCoordinate() {
        x = lastX;
        y = lastY;
    }

    /**
     * This method records the actors most recent position and update the actor
     * to their new position.
     * @param deltaX the number of pixels an actor needs to move horizontally
     * @param deltaY the number of pixels an actor needs to move vertically
     */
    public void setCoordinate(int deltaX, int deltaY) {
        lastX = x;
        lastY = y;
        x += deltaX;
        y += deltaY;
    }

    /**
     * A method that is overridden by its child class.
     */
    public void update() {}

    /**
     * The method that ShadowLife calls every tick, use to invoke the update
     * method of the child class
     */
    public final void tick() {
        update();
    }

    /**
     * Draw the actor with its top-left at (x, y).
     */
    public void render() {
        image.drawFromTopLeft(x, y);
    }

    /**
     * The method that overridden by its child class.
     *
     * @param actor an ActiveActor
     */
    public abstract void actorAction(ActiveActor actor);

    /**
     * Compare the position of two actor
     *
     * @param actor an actor
     * @return A boolean that indicates if two actors are overlapped
     */
    public boolean overlapped(Actor actor) {
        return this.x == actor.x && this.y == actor.y;
    }
}