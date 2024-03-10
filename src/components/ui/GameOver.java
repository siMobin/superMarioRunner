package components.ui;

import interfaces.Drawable;
import components.utility.Resource;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static main.GameWindow.WINDOW_HEIGHT;
import static main.GameWindow.WINDOW_WIDTH;

public class GameOver implements Drawable {
    private static final BufferedImage text = new Resource().getResourceImage("/assets/Game-over.png");
    private static final BufferedImage restartButton = new Resource().getResourceImage("/assets/Restart.png");

    public GameOver() {
        //
    }

    @Override
    public void update() {
        //
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(text, (WINDOW_WIDTH - text.getWidth()) / 2, (WINDOW_HEIGHT - text.getHeight()) / 2 - 70, null);
        g.drawImage(restartButton, (WINDOW_WIDTH - restartButton.getWidth()) / 2,
                (WINDOW_HEIGHT - restartButton.getHeight()) / 2 - 30, null);
    }

    @Override
    public void reset() {
        //
    }
}
