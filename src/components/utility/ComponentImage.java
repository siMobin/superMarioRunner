package components.utility;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ComponentImage {
    public BufferedImage image;
    public int x, y;
    public Color debugColor;

    public ComponentImage(BufferedImage image, int x, int y, Color debugColor) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.debugColor = debugColor;
    }

    public ComponentImage(BufferedImage image, int x, Color debugColor) {
        this.image = image;
        this.x = x;
        this.debugColor = debugColor;
    }

}
