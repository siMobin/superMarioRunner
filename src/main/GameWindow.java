package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import interfaces.GameSettings;

public class GameWindow implements GameSettings {
    // Timing...
    private static final long FRAME_TIME = (long) (1000.0 / game_fps);

    public GameWindow() {
        JFrame mainGameWindow = new JFrame(WINDOW_TITLE); // Create the main game window
        mainGameWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT); // Set the window size
        mainGameWindow.setResizable(WINDOW_RESIZABLE);

        GamePanel gamePanel = new GamePanel(); // Create the game panel
        mainGameWindow.add(gamePanel);

        mainGameWindow.addKeyListener(gamePanel); // Add the game panel as a key listener

        mainGameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the window to exit on close (ensure the
                                                                       // program closes when the window is closed)
        // Set the icon image for the JFrame
        ImageIcon icon = new ImageIcon("lib/icon_sm.png"); // app icon
        mainGameWindow.setIconImage(icon.getImage());
        // Set the window to the screen
        mainGameWindow.setLocationRelativeTo(null); // Set the window to the center of the screen
        mainGameWindow.setVisible(true);
        mainGameWindow.setAlwaysOnTop(WINDOW_ALWAYS_ON_TOP); // Set the window index (z)

        // Start the game loop
        startGameLoop(gamePanel);
    }

    /**
     * Starts the game loop for the given game panel.
     *
     * @param gamePanel the game panel to start the game loop for
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
