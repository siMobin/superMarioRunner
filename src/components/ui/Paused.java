package components.ui;

import components.utility.Resource;
import interfaces.Drawable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Paused implements Drawable {
    private static final BufferedImage PAUSED_TEXT = new Resource().getResourceImage("/Paused.png");

    public Paused() {
        //
    }

    /**
     * Draws the PAUSED_TEXT image on the graphics object at the center of the
     * window.
     *
     * @param g the graphics object to draw on
     * @return void
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(PAUSED_TEXT, (WINDOW_WIDTH - PAUSED_TEXT.getWidth()) / 2,
                (WINDOW_HEIGHT - PAUSED_TEXT.getHeight()) / 2 - 70, null);
    }

    @Override
    public void update() {
    }

    @Override
    public void reset() {
    }
}
