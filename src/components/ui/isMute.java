package components.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import components.utility.Resource;
import interfaces.Drawable;
import main.GamePanel;

public class isMute implements Drawable {

    private static final BufferedImage MUTE_IMAGE = new Resource().getResourceImage("/noSound.png");

    /**
     * Draws the mute icon on the graphics object.
     * 
     * Check if the sound is on or off
     * 
     * @see GamePanel#paintComponent(Graphics g)
     * 
     * @param g the graphics object to draw on
     * @return void
     */
    public void draw(Graphics g) {
        g.drawImage(MUTE_IMAGE, 5, 5, null);
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void reset() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reset'");
    }
}
