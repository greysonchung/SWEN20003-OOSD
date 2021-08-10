import bagel.*;

import java.io.*;
import java.nio.file.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.nio.charset.Charset;

/**
 * ShadowLife, a simulation game
 */
//This file contains content from the sample solution for Project 1
public class ShadowLife extends AbstractGame {

    /** The length and width of one tile */
    public static final int TILE_SIZE = 64;

    /** The error code for exception */
    public static final int ERROR_CODE = -1;

    private final int tickRate;
    private final int maxTicks;
    private int tickElapsed = 0;
    private final String worldFile;
    private long lastTick = System.currentTimeMillis();
    private final ArrayList<ActiveActor> activeActors = new ArrayList<>();
    private final ArrayList<StationActor> stationActors = new ArrayList<>();
    private final Image background = new Image("res/images/background.png");

    /**
     * Creates a new ShadowLife world
     *
     * @param tickRate Time in milliseconds between each tick
     * @param maxTicks Maximum number of ticks
     * @param worldFile A list of Actors and coordinates
     * @throws IllegalArgumentException Thrown to indicate an inappropriate argument
     */
    public ShadowLife(int tickRate, int maxTicks, String worldFile)
            throws IllegalArgumentException {
        if (tickRate < 0 || maxTicks < 0) {
            throw new IllegalArgumentException();
        }
        this.tickRate = tickRate;
        this.maxTicks = maxTicks;
        this.worldFile = worldFile;
        loadActors();
    }

    /**
     * Returns an ArrayList of Active Actors of the world
     */
    public ArrayList<ActiveActor> getActiveActors() {
        return activeActors;
    }

    /**
     * Returns an ArrayList of Station Actors of the world
     */
    public ArrayList<StationActor> getStationActors() {
        return stationActors;
    }
    
    private void loadActors() {
        int count = 0;
        ActorFactory factory = new ActorFactory();
        try (BufferedReader reader = new BufferedReader(new FileReader(worldFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Line format is: type,x,y
                String[] parts = line.split(",");
                count++;

                // Check if the read in file is valid format
                if (!checkValidLine(parts)) {
                    System.out.println("error: in file \"<" + worldFile + ">\" at line <" + count + ">");
                    System.exit(ERROR_CODE);
                }

                // Creates new actor based on the input, and add this ShadowLife
                // as the key in a one to many relationship key
                String type = parts[0];
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);
                if (Arrays.asList(ActiveActor.activeType).contains(type)) {
                    activeActors.add((ActiveActor) factory.createActor(type, x, y, this));
                }
                else if (Arrays.asList(StationActor.stationType).contains(type)) {
                    stationActors.add((StationActor) factory.createActor(type, x, y, this));
                }
            }
        }
        catch (IOException e) {
            System.out.println("error: file \"<" + worldFile + ">\" not found");
            System.exit(ERROR_CODE);
        }
    }

    @Override
    protected void update(Input input) {
        // If all the actors are set to inactive, print the result and close game
        if (checkActive()) {
            printResult();
            Window.close();
        }

        // if maximum numbers of ticks reached, close game
        if (tickElapsed == maxTicks) {
            System.out.println("Timed Out");
            Window.close();
        }

        // If enough time has passed, run the next tick
        if (System.currentTimeMillis() - lastTick >= tickRate) {
            tickElapsed += 1;
            lastTick = System.currentTimeMillis();

            // Let Gatherers perform the tick first, follow by the thief
            for (int i = 0; i < activeActors.size(); i++) {
                if (activeActors.get(i) != null && activeActors.get(i) instanceof Gatherer) {
                    activeActors.get(i).tick();
                }
            }
            for (int i = 0; i < activeActors.size(); i++) {
                if (activeActors.get(i) != null && activeActors.get(i) instanceof Thief) {
                    activeActors.get(i).tick();
                }
            }
        }

        // Draw all elements
        background.drawFromTopLeft(0, 0);
        for (StationActor actor : stationActors) {
            if (actor != null) {
                actor.render();
            }
        }
        drawActive(Gatherer.TYPE);
        drawActive(Thief.TYPE);
    }

    private boolean checkActive() {
        int count = 0;
        int nullCount = 0;
        for (ActiveActor actor : activeActors) {
            // Skip all the actors that has been set to null
            if (actor != null) {
                // Count the numbers of inactive actors
                if (!actor.getActive()) {
                    count++;
                }
            }
            // Count all the actors that has been set to null
            else {
                nullCount++;
            }
        }
        // return true if the number of inactive actors equals
        // to the number of those that has not been set to null
        return count == activeActors.size() - nullCount;
    }

    private void drawActive(String type) {
        // Draw actors on background based on the given type
        for (ActiveActor actor : activeActors) {
            if (actor != null && actor.type.equals(type)) {
                actor.render();
            }
        }
    }

    private void printResult() {
        // Print elapsed ticks and the amount of fruit of each stockpile and hoard
        System.out.println(tickElapsed + " ticks");
        for (StationActor actor : stationActors) {
            if (actor instanceof Storage) {
                System.out.println(((Storage) actor).getFruitCount());
            }
        }
    }

    private boolean checkValidLine(String[] line) {
        if (line.length != 3) {
            return false;
        }
        else return Integer.parseInt(line[1]) >= 0 && Integer.parseInt(line[2]) >= 0;
    }

    private static String[] argsFromFile() {
        try {
            return Files.readString(Path.of("res/worlds/args.txt"),
                    Charset.defaultCharset()) .split(" ");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Entry point of the game, which takes the command line argument
     */
    public static void main(String[] args) {
        int tickRate, maxTick;
        String fileName;
        ShadowLife game = null;
        try {
            String[] temp = ShadowLife.argsFromFile();
            tickRate = Integer.parseInt(temp[0]);
            maxTick = Integer.parseInt(temp[1]);
            fileName = temp[2];
            game = new ShadowLife(tickRate, maxTick, fileName);
        }
        catch (Exception e) {
            System.out.println("usage: ShadowLife <tick rate> <max ticks> <world file>");
            System.exit(ERROR_CODE);
        }
        game.run();
    }
}