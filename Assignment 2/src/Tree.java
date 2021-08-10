/**
 * A type of Stationary Actor
 */

public class Tree extends StationActor {

    /** The type of this actor */
    public static final String TYPE = "Tree";
    private int fruitCount;

    /**
     * Creates a new Tree
     *
     * @param x X coordinate of Tree
     * @param y Y coordinate of Tree
     * @param world The world the Tree belongs to
     */
    public Tree(int x, int y, ShadowLife world) {
        super("res/images/tree.png", TYPE, x, y, world);
        fruitCount = 3;
    }

    /**
     * Overrides the render() method in Actor class in order to display
     * the numbers of fruitCount as well as draw image of the actor
     */
    public void render() {
        font.drawString(String.valueOf(fruitCount), getX(), getY());
        image.drawFromTopLeft(getX(), getY());
    }

    /**
     * Takes an active actor and check if it's overlapping and
     * update direction and status of Tree and Active actor accordingly
     */
    public void actorAction(ActiveActor actor) {
        if (overlapped(actor) && !actor.getCarrying()) {
            if (fruitCount >= 1) {
                fruitCount--;
                actor.setCarrying(true);
                if (actor instanceof Gatherer) {
                    actor.setDirection(Direction.rotateOneEighty(actor.getDirection()));
                }
            }
        }
    }
}