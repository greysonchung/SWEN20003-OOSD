import java.util.ArrayList;

public class Thief extends ActiveActor {

    /** The type of this actor */
    public static final String TYPE = "Thief";
    private boolean consume = false;

    /**
     * Creates a new Thief
     *
     * @param x X coordinate of Thief
     * @param y Y coordinate of Thief
     * @param world The world the Thief belongs to
     */
    public Thief(int x, int y, ShadowLife world) {
        super("res/images/thief.png", TYPE, x, y, world);
        setDirection(Direction.UP);
        setCarrying(false);
        setActive(true);
    }

    /**
     * Clone a new Thief (use in cloneActor())
     *
     * @param direction Direction of Thief
     * @param x X coordinate of Thief
     * @param y Y coordinate of Thief
     * @param world The world the Thief belongs to
     */
    public Thief(int direction, int x, int y, ShadowLife world) {
        super("res/images/thief.png", TYPE, x, y, world);
        setDirection(direction);
        setCarrying(false);
        setActive(true);
    }

    /**
     * Return the consume status
     */
    public boolean isConsume() {
        return consume;
    }

    /**
     * Set a new consume status
     * @param consume A boolean
     */
    public void setConsume(boolean consume) {
        this.consume = consume;
    }

    /**
     * Update the position of Thief every tick and check if this Thief
     * has encountered other stationary actor.
     */
    @Override
    public void update() {
        move();
        checkWithStationActor();

        for (ActiveActor actor : getWorld().getActiveActors()) {
            if (actor != null && actor.type.equals(Gatherer.TYPE)) {
                actor.actorAction(this);
            }
        }
    }

    /**
     * Overriding method, does nothing in this class
     */
    @Override
    public void actorAction(ActiveActor actor) {
    }

    /**
     * Clone two new Thief and destroy the current one.
     */
    @Override
    public void cloneActor() {
        ArrayList<ActiveActor> active = getWorld().getActiveActors();
        active.add(new Thief(Direction.rotateCounter(getDirection()), getX(), getY(), getWorld()));
        active.add(new Thief(Direction.rotateClockWise(getDirection()), getX(), getY(), getWorld()));
        active.set(active.indexOf(this), null);
    }
}