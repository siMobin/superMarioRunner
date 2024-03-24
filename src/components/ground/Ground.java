package components.ground;

import components.utility.ComponentImage;
import interfaces.Drawable;
import components.utility.Resource;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Ground implements Drawable {
    private static final BufferedImage GROUND_IMAGE = new Resource().getResourceImage("/Ground.png");

    private static ArrayList<ComponentImage> groundImages;

    private static final ComponentImage FIRST_GROUND = new ComponentImage(GROUND_IMAGE, 0, Color.green);
    private static final ComponentImage SECOND_GROUND = new ComponentImage(GROUND_IMAGE, GROUND_IMAGE.getWidth(),
            Color.blue);

    public Ground() {
        groundInit();
    }

    /**
     * Initializes the ground by creating a new ArrayList and adding
     * the ground images to it. Also initializes the first and
     * second ground images.
     */
    private void groundInit() {
        groundImages = new ArrayList<>();

        groundImages.add(FIRST_GROUND);
        groundImages.add(SECOND_GROUND);
    }

    /**
     * @see ComponentImage#x Defines also inequalities arising
     *      from updating x before changing GroundImage position (I think)
     */
    public void update() {
        FIRST_GROUND.x -= GamePanel.gameSpeed;
        SECOND_GROUND.x -= GamePanel.gameSpeed;

        if (FIRST_GROUND.x <= -FIRST_GROUND.image.getWidth()) {
            FIRST_GROUND.x = SECOND_GROUND.image.getWidth() + SECOND_GROUND.x;
        }
        if (SECOND_GROUND.x <= -SECOND_GROUND.image.getWidth()) {
            SECOND_GROUND.x = FIRST_GROUND.image.getWidth() + FIRST_GROUND.x;
        }
    }

    /**
     * Draws the ground images on the graphics object.
     *
     * @param g the graphics object on which to draw
     * @return void
     */
    public void draw(Graphics g) {
        for (ComponentImage ground : groundImages) {
            if (GamePanel.debugMode) {
                g.setColor(ground.debugColor);
                g.drawLine(ground.x, GROUND_Y, ground.image.getWidth() + ground.x, GROUND_Y);
            }
            g.drawImage(ground.image, ground.x, GROUND_Y + ground_y_image_offset, null);
        }
    }

    @Override
    public void reset() {
        groundInit();
    }
}
