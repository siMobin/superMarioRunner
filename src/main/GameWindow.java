package main;

import javax.swing.*;

import interfaces.GameSettings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow implements GameSettings {
    // Define framerate
    // private static final int FPS = 360;
    private static final long FRAME_TIME = (long) (1000.0 / GAME_FPS);

    public GameWindow() {
        JFrame mainGameWindow = new JFrame(WINDOW_TITLE);
        mainGameWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        mainGameWindow.setResizable(WINDOW_RESIZABLE);

        GamePanel gamePanel = new GamePanel();
        mainGameWindow.add(gamePanel);

        mainGameWindow.addKeyListener(gamePanel);

        mainGameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set custom icon
        // ImageIcon icon = new ImageIcon("/assets/mario/Mario-fall.png");
        // mainGameWindow.setIconImage(icon.getImage());

        mainGameWindow.setLocationRelativeTo(null);
        mainGameWindow.setVisible(true);
        mainGameWindow.setAlwaysOnTop(WINDOW_ALWAYS_ON_TOP);

        // Start the game loop
        startGameLoop(gamePanel);
    }

    /**
     * Method to start the game loop.
     *
     * @param gamePanel the game panel to be updated and rendered
     */
    private void startGameLoop(GamePanel gamePanel) {
        Timer timer = new Timer((int) FRAME_TIME, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update game logic
                gamePanel.validate();
                // Render the game
                gamePanel.repaint();
            }
        });
        timer.start();
    }
}
