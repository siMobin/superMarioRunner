package components.mario;

import components.utility.*;
import interfaces.Drawable;
import interfaces.SoundManager;
import static main.GamePanel.debugMode;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Mario implements Drawable, SoundManager {
    private static final int MARIO_BORDER_SIZE = 1; // 1px - The border size of the Mario image that is used in
                                                    // collision detection (area)
    private static final float X = MARIO_START_X; // The starting x-coordinate of the Mario (c)

    public static boolean isMario = false;
    public static boolean marioLoaded = false;
    private boolean isMuted = false;

    private static MarioStates marioState = MarioStates.IDLE;
    private final static BufferedImage JUMP = ResizeImage.getResizedImage("/mario/jump.png", 50);

    private static BufferedImage idleImage = JUMP;
    private static BufferedImage jumpImage = JUMP;
    private static BufferedImage fallImage = JUMP;
    private static Animation runAnimation = new Animation(mario_running_animation_delta_time);
    private static BufferedImage dieImage = new Resource().getResourceImage("/mario/dead.png");

    /**
     * Collision adjustments.
     * It's a modified version of bohdankordon/chrome-dino-java
     * ---------------------------------------------------------------------------------------------
     * ---------------------------------------------------------------------------------------------
     * 
     * @see https://github.com/bohdankordon/chrome-dino-java/blob/966e24fd7a85d9749056182d815503a60ddd2231/src/game_object/Dino.java
     */
    public static ArrayList<Coordinates> constructedCoordinates = new ArrayList<>();
    private static final Coordinates collisionLeft = new Coordinates(0, 15, 11 - MARIO_BORDER_SIZE,
            21 - MARIO_BORDER_SIZE);
    private static final Coordinates collisionMiddle = new Coordinates(10, 0, 22 - MARIO_BORDER_SIZE,
            45 - MARIO_BORDER_SIZE);
    private static final Coordinates collisionRight = new Coordinates(31, 0, 10 - MARIO_BORDER_SIZE,
            21 - MARIO_BORDER_SIZE);

    private static float y = GROUND_Y - idleImage.getHeight();
    private static float speedY;

    /**
     * This variable is for checking y before MARIO hit the ground.
     * Without it MARIO for msPerFrame ms is under the ground.
     */
    private static float TEMP_y;

    /**
     * It eliminates system delay between typed key event and pressed key event
     */
    public boolean jumpRequested;

    private static Sound jumpSound = new Sound("/jump.wav");
    public Sound gameOverSound = new Sound("/dead.wav");

    /**
     * Constructor for the Mario class.
     * 
     * Initializes the Mario object with default animations
     * and collision coordinates.
     * 
     * @see MarioStates // @RUNNING Animation objects...
     */
    public Mario() {
        for (int i = 0; i <= MARIO_FRAME; i++) {
            runAnimation.addFrame(ResizeImage.getResizedImage("/marioFrame/frame_" + i + ".png", 50));
        }
        System.out.println("MArio frame size: " + MARIO_FRAME);
        // Add more @Animation addFrame

        // Initialize collision coordinates
        constructedCoordinates.add(
                new Coordinates((int) X, (int) y + collisionLeft.y, collisionLeft.width, collisionLeft.height));
        constructedCoordinates.add(
                new Coordinates((int) X + collisionMiddle.x, (int) y, collisionMiddle.width, collisionMiddle.height));
        constructedCoordinates.add(
                new Coordinates((int) X + collisionRight.x, (int) y, collisionRight.width, collisionRight.height));
    }

    /**
     * Run method to set Mario's state to RUNNING.
     */
    public void run() {
        marioState = MarioStates.RUNNING;
    }

    /**
     * Toggles the mute state of the object and plays or stops the jump sound
     * accordingly.
     */
    public void toggleMic() {
        isMuted = !isMuted;
    }

    /**
     * This function is responsible for making Mario jump in the game.
     * 
     * It checks the current state of Mario and transitions him to the jumping state
     * if he is running. It also updates the vertical speed and position of Mario
     * to simulate the jump. Additionally, it stops any ongoing jump sound and plays
     * the jump sound if it is not muted.
     * 
     * If Mario is not running and is already in the air, it sets the jump requested
     * flag to true.
     *
     * @param None
     * @return None
     */
    public void jump() {
        if (marioState == MarioStates.RUNNING) {
            marioState = MarioStates.JUMPING;

            speedY = -MARIO_JUMP_STRENGTH;
            y += speedY;

            // It prevents from layering sounds and game freeze
            if (!jumpSound.isNull() && !isMuted) {
                if (jumpSound.isOpen())
                    jumpSound.stop();
            }
            if (!isMuted) {
                jumpSound.play();
            }
        } else if (isInAir()) {
            jumpRequested = true;
        }
    }

    /**
     * A method that simulates the fall of an object if it is in the air.
     */
    public void fall() {
        if (isInAir()) {
            speedY = MARIO_FALL_STRENGTH;
            y += speedY;
        }
    }

    /**
     * A method to set Mario's state to DIE and play the game over sound.
     */
    public void die() {
        marioState = MarioStates.DIE;
        if (marioState == MarioStates.DIE && !isMuted && gameOverSound != null) {
            gameOverSound.play();
        }
    }

    /**
     * Check if the Mario character is in the air.
     *
     * @return true if Mario is jumping or falling, false otherwise
     */
    public boolean isInAir() {
        return marioState == MarioStates.JUMPING || marioState == MarioStates.FALL;
    }

    /**
     * Draw state into Graphics object
     *
     * @param g Graphics object used for drawing
     */
    @Override
    public void draw(Graphics g) {
        if (debugMode) {
            for (Coordinates coordinates : constructedCoordinates) {
                g.setColor(Color.BLACK);
                g.drawRect(coordinates.x, coordinates.y, coordinates.width, coordinates.height);
            }
        }
        switch (marioState) {
            case IDLE:
                g.drawImage(idleImage, (int) X, (int) y, null);
                break;
            case JUMPING:
                g.drawImage(jumpImage, (int) X, (int) y, null);
                break;
            case FALL:
                g.drawImage(fallImage, (int) X, (int) y, null);
                break;
            case RUNNING:
                runAnimation.update();
                g.drawImage(runAnimation.getFrame(), (int) X, (int) y, null);
                break;
            case DIE:
                g.drawImage(dieImage, (int) X, (int) y, null);
                break;
            default:
                break;
        }
    }

    /**
     * Updates the position and state of the Mario character based on its current
     * speed and the state of the game.
     * If Mario is on the ground, his speed is set to 0 and he runs. If a jump was
     * requested, Mario jumps.
     * If Mario is in the air, his speed increases due to gravity and his position
     * is updated accordingly.
     * The Mario character's coordinates are updated based on its current position
     * and collision data.
     */
    @Override
    public void update() {
        if ((TEMP_y + speedY) >= GROUND_Y - idleImage.getHeight()) {
            speedY = 0;
            y = GROUND_Y - idleImage.getHeight();
            run();
            if (jumpRequested) {
                jump();
                jumpRequested = false;
            }
        } else if (isInAir()) {
            speedY += game_gravity;
            y += speedY;
            TEMP_y = y;

            if (speedY > 0)
                marioState = MarioStates.FALL;
        }
        if (constructedCoordinates.size() > 1) {
            constructedCoordinates.get(0).x = (int) X;
            constructedCoordinates.get(0).y = (int) y + collisionLeft.y;

            constructedCoordinates.get(1).x = (int) X + collisionMiddle.x;
            constructedCoordinates.get(1).y = (int) y;

            constructedCoordinates.get(2).x = (int) X + collisionRight.x;
            constructedCoordinates.get(2).y = (int) y;
        } else {
            constructedCoordinates.get(0).x = (int) X;
            constructedCoordinates.get(0).y = (int) y;
        }
    }

    /**
     * Resets the object's y position to the ground level and calls the run()
     * method.
     */
    @Override
    public void reset() {
        y = GROUND_Y - idleImage.getHeight();
        run();
    }
}
