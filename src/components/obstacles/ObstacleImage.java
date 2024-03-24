package components.obstacles;

import components.utility.Coordinates;
import components.utility.ComponentImage;

import java.awt.*;
import java.awt.image.BufferedImage;

import static components.ground.Ground.GROUND_Y;

public class ObstacleImage {
    private final ComponentImage OBSTACLE_IMAGE;
    private int spaceBehind;

    public Coordinates coordinates;

    public ObstacleImage(BufferedImage OBSTACLE_IMAGE) {
        this.OBSTACLE_IMAGE = new ComponentImage(OBSTACLE_IMAGE, 0, GROUND_Y - OBSTACLE_IMAGE.getHeight(), Color.red);
        this.spaceBehind = 0;

        coordinates = new Coordinates(this.OBSTACLE_IMAGE.x,
                this.OBSTACLE_IMAGE.y,
                OBSTACLE_IMAGE.getWidth(),
                OBSTACLE_IMAGE.getHeight());
    }

    public ObstacleImage(BufferedImage OBSTACLE_IMAGE, int spaceBehind) {
        this.OBSTACLE_IMAGE = new ComponentImage(OBSTACLE_IMAGE, 0, GROUND_Y - OBSTACLE_IMAGE.getHeight(), Color.red);
        this.spaceBehind = spaceBehind;

        coordinates = new Coordinates(this.OBSTACLE_IMAGE.x,
                this.OBSTACLE_IMAGE.y,
                OBSTACLE_IMAGE.getWidth(),
                OBSTACLE_IMAGE.getHeight());
    }

    public ObstacleImage(BufferedImage OBSTACLE_IMAGE, int x, int spaceBehind) {
        this.OBSTACLE_IMAGE = new ComponentImage(OBSTACLE_IMAGE, x, GROUND_Y - OBSTACLE_IMAGE.getHeight(), Color.red);
        this.spaceBehind = spaceBehind;

        coordinates = new Coordinates(this.OBSTACLE_IMAGE.x,
                this.OBSTACLE_IMAGE.y,
                OBSTACLE_IMAGE.getWidth(),
                OBSTACLE_IMAGE.getHeight());
    }

    /**
     * Set the space behind value.
     *
     * @param spaceBehind the new space behind value to set
     */
    public void setSpaceBehind(int spaceBehind) {
        this.spaceBehind = spaceBehind;
    }

    /**
     * Set the x-coordinate for the obstacle image and coordinates.
     *
     * @param x the new x-coordinate value
     */
    public void setX(int x) {
        this.OBSTACLE_IMAGE.x = x;
        coordinates.x = x;
    }

    /**
     * Retrieves the value of the x coordinate for the obstacle image.
     *
     * @return the x coordinate of the obstacle image
     */
    public int getX() {
        return OBSTACLE_IMAGE.x;
    }

    /**
     * Retrieves the y-coordinate of the OBSTACLE_IMAGE.
     *
     * @return the y-coordinate of the OBSTACLE_IMAGE
     */
    public int getY() {
        return OBSTACLE_IMAGE.y;
    }

    /**
     * Retrieves the debug color.
     *
     * @return the debug color
     */
    public Color getDebugColor() {
        return OBSTACLE_IMAGE.debugColor;
    }

    /**
     * Retrieves the OBSTACLE_IMAGE.
     *
     * @return the OBSTACLE_IMAGE as a BufferedImage.
     */
    public BufferedImage getOBSTACLE_IMAGE() {
        return OBSTACLE_IMAGE.image;
    }

    /**
     * Returns the value of the spaceBehind variable.
     *
     * @return the value of the spaceBehind variable
     */
    public int getSpaceBehind() {
        return spaceBehind;
    }
}
