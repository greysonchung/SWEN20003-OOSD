/**
 * An active actor
 */
public abstract class ActiveActor extends Actor {
    private int direction;
    private boolean active;
    private boolean carrying;

    /** A list of actor that inherits ActiveActor */
    public static final String[] activeType = {"Gatherer", "Thief"};

    /**
     * Creates a new active actor.
     *
     * @param filename The image of the actor
     * @param type The type of the actor
     * @param x X coordinate of the actor's position
     * @param y Y coordinate of the actor's position
     * @param world The world that the actor belongs to
     */
    public ActiveActor(String filename, String type, int x, int y, ShadowLife world) {
        super(filename, type, x, y, world);
    }

    /**
     * Returns the direction of the actor.
     */
    public int getDirection() { return direction; }

    /**
     * Assign a new direction for the actor.
     * @param direction A direction integer.
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * Returns the active status of the actor.
     */
    public boolean getActive() { return active; }

    /**
     * Assign a new active status for the actor.
     * @param status A boolean that represent active status.
     */
    public void setActive(boolean status) {
        this.active = status;
    }

    /**
     * Returns the carrying status of the actor.
     */
    public boolean getCarrying() { return carrying; }

    /**
     * Assign a new carrying status for the actor.
     * @param carrying A boolean which states the carrying status of a actor
     */
    public void setCarrying(boolean carrying) {
        this.carrying = carrying;
    }

    /**
     * An abstract method that create two new active actor
     */
    public abstract void cloneActor();

    /**
     * Updates the position of the actor according to its direction
     */
    public void move() {
        if (active) {
            switch (direction) {
                case Direction.UP:
                    setCoordinate(0, -ShadowLife.TILE_SIZE);
                    break;
                case Direction.DOWN:
                    setCoordinate(0, ShadowLife.TILE_SIZE);
                    break;
                case Direction.LEFT:
                    setCoordinate(-ShadowLife.TILE_SIZE, 0);
                    break;
                case Direction.RIGHT:
                    setCoordinate(ShadowLife.TILE_SIZE, 0);
                    break;
            }
        }
    }

    /**
     * Checks with stationary actors of ShadowLife and update their status
     * based on which stationary actor was encountered.
     */
    public void checkWithStationActor() {
        for (StationActor actor : getWorld().getStationActors()) {
            actor.actorAction(this);
        }
    }
}