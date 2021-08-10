/**
 * A type of Stationary Actor
 */

public class Pad extends StationActor {

    /** The type of this actor */
    public static final String TYPE = "Pad";

    /**
     * Creates a new pad
     *
     * @param x X coordinate of Pad
     * @param y Y coordinate of Pad
     * @param world The world the Pad belongs to
     */
    public Pad(int x, int y, ShadowLife world) {
        super("res/images/pad.png", TYPE, x, y, world);
    }

    /**
     * Takes a thief and check if it's overlapping and
     * update its consume status accordingly
     */
    @Override
    public void actorAction(ActiveActor actor) {
        if (overlapped(actor) && actor instanceof Thief) {
            ((Thief) actor).setConsume(true);
        }
    }
}
