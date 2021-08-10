import java.util.ArrayList;

/**
 * A type of active actor
 */
public class Gatherer extends ActiveActor {

    /** The type of this actor */
    public static final String TYPE = "Gatherer";

    /**
     * Creates a new Gatherer
     *
     * @param x X coordinate of Gatherer
     * @param y Y coordinate of Gatherer
     * @param world The world the Gatherer belongs to
     */
    public Gatherer(int x, int y, ShadowLife world) {
        super("res/images/gatherer.png", TYPE, x, y, world);
        setDirection(Direction.LEFT);
        setCarrying(false);
        setActive(true);
    }

    /**
     * Clone a new Gatherer
     *
     * @param direction Direction of Gatherer
     * @param x X coordinate of Gatherer
     * @param y Y coordinate of Gatherer
     * @param world The world the Gatherer belongs to
     */
    public Gatherer(int direction, int x, int y, ShadowLife world) {
        super("res/images/gatherer.png", TYPE, x, y, world);
        setDirection(direction);
        setCarrying(false);
        setActive(true);
    }

    /**
     * Update the direction of the thief if encountered
     *
     * @param actor A thief
     */
    @Override
    public void actorAction(ActiveActor actor) {
        if (overlapped(actor) && actor instanceof Thief) {
            actor.setDirection(Direction.rotateCounter(actor.getDirection()));
        }
    }

    /**
     * Update the position of Gatherer every tick and check if this Gatherer
     * has encountered other stationary actor.
     */
    @Override
    public void update() {
        move();
        checkWithStationActor();
    }

    /**
     * Clone two new Gatherer and destroy the current one.
     */
    @Override
    public void cloneActor() {
        ArrayList<ActiveActor> active = getWorld().getActiveActors();
        active.add(new Gatherer(Direction.rotateCounter(getDirection()), getX(), getY(), getWorld()));
        active.add(new Gatherer(Direction.rotateClockWise(getDirection()), getX(), getY(), getWorld()));
        active.set(active.indexOf(this), null);
    }
}