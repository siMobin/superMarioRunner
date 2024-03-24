package components.background;

import components.utility.ComponentImage;
import components.utility.Resource;
import interfaces.Drawable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import static main.GamePanel.*;

public class Background implements Drawable {
    private static final BufferedImage[] CLOUD_IMAGES;
    private final int backgroundSpeed = gameSpeed / 5;
    private BackgroundColors backgroundColor;
    private static ArrayList<ComponentImage> cloudImages;

    static {
        CLOUD_IMAGES = new BufferedImage[CLOUD_LEVEL + 1]; // +1 for a safe memory allocation...
        for (int i = 0; i <= CLOUD_LEVEL; i++) {
            CLOUD_IMAGES[i] = new Resource().getResourceImage("/cloud/cloud_" + i + ".png");
        }
        System.out.println("Cloud level: " + CLOUD_LEVEL);
    }

    public Background() {
        backgroundColor = BackgroundColors.DEFAULT;
        backgroundInit();
    }

    /**
     * Initializes the background by generating a specified number of cloud images
     * at random positions within the window.
     *
     * @param None
     * @return None
     */
    private void backgroundInit() {
        cloudImages = new ArrayList<>();
        Random random = new Random();

        System.out.print("Cloud density: ");
        for (int i = 0; i <= cloud_density; i++) {
            int z = (int) (Math.random() * WINDOW_HEIGHT);
            System.out.print(z + " ");
            int y = random.nextInt(WINDOW_HEIGHT - z); // Random y within the window height
            int x = random.nextInt(WINDOW_WIDTH); // Random x within the window width
            BufferedImage cloudImage = CLOUD_IMAGES[random.nextInt(CLOUD_IMAGES.length)]; // Random cloud image
            ComponentImage cloud = new ComponentImage(cloudImage, x, y, Color.WHITE);
            cloudImages.add(cloud);
        }
        System.out.println();
    }

    /**
     * Updates the cloud images by moving them horizontally and resetting their
     * position if they move off the screen.
     */
    @Override
    public void update() {
        for (ComponentImage cloud : cloudImages) {
            cloud.x -= backgroundSpeed;
            if (cloud.x <= -cloud.image.getWidth()) {
                cloud.x = WINDOW_WIDTH;
            }
        }
    }

    /**
     * Draws the background based on the specified background color.
     *
     * @param g the Graphics object used for drawing
     */
    @Override
    public void draw(Graphics g) {

        switch (backgroundColor) {
            case DEFAULT:
                defaultColor(g);
                break;
            case DARK:
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
                break;
        }

        for (ComponentImage clouds : cloudImages) {
            if (debugMode) {
                g.setColor(clouds.debugColor);
                g.drawRect(clouds.x, clouds.y, clouds.image.getWidth(), clouds.image.getHeight());
            }
            g.drawImage(clouds.image, clouds.x, clouds.y, null);
        }
    }

    /**
     * Set the default color and fill the entire window with it.
     *
     * @param g The graphics object to set the color and fill the window
     */
    public void defaultColor(Graphics g) {
        g.setColor(DEFAULT_COLOR);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    /**
     * Reset the object by initializing the background and setting the background
     * color to default.
     */
    @Override
    public void reset() {
        backgroundInit();
        backgroundColor = BackgroundColors.DEFAULT;
    }

}
