package components.utility;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ResizeImage {
    /**
     * Resizes an image to the specified height and returns the resized image.
     *
     * @param path   the path to the original image
     * @param height the desired height of the resized image
     * @return the resized image with the specified height
     */
    public static BufferedImage getResizedImage(String path, int height) {
        // Load original image and resize it
        BufferedImage originalImage = new Resource().getResourceImage(path);
        double scaleFactor = (double) height / originalImage.getHeight();
        int targetWidth = (int) (originalImage.getWidth() * scaleFactor);
        BufferedImage resizedImage = new BufferedImage(targetWidth, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage.getScaledInstance(targetWidth, height, Image.SCALE_SMOOTH), 0, 0, null);
        g.dispose();
        System.out.println("Image loaded: " + path);
        System.out.println("Image resized: " + targetWidth + "x" + height);
        return resizedImage;
    }
}
