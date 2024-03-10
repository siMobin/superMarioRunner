package components.ui;

import components.utility.Resource;
import interfaces.Drawable;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.GameWindow.WINDOW_HEIGHT;
import static main.GameWindow.WINDOW_WIDTH;

public class Paused implements Drawable {
    private static final BufferedImage PAUSED_TEXT = new Resource().getResourceImage("/assets/Paused.png");

    public Paused() { }

    @Override
    public void draw(Graphics g) {
        System.out.println("draw pause");
        g.drawImage(PAUSED_TEXT, (WINDOW_WIDTH - PAUSED_TEXT.getWidth())/ 2, (WINDOW_HEIGHT - PAUSED_TEXT.getHeight()) / 2 - 70, null);
    }

    @Override
    public void update() { }

    @Override
    public void reset() { }
}
