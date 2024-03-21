package components.background;

// import components.ui.Score;
import components.utility.ComponentImage;
import components.utility.Resource;
import interfaces.Drawable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static main.GamePanel.*;
import static main.GameWindow.WINDOW_HEIGHT;
import static main.GameWindow.WINDOW_WIDTH;

public class Background implements Drawable {
    private static final BufferedImage CLOUD_IMAGE = new Resource().getResourceImage("/assets/Cloud.png");
    private static final BufferedImage CLOUD_IMAGE_2 = new Resource().getResourceImage("/assets/Cloud-2.png");

    private final int backgroundSpeed = gameSpeed / 5;

    private BackgroundColors backgroundColor;

    private static ArrayList<ComponentImage> cloudImages;
    private static final ComponentImage firstCloud = new ComponentImage(CLOUD_IMAGE, WINDOW_WIDTH - 700, 40,
            Color.LIGHT_GRAY);
    private static final ComponentImage secondCloud = new ComponentImage(CLOUD_IMAGE_2, WINDOW_WIDTH - 400, 20,
            Color.LIGHT_GRAY);
    private static final ComponentImage thirdCloud = new ComponentImage(CLOUD_IMAGE, WINDOW_WIDTH - 200, 80,
            Color.LIGHT_GRAY);

    public Background() {
        backgroundColor = BackgroundColors.DEFAULT;

        backgroundInit();
    }

    /**
     * Set the background color.
     *
     * @param backgroundColor the background color to set
     */
    public void setBackgroundColor(BackgroundColors backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    private void backgroundInit() {
        cloudImages = new ArrayList<>();
        cloudImages.add(firstCloud);
        cloudImages.add(secondCloud);
        cloudImages.add(thirdCloud);
    }

    /**
     * Change the background color based on the score.
     *
     */
    // private void changeBackgroundColor() {
    // if (Score.score > 0 && Score.score % 600 == 0 && backgroundColor !=
    // BackgroundColors.DARK) {
    // setBackgroundColor(BackgroundColors.DARK);
    // } else if (Score.score > 0 && Score.score % 800 == 0) {
    // setBackgroundColor(BackgroundColors.DEFAULT);
    // }
    // }

    /**
     * Update the position of the clouds and change the background color.
     *
     */
    @Override
    public void update() {
        firstCloud.x -= backgroundSpeed;
        secondCloud.x -= backgroundSpeed;
        thirdCloud.x -= backgroundSpeed;

        if (firstCloud.x <= -firstCloud.image.getWidth()) {
            firstCloud.x = WINDOW_WIDTH;
        }

        if (secondCloud.x <= -secondCloud.image.getWidth()) {
            secondCloud.x = WINDOW_WIDTH;
        }

        if (thirdCloud.x <= -thirdCloud.image.getWidth()) {
            thirdCloud.x = WINDOW_WIDTH;
        }

        // changeBackgroundColor();
    }

    /**
     * Draws the graphics with the specified background color and cloud images.
     *
     * @param g Graphics object to be drawn
     * @return void
     */
    @Override
    public void draw(Graphics g) {
        switch (backgroundColor) {
            case DEFAULT:
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
     * Reset the object to its initial state.
     *
     * @param none
     * @return void
     */
    @Override
    public void reset() {
        backgroundInit();
        backgroundColor = BackgroundColors.DEFAULT;
    }
}
