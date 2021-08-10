/**
 * A factory for creating actor
 */

public class ActorFactory {

    /**
     * Returns an actor based on the given type
     *
     * @param type Types of an actor
     * @param x X coordinate of the actor
     * @param y Y coordinate of the actor
     * @param world The world the actor belongs to
     */
    public Actor createActor(String type, int x, int y, ShadowLife world) {
        switch (type) {
            case Gatherer.TYPE:
                return new Gatherer(x, y, world);
            case Thief.TYPE:
                return new Thief(x, y, world);
            case Tree.TYPE:
                return new Tree(x, y, world);
            case GoldenTree.TYPE:
                return new GoldenTree(x, y, world);
            case MitosisPool.TYPE:
                return new MitosisPool(x, y, world);
            case Hoard.TYPE:
                return new Hoard(x, y, world);
            case Stockpile.TYPE:
                return new Stockpile(x, y, world);
            case Pad.TYPE:
                return new Pad(x, y, world);
            case Fence.TYPE:
                return new Fence(x, y, world);
            case Sign.LEFT:
                return new Sign(Direction.LEFT, x, y, world);
            case Sign.UP:
                return new Sign(Direction.UP, x, y, world);
            case Sign.RIGHT:
                return new Sign(Direction.RIGHT, x, y, world);
            case Sign.DOWN:
                return new Sign(Direction.DOWN, x, y, world);
            default:
                break;
        }
        return null;
    }

}
