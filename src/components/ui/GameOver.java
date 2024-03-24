package components.ui;

import interfaces.Drawable;
import components.utility.Resource;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class GameOver implements Drawable {
    private static final BufferedImage TEXT = new Resource().getResourceImage("/Game-over.png");
    private static final BufferedImage RESTART_BUTTON = new Resource().getResourceImage("/Restart.png");

    public GameOver() {
        //
    }

    /**
     * Draws the game over screen with the given graphics object.
     *
     * @param g the graphics object used for drawing
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(TEXT, (WINDOW_WIDTH - TEXT.getWidth()) / 2, (WINDOW_HEIGHT - TEXT.getHeight()) / 2 - 70, null);
        g.drawImage(RESTART_BUTTON, (WINDOW_WIDTH - RESTART_BUTTON.getWidth()) / 2,
                (WINDOW_HEIGHT - RESTART_BUTTON.getHeight()) / 2 - 30, null);
    }

    /**
     * The following methods are unimplemented/non-required.
     * 
     * @see components.Drawable#update()
     * 
     * @see components.Drawable#reset()
     */
    @Override
    public void update() {
        //
    }

    @Override
    public void reset() {
        //
    }
}
