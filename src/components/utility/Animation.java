package components.utility;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Useful resource:
 * -
 * https://gamedev.stackexchange.com/questions/112805/putting-each-animation-on-a-thread
 * - https://gamedev.stackexchange.com/questions/111741/calculating-delta-time
 */

public class Animation {
    private final DeltaTime DELTA_TIME;

    private ArrayList<BufferedImage> frames;
    private int index;

    /**
     * Constructs an Animation object with a given frame delay in milliseconds.
     * The delay is the amount of time between each frame in the animation.
     *
     * @param deltaTime the frame delay in milliseconds
     */
    public Animation(int deltaTime) {
        frames = new ArrayList<>();
        index = 0;

        this.DELTA_TIME = new DeltaTime(deltaTime);
    }

    /**
     * This method is called to update the state of the object. It checks if the
     * delta time is available to execute the update. If it is, it increments the
     * index and resets it to 0 if it exceeds the size of the frames array.
     *
     * @param None
     * @return None
     */
    public void update() {
        if (DELTA_TIME.canExecute()) {
            index++;
            if (index >= frames.size()) {
                index = 0;
            }
        }
    }

    /**
     * Adds a frame to the list of frames.
     *
     * @param frame the BufferedImage frame to be added
     */
    public void addFrame(BufferedImage frame) {
        frames.add(frame);
    }

    /**
     * Returns the current frame in the list of frames.
     *
     * @param None
     * @return the current frame in the list of frames
     */
    public BufferedImage getFrame() {
        return frames.get(index);
    }
}
