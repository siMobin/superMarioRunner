package components.obstacles;

import components.mario.Mario;
import components.utility.Coordinates;
import interfaces.Drawable;
import components.utility.Resource;
import main.GamePanel;

import java.awt.*;
import java.util.ArrayList;

public class Obstacles implements Drawable {
    private static final int OBSTACLES_MIN_SPACE_BETWEEN = 250;
    private static final int OBSTACLES_MAX_SPACE_BETWEEN = 500;
    private static final int OBSTACLES_FIRST_OBSTACLE_X = 600;
    private static final int RANGE_SPACE_BETWEEN_OBSTACLES = OBSTACLES_MAX_SPACE_BETWEEN - OBSTACLES_MIN_SPACE_BETWEEN
            + 1;

    private static final ArrayList<ObstacleImage> OBSTACLE_IMAGES = new ArrayList<>();

    private static final int MAX_INCOMING_OBSTACLES = 5;
    private ArrayList<ObstacleImage> incomingObstacles;

    public Obstacles() {
        ObstacleImage enemy_1 = new ObstacleImage(new Resource().getResourceImage("/assets/obstacles/Cactus-1-c.png"));
        ObstacleImage enemy_2 = new ObstacleImage(new Resource().getResourceImage("/assets/obstacles/Cactus-2-c.png"));
        ObstacleImage enemy_3 = new ObstacleImage(new Resource().getResourceImage("/assets/obstacles/shit.png"));
        ObstacleImage enemy_5 = new ObstacleImage(new Resource().getResourceImage("/assets/obstacles/tree-root-1.png"));
        ObstacleImage enemy_6 = new ObstacleImage(new Resource().getResourceImage("/assets/obstacles/mushroom-1.png"));
        ObstacleImage enemy_4 = new ObstacleImage(new Resource().getResourceImage("/assets/obstacles/mushroom-2.png"));

        OBSTACLE_IMAGES.add(enemy_1);
        OBSTACLE_IMAGES.add(enemy_2);
        OBSTACLE_IMAGES.add(enemy_3);
        OBSTACLE_IMAGES.add(enemy_4);
        OBSTACLE_IMAGES.add(enemy_5);
        OBSTACLE_IMAGES.add(enemy_6);

        initFirstObstacles();
    }

    private void initFirstObstacles() {
        incomingObstacles = new ArrayList<>();

        for (int i = 0; i < MAX_INCOMING_OBSTACLES; i++) {
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

    private ObstacleImage getRandomObstacle() {
        int randCactus = (int) (Math.random() * (OBSTACLE_IMAGES.size()));
        ObstacleImage randObstacle = OBSTACLE_IMAGES.get(randCactus);

        return new ObstacleImage(randObstacle.getOBSTACLE_IMAGE(), getRandomSpace());
    }

    private ObstacleImage getRandomObstacle(int x) {
        int randCactus = (int) (Math.random() * (OBSTACLE_IMAGES.size()));
        ObstacleImage randObstacle = OBSTACLE_IMAGES.get(randCactus);

        return new ObstacleImage(randObstacle.getOBSTACLE_IMAGE(), x, getRandomSpace());
    }

    private int getRandomSpace() {
        return (int) (Math.random() * RANGE_SPACE_BETWEEN_OBSTACLES) + OBSTACLES_MIN_SPACE_BETWEEN;
    }

    public boolean isCollision() {
        for (ObstacleImage obstacle : incomingObstacles) {
            for (Coordinates marioCoordinates : Mario.constructedCoordinates)
                if (marioCoordinates.intersects(obstacle.coordinates)) {
                    return true;
                }
        }
        return false;
    }

    @Override
    public void reset() {
        initFirstObstacles();
    }

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

    @Override
    public void draw(Graphics g) {
        for (ObstacleImage obstacle : incomingObstacles) {
            if (GamePanel.debugMode) {
                g.setColor(obstacle.getDebugColor());
                g.drawRect(obstacle.coordinates.x, obstacle.coordinates.y, obstacle.coordinates.width,
                        obstacle.coordinates.height);
            }
            g.drawImage(obstacle.getOBSTACLE_IMAGE(), obstacle.getX(), obstacle.getY(), null);
        }
    }
}
