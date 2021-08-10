/**
 * A type of Storage Stationary Actor
 */

public class Stockpile extends Storage {

    /** The type of this actor */
    public static final String TYPE = "Stockpile";

    /**
     * Creates a new Stockpile
     *
     * @param x X coordinate of Stockpile
     * @param y Y coordinate of Stockpile
     * @param world The world the stockpile belongs to
     */
    public Stockpile(int x, int y, ShadowLife world) {
        super("res/images/cherries.png", TYPE, x, y, world);
    }

    /**
     * Takes an active actor and check if it's overlapping and
     * update direction and status of Stockpile and Active actor accordingly
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
            if (!actor.getCarrying()) {
                if (getFruitCount() >= 1) {
                    actor.setCarrying(true);
                    ((Thief) actor).setConsume(false);
                    decreaseFruitNum();
                    actor.setDirection(Direction.rotateClockWise(actor.getDirection()));
                }
            }
            else {
                actor.setDirection(Direction.rotateClockWise(actor.getDirection()));
            }
        }
    }
}
