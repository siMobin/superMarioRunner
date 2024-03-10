package components.utility;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Resource {
    BufferedImage img;

    public BufferedImage getResourceImage(String path) {
        try {
            img = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Image loaded: " + getClass().getResource(path));

        return img;
    }
}
