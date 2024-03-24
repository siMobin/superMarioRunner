package components.utility;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ComponentImage {
    public BufferedImage image;
    public int x, y;
    public Color debugColor;

    /**
     * This constructor initializes the image, x and y coordinates, and debugColor
     * of the image component.
     * 
     * @param image
     * @param x
     * @param y
     * @param debugColor
     */
    public ComponentImage(BufferedImage image, int x, int y, Color debugColor) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.debugColor = debugColor;
    }

    /**
     * This constructor initializes the image, x and debugColor
     * of the image component.
     *
     * @overload Contractor@ComponentImage(BufferedImage image, int x, int y, Color
     *           debugColor)
     * @param image
     * @param x
     * @param debugColor
     */
    public ComponentImage(BufferedImage image, int x, Color debugColor) {
        this.image = image;
        this.x = x;
        this.debugColor = debugColor;
    }
}
