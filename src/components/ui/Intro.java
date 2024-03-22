package components.ui;

import components.utility.Resource;
import components.utility.Sound;
import interfaces.Drawable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;

public class Intro implements Drawable {
    private static BufferedImage text = new Resource().getResourceImage("/Intro-mario.png");

    public JLabel introLabel = new JLabel();

    public final Sound overworld = new Sound("/mario/overworld.wav");

    public Intro() {
        introLabel.setBounds((WINDOW_WIDTH - text.getWidth()) / 2, (WINDOW_HEIGHT - text.getHeight()) / 2 - 50,
                text.getWidth(), text.getHeight());
        introLabel.addMouseListener(new MouseAdapter() {
            // @Override
            // public void mouseClicked(MouseEvent e) {
            // changeIntroTextToMario();
            // overworld.playInLoop();
            // Mario.isMario = true;
            // }
        });
    }

    /**
     * Sets the visibility of the intro label.
     *
     * @param val the new visibility value
     */
    public void setVisible(boolean val) {
        introLabel.setVisible(val);
    }

    /**
     * Draws an image on the graphics context at the center of the window.
     *
     * @param g the graphics context to draw on
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(text, (WINDOW_WIDTH - text.getWidth()) / 2, (WINDOW_HEIGHT - text.getHeight()) / 2 - 50, null);
    }

    @Override
    public void update() {
    }

    @Override
    public void reset() {
    }
}
