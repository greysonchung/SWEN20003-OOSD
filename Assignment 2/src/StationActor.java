/**
 * A Stationary Actor
 */

public abstract class StationActor extends Actor {

    /** A list of actor that inherits StationActor */
    public static final String[] stationType = {"Tree", "GoldenTree", "Pool", "Pad", "Fence",
            "SignLeft", "SignRight", "SignUp", "SignDown", "Stockpile", "Hoard"};

    /**
     * Creates a new station actor.
     *
     * @param filename The image of the actor
     * @param type The type of the actor
     * @param x X coordinate of the actor's position
     * @param y Y coordinate of the actor's position
     * @param world The world that the actor belongs to
     */
    public StationActor(String filename, String type, int x, int y, ShadowLife world) {
        super(filename, type, x, y, world);
    }
}
