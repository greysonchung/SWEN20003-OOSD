/**
 * A type of Storage Stationary Actor
 */

public class Hoard extends Storage {

    /** The type of this actor */
    public static final String TYPE = "Hoard";

    /**
     * Creates a new Hoard
     *
     * @param x X coordinate of Hoard
     * @param y Y coordinate of Hoard
     * @param world The world the Hoard belongs to
     */
    public Hoard(int x, int y, ShadowLife world) {
        super("res/images/hoard.png", TYPE, x, y, world);
    }

    /**
     * Takes an active actor and check if it's overlapping and
     * update direction and status of Hoard and Active actor accordingly
     */
    @Override
    public void actorAction(ActiveActor actor) {
        if (overlapped(actor) && actor instanceof Gatherer) {
            if (actor.getCarrying()) {
                actor.setCarrying(false);
                increaseFruitNum();
            }
            actor.setDirection(Direction.rotateOneEighty(actor.getDirection()));
        }

        else if (overlapped(actor) && actor instanceof Thief) {
            if (((Thief) actor).isConsume()) {
                ((Thief) actor).setConsume(false);
                if (!actor.getCarrying()) {
                    if (getFruitCount() >= 1) {
                        actor.setCarrying(true);
                        decreaseFruitNum();
                    }
                    else {
                        actor.setDirection(Direction.rotateClockWise(actor.getDirection()));
                    }
                }
            }
            else if (actor.getCarrying()) {
                actor.setCarrying(false);
                increaseFruitNum();
                actor.setDirection(Direction.rotateClockWise(actor.getDirection()));
            }
        }
    }
}