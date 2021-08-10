/**
 * A type of Stationary Actor
 */

public class GoldenTree extends StationActor {

    /** The type of this actor */
    public static final String TYPE = "GoldenTree";

    /**
     * Creates a new GoldenTree
     *
     * @param x X coordinate of GoldenTree
     * @param y Y coordinate of GoldenTree
     * @param world The world the GoldenTree belongs to
     */
    public GoldenTree(int x, int y, ShadowLife world) {
        super("res/images/gold-tree.png", TYPE, x, y, world);
    }

    /**
     * Takes an active actor and check if it's overlapping and
     * update direction and status of Active actor accordingly
     */
    @Override
    public void actorAction(ActiveActor actor) {
        if (overlapped(actor)) {
            actor.setCarrying(true);
            if (actor instanceof Gatherer) {
                actor.setDirection(Direction.rotateOneEighty(actor.getDirection()));
            }
        }
    }
}
