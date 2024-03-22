package interfaces;

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
 * @version 1.0.0
 * @since 71dadc8
 * @date 2023-03-20
 * @author [siMobin](https://github.com/siMobin)
 */

public interface GameSettings {
    boolean DEBUG_MODE = true;

    /**
     * General options
     * Game Window & Others
     * https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/index.html
     */
    static final String WINDOW_TITLE = "Supper Mario";
    static final boolean WINDOW_RESIZABLE = false;
    static final boolean WINDOW_ALWAYS_ON_TOP = false;
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 260;

    /**
     * General Physics
     * Ground options
     * Contain all the necessary physics for the game
     * https://stackoverflow.com/questions/18283199/java-main-game-loop
     */
    int GAME_FPS = 360;
    float GAME_GRAVITY = 0.64f;
    int GAME_SPEED = 6;
    static int GAME_START_SPEED = 5;
    static int GAME_MAX_SPEED = 12;
    // public static int gameSpeed = GAME_START_SPEED;
    //
    final int GROUND_Y = 220;
    int GROUND_Y_IMAGE_OFFSET = -9;
    // int GROUND_SPEED = GAME_SPEED;

    /**
     * Obstacles options
     */
    final int OBSTACLES_MIN_SPACE_BETWEEN = 250;
    final int OBSTACLES_MAX_SPACE_BETWEEN = 500;
    final int OBSTACLES_FIRST_OBSTACLE_X = 600;
    static int MAX_INCOMING_OBSTACLES = 10;
    // int OBSTACLES_SPEED = GAME_SPEED;

    /**
     * Mario's attributes.
     */
    static final float MARIO_FRAME = 11;
    static final int MARIO_JUMP_STRENGTH = 13;
    static final int MARIO_FALL_STRENGTH = 8;
    static final float MARIO_START_X = 50;
    static int MARIO_RUNNING_ANIMATION_DELTA_TIME = 30;

    /**
     * Score settings.
     */
    static final String SCORE_FILE_NAME = "HighScore";
    int SCORE_MAX_ZEROS = 5;
    int SCORE_MAX_HIGH_SCORE = 99999;
}
