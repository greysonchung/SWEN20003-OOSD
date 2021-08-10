/**
 * A type of Stationary Actor
 */

public class MitosisPool extends StationActor {

    /** The type of this actor */
    public static final String TYPE = "Pool";

    /**
     * Creates a new Mitosis Pool
     *
     * @param x X coordinate of Pool
     * @param y Y coordinate of Pool
     * @param world The world the Pool belongs to
     */
    public MitosisPool(int x, int y, ShadowLife world) {
        super("res/images/pool.png", TYPE, x, y, world);
    }

    /**
     * Takes an active actor and check if it's overlapping
     * and clone two new actor of the current one
     */
    @Override
    public void actorAction(ActiveActor actor) {
        if (overlapped(actor)) {
            actor.cloneActor();
        }
    }
}