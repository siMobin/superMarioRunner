package main;

import components.background.Background;
import components.mario.Mario;
import components.ground.Ground;
import components.obstacles.Obstacles;
import components.ui.GameOver;
import components.ui.Intro;
import components.ui.Paused;
import components.ui.Score;
import interfaces.GameSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable, KeyListener, GameSettings {
    private static final int game_fps = 60;

    private Thread mainThread = new Thread(this);

    public static boolean debugMode = false;
    public static int gameSpeed = game_start_speed;
    public static boolean isGameSpeedChanged = false;

    public boolean running = false;
    public boolean paused = false;
    public boolean gameOver = false;
    public boolean intro = true;
    final Object pauseLock = new Object();

    Mario mario = new Mario();
    Ground ground = new Ground();
    Obstacles obstacles = new Obstacles();
    Background background = new Background();

    Score scoreUI = new Score();
    GameOver gameOverUI = new GameOver();
    Paused pausedUI = new Paused();
    Intro introUI = new Intro();

    public GamePanel() {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLayout(null);
        setVisible(true);

        add(introUI.introLabel);

        mainThread.start();
    }

    /**
     * Starts the game by setting the running flag to true, stopping the intro
     * music, and hiding the intro UI.
     *
     * @param None
     * @return None
     */
    public void startGame() {
        System.out.println("\nGame log");
        System.out.println("-----------------------------------------------------");

        running = true;
        intro = false;
        introUI.overworld.stop(); // stop the intro music
        if (running == true) {
            System.out.println("Running...");
        }
    }

    /**
     * Resets the game state and starts a new game.
     *
     * This function sets the gameOver flag to false, the running flag to true,
     * and resets the game speed to the initial value. It also resets the score,
     * mario, obstacles, ground, and background objects. Finally, it starts a new
     * thread to run the game loop.
     *
     * @param None
     * @return None
     */
    public void resetGame() {
        gameOver = false;
        running = true;

        gameSpeed = game_start_speed;

        scoreUI.reset();
        mario.reset();
        obstacles.reset();
        ground.reset();
        background.reset();
        mainThread = new Thread(this);
        mainThread.start();
    }

    /**
     * Pauses the game by setting the `paused` flag to true and playing the
     * `playInLoop` method of the `introUI.overworld` object.
     * Prints "Paused" to the console.
     */
    public void pauseGame() {
        paused = true;
        introUI.overworld.playInLoop();
        System.out.println("Paused");
    }

    /**
     * Resumes the game by setting the 'paused' flag to false, stopping the
     * 'introUI.overworld' and notifying the 'pauseLock' to wake up any waiting
     * threads. Also prints "Resumed" to the console.
     */
    public void resumeGame() {
        synchronized (pauseLock) {
            paused = false;
            introUI.overworld.stop();
            pauseLock.notify();
            System.out.println("Resumed");
        }
    }

    /**
     * @Experimental!
     *                Changes the game speed if the score is greater than 0 and is a
     *                multiple of 260, and the game speed has not already been
     *                changed
     *                and is less than the maximum game speed.
     *
     * @return void
     */
    private void changeGameSpeed() {
        if (Score.score > 0 && Score.score % 260 == 0 && !isGameSpeedChanged && gameSpeed < game_max_speed) {
            isGameSpeedChanged = true;
            gameSpeed += 1;
        }
    }

    /**
     * MAIN PAINT METHOD
     * --------------------------------------------------------
     * 
     * @param g Graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        background.draw(g);

        if (paused)
            pausedUI.draw(g);
        if (gameOver)
            gameOverUI.draw(g);
        if (!intro)
            scoreUI.draw(g);

        ground.draw(g);
        mario.draw(g);
        obstacles.draw(g);

        if (intro)
            introUI.draw(g);
    }

    /**
     * MAIN GAME LOOP
     * It is probably the simplest version
     * ------------------------------------------------------------------------
     * Good resources:
     * -
     * https://gamedev.stackexchange.com/questions/160329/java-game-loop-efficiency
     * - https://stackoverflow.com/questions/18283199/java-main-game-loop
     */
    @Override
    public void run() {
        // INTRO LOOP FOR EASTER EGG
        while (intro) {
            try {
                int msPerFrame = 1000 / game_fps;
                Thread.sleep(msPerFrame);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }

        // MAIN GAME LOOP
        while (running) {

            // GAME TIMING
            try {
                int msPerFrame = 1000 / game_fps;
                Thread.sleep(msPerFrame);

                if (paused) {
                    synchronized (pauseLock) {
                        repaint();
                        pauseLock.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // GAME LOGIC
            changeGameSpeed();

            scoreUI.update();
            background.update();
            mario.update();
            ground.update();
            obstacles.update();

            if (obstacles.isCollision()) {
                mario.die();
                if (Mario.isMario)
                    introUI.overworld.stop();
                scoreUI.writeHighScore();
                gameOver = true;
                running = false;
                System.out.println("Game over");
            }
            // RENDER OUTPUT
            repaint();
        }
    }

    /**
     * KEY BINDINGS
     *
     * -------------------------------------------
     * Debug mode: '`'
     * Jump: ' ', 'w', 'ARROW UP'
     * Fall: 's', 'ARROW DOWN'
     * Pause: 'p', 'ESC'
     * -------------------------------------------
     * 
     * @param e KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // DEBUG
        if (e.getKeyChar() == '`') {
            debugMode = !debugMode;
        }

        // JUMP
        if (e.getKeyChar() == ' ' || e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP) {
            if (!paused && running) {
                mario.jump();
            } else if (paused && running) {
                resumeGame();
            }

            if (!running && !gameOver) {
                startGame();
                mario.run();
                mario.jump();
            } else if (gameOver) {
                resetGame();
            }
        }

        // FALL
        if (e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (!paused && running) {
                mario.fall();
            }
        }

        // PAUSE
        if (e.getKeyChar() == 'p' || e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (!paused && running) {
                pauseGame();
            } else if (paused && running) {
                resumeGame();
            }
        }
    }

    /**
     * Just checking if someone change mind to jump
     * right after hitting ground
     * --------------------------------------------------------
     * 
     * @param e KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == ' ' || e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP)
            mario.jumpRequested = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
