import bagel.*;
import java.io.*;
import java.util.*;

public class ShadowLife extends AbstractGame {
    private final Background background = new Background(BACKGROUND_IMAGE);
    // ArrayLists to store actors of game
    private final ArrayList<Tree> trees = new ArrayList<Tree>();
    private final ArrayList<Gatherer> gatherers = new ArrayList<Gatherer>();
    private Font font = new Font("res/VeraMono.ttf", 24);

    private static final int origin = 0;
    private static final int oneTick = 500;
    private static final int fiveTicks = 5;
    private static final String COMMA = ",";
    private static final String WORLD_FILE = "res/worlds/test.csv";
    private static final String TREE_IMAGE = "res/images/tree.png";
    private static final String GATHERER_IMAGE = "res/images/gatherer.png";
    private static final String BACKGROUND_IMAGE = "res/images/background.png";

    private int tickCount = 0;
    private long oneTickTime = System.currentTimeMillis();

    /**
     * Creates a new instance of the ShadowLife game
     */
    public ShadowLife() {
        super();
        readCSV();
    }

    /**
     * Read the provided CSV file, and add actors to ArrayList accordingly
     */
    private void readCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(WORLD_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] word = line.split(COMMA);

                // Instantiate new object and add into ArrayList
                if (word[0].equals("Tree")) {
                    trees.add(new Tree(TREE_IMAGE,
                            Integer.parseInt(word[1]), Integer.parseInt(word[2])));
                }
                else if (word[0].equals("Gatherer")) {
                    gatherers.add(new Gatherer(GATHERER_IMAGE,
                            Integer.parseInt(word[1]), Integer.parseInt(word[2])));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Update the state of the simulation
     */
    protected void update(Input input) {
        // Record the current time for calculation
        long currentTime = System.currentTimeMillis();

        // Update the position of each gatherer every tick
        if ((currentTime - oneTickTime) >= oneTick) {
            for (Gatherer gatherer : gatherers) {
                gatherer.move();
            }
            // Record the current time and increase tick by one
            oneTickTime = currentTime;
            tickCount += 1;

            // Randomize a direction of gatherer every five tick
            if (tickCount == fiveTicks) {
                for (Gatherer gatherer : gatherers) {
                    gatherer.setDirection();
                }
                // Reset tick back to zero
                tickCount = 0;
            }
        }

        // Draw background and update position of the actors
        background.drawFromTopLeft(origin, origin);
        for (Tree tree : trees) {
            font.drawString(String.valueOf(3), tree.getX(), tree.getY());
            tree.drawFromTopLeft(tree.getX(), tree.getY());
        }
        for (Gatherer gatherer : gatherers) {
            gatherer.drawFromTopLeft(gatherer.getX(), gatherer.getY());
        }
    }

    /**
     * The entry-point for the game
     */
    public static void main(String[] args) {
        ShadowLife game = new ShadowLife();
        game.run();
    }
}