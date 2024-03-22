package components.utility;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Resource {
    /**
     * Retrieves a BufferedImage from the specified file path relative to the "lib"
     * folder.
     *
     * @param path the relative path of the image file
     * @return the BufferedImage object read from the file, or null if the file does
     *         not exist or an I/O error occurs
     */
    public BufferedImage getResourceImage(String path) {
        try {
            // Constructing the file path relative to the "lib" folder
            File file = new File("lib", path);
            // Check if the file exists before attempting to read it
            if (!file.exists()) {
                System.err.println("Image file not found: " + file.getAbsolutePath());
                return null;
            }
            BufferedImage img = ImageIO.read(file);
            System.out.println("Image: " + path);
            return img;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
