package components.utility;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ResizeImage {
    /**
     * Get a resized image with the specified height.
     *
     * @param path   the path to the image file
     * @param height the desired height of the resized image
     * @return the resized BufferedImage
     */
    public static BufferedImage getResizedImage(String path, int height) {
        try {
            // Load original image from file
            File file = new File("lib/" + path); // 'lib' folder is in the project root
            BufferedImage originalImage = ImageIO.read(file);

            // Resize the image
            double scaleFactor = (double) height / originalImage.getHeight();
            int targetWidth = (int) (originalImage.getWidth() * scaleFactor);
            BufferedImage resizedImage = new BufferedImage(targetWidth, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(originalImage.getScaledInstance(targetWidth, height, Image.SCALE_SMOOTH), 0, 0, null);
            g.dispose();

            // Output resizing information
            System.out.println("Image: " + path);
            System.out.println("Image resized: " + targetWidth + "x" + height);

            return resizedImage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
