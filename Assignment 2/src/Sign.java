/**
 * A type of Stationary Actor
 */

public class Sign extends StationActor {

    /** The type of this actor */
    public static final String TYPE = "Sign";
    /** The type of Left Sign */
    public static final String LEFT = "SignLeft";
    /** The type of Up Sign */
    public static final String UP = "SignUp";
    /** The type of Right Sign*/
    public static final String RIGHT = "SignRight";
    /** The type of Down Sign */
    public static final String DOWN = "SignDown";

    private final int direction;

    /**
     * Creates a new Tree
     *
     * @param direction Direction of the sign
     * @param x X coordinate of Sign
     * @param y Y coordinate of Sign
     * @param world The world the Sign belongs to
     */
    public Sign(int direction, int x, int y, ShadowLife world) {
        super(chooseFile(direction), TYPE, x, y, world);
        this.direction = direction;
    }

    /**
     * Factory method that returns a file string according to
     * the direction given
     */
    private static String chooseFile(int direction) {
        if (direction == Direction.LEFT) {
            return "res/images/left.png";
        }
        else if (direction == Direction.UP) {
            return "res/images/up.png";
        }
        else if (direction == Direction.RIGHT) {
            return "res/images/right.png";
        }
        else {
            return "res/images/down.png";
        }
    }

    /**
     * Takes an active actor and check if it's overlapping and
     * update direction of Active actor to match the sign
     */
    @Override
    public void actorAction(ActiveActor actor) {
        if (overlapped(actor)) {
            actor.setDirection(direction);
        }
    }
}
