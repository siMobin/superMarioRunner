package main;

import javax.swing.*;
// import java.awt.*;

public class GameWindow {
    private static final String WINDOW_TITLE = "Supper Mario";
    private static final boolean WINDOW_RESIZABLE = false;
    private static final boolean WINDOW_ALWAYS_ON_TOP = false;

    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 240;

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
    }
}
