package components.obstacles;

import components.mario.Mario;
import components.utility.Coordinates;
import interfaces.Drawable;
import components.utility.Resource;
import main.GamePanel;

import java.awt.*;
import java.util.ArrayList;

public class Obstacles implements Drawable {
    private static final int RANGE_SPACE_BETWEEN_OBSTACLES = OBSTACLES_MAX_SPACE_BETWEEN - OBSTACLES_MIN_SPACE_BETWEEN
            + 1;
    private static final ArrayList<ObstacleImage> OBSTACLE_IMAGES = new ArrayList<>();

    private ArrayList<ObstacleImage> incomingObstacles;

    public Obstacles() {
        ObstacleImage enemy_1 = new ObstacleImage(new Resource().getResourceImage("/obstacles/Cactus-1-c.png"));
        ObstacleImage enemy_2 = new ObstacleImage(new Resource().getResourceImage("/obstacles/Cactus-2-c.png"));
        ObstacleImage enemy_3 = new ObstacleImage(new Resource().getResourceImage("/obstacles/shit.png"));
        ObstacleImage enemy_4 = new ObstacleImage(new Resource().getResourceImage("/obstacles/tree-root-1.png"));
        //
        ObstacleImage enemy_5 = new ObstacleImage(new Resource().getResourceImage("/obstacles/mushroom-1.png"));
        ObstacleImage enemy_6 = new ObstacleImage(new Resource().getResourceImage("/obstacles/mushroom-2.png"));
        ObstacleImage enemy_7 = new ObstacleImage(new Resource().getResourceImage("/obstacles/mushroom-3.png"));
        ObstacleImage enemy_8 = new ObstacleImage(new Resource().getResourceImage("/obstacles/mushroom-4.png"));
        ObstacleImage enemy_9 = new ObstacleImage(new Resource().getResourceImage("/obstacles/mushroom-5.png"));
        ObstacleImage enemy_10 = new ObstacleImage(new Resource().getResourceImage("/obstacles/mushroom-6.png"));
        ObstacleImage enemy_11 = new ObstacleImage(new Resource().getResourceImage("/obstacles/mushroom-7.png"));
        //
        ObstacleImage enemy_12 = new ObstacleImage(new Resource().getResourceImage("/obstacles/stone-0.png"));
        ObstacleImage enemy_13 = new ObstacleImage(new Resource().getResourceImage("/obstacles/stone-1.png"));
        ObstacleImage enemy_14 = new ObstacleImage(new Resource().getResourceImage("/obstacles/stone-2.png"));

        OBSTACLE_IMAGES.add(enemy_1);
        OBSTACLE_IMAGES.add(enemy_2);
        OBSTACLE_IMAGES.add(enemy_3);
        OBSTACLE_IMAGES.add(enemy_4);
        OBSTACLE_IMAGES.add(enemy_5);
        OBSTACLE_IMAGES.add(enemy_6);
        OBSTACLE_IMAGES.add(enemy_7);
        OBSTACLE_IMAGES.add(enemy_8);
        OBSTACLE_IMAGES.add(enemy_9);
        OBSTACLE_IMAGES.add(enemy_10);
        OBSTACLE_IMAGES.add(enemy_11);
        OBSTACLE_IMAGES.add(enemy_12);
        OBSTACLE_IMAGES.add(enemy_13);
        OBSTACLE_IMAGES.add(enemy_14);

        initFirstObstacles();
    }

    /**
     * Initializes the first set of obstacles.
     */
    private void initFirstObstacles() {
        incomingObstacles = new ArrayList<>();

        for (int i = 0; i < max_incoming_obstacles; i++) {
            ObstacleImage rand = getRandomObstacle();

            incomingObstacles.add(rand);
            if (i == 0) {
                incomingObstacles.get(0).setX(OBSTACLES_FIRST_OBSTACLE_X);
            } else {
                incomingObstacles.get(i)
                        .setX(incomingObstacles.get(i - 1).getX() + incomingObstacles.get(i - 1).getSpaceBehind());
            }
        }
    }

    /**
     * Generates a random obstacle image.
     *
     * @return a new ObstacleImage with a randomly selected image and space
     */
    private ObstacleImage getRandomObstacle() {
        int randCactus = (int) (Math.random() * (OBSTACLE_IMAGES.size()));
        ObstacleImage randObstacle = OBSTACLE_IMAGES.get(randCactus);

        return new ObstacleImage(randObstacle.getOBSTACLE_IMAGE(), getRandomSpace());
    }

    /**
     * Generate a random obstacle image at the given x coordinate.
     *
     * @param x the x coordinate for the obstacle image
     * @return the randomly generated obstacle image
     */
    private ObstacleImage getRandomObstacle(int x) {
        int randCactus = (int) (Math.random() * (OBSTACLE_IMAGES.size()));
        ObstacleImage randObstacle = OBSTACLE_IMAGES.get(randCactus);

        return new ObstacleImage(randObstacle.getOBSTACLE_IMAGE(), x, getRandomSpace());
    }

    /**
     * A method to return a random space between obstacles.
     *
     * @return the random space between obstacles
     */
    private int getRandomSpace() {
        return (int) (Math.random() * RANGE_SPACE_BETWEEN_OBSTACLES) + OBSTACLES_MIN_SPACE_BETWEEN;
    }

    /**
     * Checks if there is a collision between Mario and any incoming obstacles.
     *
     * @return true if there is a collision, false otherwise
     */
    public boolean isCollision() {
        for (ObstacleImage obstacle : incomingObstacles) {
            for (Coordinates marioCoordinates : Mario.constructedCoordinates)
                if (marioCoordinates.intersects(obstacle.coordinates)) {
                    return true;
                }
        }
        return false;
    }

    /**
     * Resets the function by initializing the first obstacles.
     *
     * @param None
     * @return None
     */
    @Override
    public void reset() {
        initFirstObstacles();
    }

    /**
     * Updates the position of the incoming obstacles and generates new obstacles
     * when the first one goes off the screen.
     *
     * This function iterates over each obstacle in the incomingObstacles list and
     * updates its x-coordinate by subtracting the game speed.
     * If the first obstacle goes off the screen (i.e., its x-coordinate is less
     * than - its width), a new obstacle is generated.
     * The last obstacle in the list is used to determine the position and space
     * behind the new obstacle.
     * The first obstacle in the list is removed from the list, and a new obstacle
     * is added at the end of the list with a random position and space behind.
     *
     * @return void
     */
    @Override
    public void update() {
        for (ObstacleImage obstacle : incomingObstacles) {
            obstacle.setX(obstacle.getX() - GamePanel.gameSpeed);
        }

        if (incomingObstacles.get(0).getX() < -incomingObstacles.get(0).getOBSTACLE_IMAGE().getWidth()) {
            ObstacleImage lastIncomingObstacle = incomingObstacles.get(incomingObstacles.size() - 1);

            incomingObstacles.remove(0);
            incomingObstacles
                    .add(getRandomObstacle(lastIncomingObstacle.getX() + lastIncomingObstacle.getSpaceBehind()));
            incomingObstacles.get(incomingObstacles.size() - 1).setSpaceBehind(getRandomSpace());
        }
    }

    /**
     * Draw existing obstacles on the graphics object. Check for collision with
     * Mario and remove obstacles that are out of the screen.
     *
     * @param g the Graphics object to draw on
     * @return void
     */
    @Override
    public void draw(Graphics g) {
        // Draw existing obstacles
        for (int i = 0; i < incomingObstacles.size(); i++) {
            ObstacleImage obstacle = incomingObstacles.get(i);
            if (GamePanel.debugMode) {
                g.setColor(obstacle.getDebugColor());
                g.drawRect(obstacle.coordinates.x, obstacle.coordinates.y, obstacle.coordinates.width,
                        obstacle.coordinates.height);
            }
            g.drawImage(obstacle.getOBSTACLE_IMAGE(), obstacle.getX(), obstacle.getY(), null);

            // If the obstacle is out of the screen, remove it and add a new one
            if (obstacle.getX() < -obstacle.getOBSTACLE_IMAGE().getWidth()) {
                incomingObstacles.remove(i);
                i--; // Decrement i because we removed an obstacle
                ObstacleImage lastIncomingObstacle = incomingObstacles.get(incomingObstacles.size() - 1);
                incomingObstacles
                        .add(getRandomObstacle(lastIncomingObstacle.getX() + lastIncomingObstacle.getSpaceBehind()));
                incomingObstacles.get(incomingObstacles.size() - 1).setSpaceBehind(getRandomSpace());
            }
        }
    }
}
