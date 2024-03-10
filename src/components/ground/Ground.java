package components.ground;

import components.utility.ComponentImage;
import interfaces.Drawable;
import components.utility.Resource;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Ground implements Drawable {
    private static final int GROUND_Y_IMAGE_OFFSET = -9;
    private static final BufferedImage GROUND_IMAGE = new Resource().getResourceImage("/assets/Ground-2.png");
    public static final int GROUND_Y = 180;

    private static ArrayList<ComponentImage> groundImages;

    private static final ComponentImage firstGround = new ComponentImage(GROUND_IMAGE, 0, Color.green);
    private static final ComponentImage secondGround = new ComponentImage(GROUND_IMAGE, GROUND_IMAGE.getWidth(),
            Color.blue);

    public Ground() {
        groundInit();
    }

    private void groundInit() {
        groundImages = new ArrayList<>();

        groundImages.add(firstGround);
        groundImages.add(secondGround);
    }

    /**
     * @see ComponentImage#x Defines also inequalities arising
     *      from updating x before changing GroundImage possition (I think)
     */
    public void update() {
        firstGround.x -= GamePanel.gameSpeed;
        secondGround.x -= GamePanel.gameSpeed;

        if (firstGround.x <= -firstGround.image.getWidth()) {
            firstGround.x = secondGround.image.getWidth() + secondGround.x;
        }
        if (secondGround.x <= -secondGround.image.getWidth()) {
            secondGround.x = firstGround.image.getWidth() + firstGround.x;
        }
    }

    public void draw(Graphics g) {
        for (ComponentImage ground : groundImages) {
            if (GamePanel.debugMode) {
                g.setColor(ground.debugColor);
                g.drawLine(ground.x, GROUND_Y, ground.image.getWidth() + ground.x, GROUND_Y);
            }
            g.drawImage(ground.image, ground.x, GROUND_Y + GROUND_Y_IMAGE_OFFSET, null);
        }
    }

    @Override
    public void reset() {
        groundInit();
    }
}
