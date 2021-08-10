/**
 * A type of Stationary Actor
 */

public class Fence extends StationActor {

    /** The type of this actor */
    public static final String TYPE = "Fence";

    /**
     * Creates a new Fence
     *
     * @param x X coordinate of Fence
     * @param y Y coordinate of Fence
     * @param world The world the Fence belongs to
     */
    public Fence(int x, int y, ShadowLife world) {
        super("res/images/fence.png", TYPE, x, y, world);
    }

    /**
     * Takes an active actor, if fence and actor are overlapping
     * change the active status of the actor to false and return
     * it's coordinate to the previous position
     *
     * @param actor An active actor
     */
    public void actorAction(ActiveActor actor) {
        if (overlapped(actor)) {
            actor.setActive(false);
            actor.previousCoordinate();
        }
    }
}