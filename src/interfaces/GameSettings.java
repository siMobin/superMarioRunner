package interfaces;

/**
 * Code below is dumped, because:
 *
 * Joshua Bloch, "Effective Java - Programming Language Guide":
 * -------------------------------------------------------------------------------------------------------
 * The constant interface pattern is a poor use of interfaces. That a class uses
 * some constants
 * internally is an implementation detail. Implementing a constant interface
 * causes this
 * implementation detail to leak into the class's exported API. It is of no
 * consequence to the users
 * of a class that the class implements a constant interface. In fact, it may
 * even confuse them.
 * Worse, it represents a commitment: if in a future release the class is
 * modified so that it no
 * longer needs to use the constants, it still must implement the interface to
 * ensure binary
 * compatibility. If a nonfinal class implements a constant interface, all of
 * its subclasses will have
 * their namespaces polluted by the constants in the interface.
 * ------------------------------------------------------------------------------------------------------
 */

public interface GameSettings {
    // /**
    // * General options
    // */
    // String WINDOW_TITLE = "SUPER T-REX RUN (PJ EDITION)";
    // int WINDOW_WIDTH = 800;
    // int WINDOW_HEIGHT = 240;
    // boolean WINDOW_RESIZABLE = false;
    // boolean WINDOW_ALWAYS_ON_TOP = false;
    //
    // boolean DEBUG_MODE = true;
    //
    // int GAME_FPS = 60;
    // float GAME_GRAVITY = 0.64f;
    // int GAME_SPEED = 6;
    //
    // /**
    // * Ground option
    // */
    // int GROUND_Y = 180;
    // int GROUND_Y_IMAGE_OFFSET = -9;
    // int GROUND_SPEED = GAME_SPEED;
    //
    // /**
    // * Obstacles option
    // */
    // int OBSTACLES_MIN_SPACE_BETWEEN = 250;
    // int OBSTACLES_MAX_SPACE_BETWEEN = 500;
    // int OBSTACLES_FIRST_OBSTACLE_X = 600;
    // int OBSTACLES_SPEED = GAME_SPEED;
    //
    // /**
    // * MARIO option
    // */
    // int MARIO_JUMP_STRENGTH = 12;
    // int MARIO_FALL_STRENGTH = 8;
    // float MARIO_START_X = 50;
    // int MARIO_RUNNING_ANIMATION_DELTA_TIME = 60;
    //
    // /**
    // * Score option
    // */
    // int SCORE_MAX_ZEROS = 5;
    // int SCORE_DELTA_TIME = 100 / GAME_SPEED * 5;
}
