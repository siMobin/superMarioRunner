package interfaces;

import java.awt.Color;
import java.awt.GraphicsEnvironment;

/**
 * This interface defines the game settings.
 * 
 * It provides constants for configuring various aspects of the game, such as
 * window properties, game physics, ground and obstacle parameters, Mario's
 * attributes, and score settings.
 * 
 * Using this interface ensures consistency in setting values across different
 * parts of the game and facilitates quick access to configuration parameters.
 * 
 * 
 * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
 * 
 * Joshua Bloch, "Effective Java - Programming Language Guide":
 * -------------------------------------------------------------------------------------------------------
 * The constant interface pattern is a poor use of interfaces. That a class uses
 * some constants internally is an implementation detail. Implementing a
 * constant interface causes this implementation detail to leak into the class's
 * exported API.
 * It is of no consequence to the users of a class that the class implements
 * a constant interface. In fact, it may even confuse them.
 * 
 * Worse, it represents a commitment: if in a future release the class is
 * modified so that it no longer needs to use the constants, it still must
 * implement the interface to ensure binary compatibility.
 * If a nonfinal class implements a constant interface, all of its subclasses
 * will have their namespaces polluted by the constants in the interface.
 * ------------------------------------------------------------------------------------------------------
 *
 * @since 71dadc8
 * @date 2024-03-20
 * 
 * @version 3.0.0
 * @date 2024-05-28
 * @author [siMobin](https://github.com/siMobin)
 */

public interface GameSettings {
    final public static boolean DEBUGGER = true;

    /**
     * General options
     * Game Window & Others
     * https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/index.html
     */
    static final String WINDOW_TITLE = "Super Mario Runner";
    static final boolean WINDOW_RESIZABLE = false;
    static final boolean WINDOW_ALWAYS_ON_TOP = false;
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 260;

    /**
     * General Physics,
     * Ground options.
     * Contain all the necessary physics for the game
     * https://stackoverflow.com/questions/18283199/java-main-game-loop
     */
    // int game_fps = 120;
    // Use the screen refresh rate so game speed behavior is consistent
    final int game_fps = GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getScreenDevices()[0].getDisplayMode().getRefreshRate();

    float game_gravity = 0.55f;
    int game_speed = 6;
    static int game_start_speed = 6;
    static int game_max_speed = 12;
    // public static int gameSpeed = game_start_speed;
    //
    final int GROUND_Y = 220;
    int ground_y_image_offset = -9;
    // int GROUND_SPEED = game_speed;
    static final int CLOUD_LEVEL = 14;
    static int cloud_density = 3;
    final Color DEFAULT_COLOR = new Color(150, 220, 250, 85);

    /**
     * Obstacles options
     */
    final int OBSTACLES_MIN_SPACE_BETWEEN = 250;
    final int OBSTACLES_MAX_SPACE_BETWEEN = 500;
    final int OBSTACLES_FIRST_OBSTACLE_X = 600;
    static int max_incoming_obstacles = 10;
    // int OBSTACLES_SPEED = game_speed;

    /**
     * Mario's attributes.
     */
    static final int MARIO_FRAME = 11;
    static final int MARIO_JUMP_STRENGTH = 11;
    static final int MARIO_FALL_STRENGTH = 9;
    static final float MARIO_START_X = 50;
    static int mario_running_animation_delta_time = MARIO_FRAME * 2;

    /**
     * Score settings.
     */
    static final String SCORE_FILE_NAME = "HighScore";
    final int SCORE_MAX_ZEROS = 5;
    final int SCORE_MAX_HIGH_SCORE = 99999;
}
