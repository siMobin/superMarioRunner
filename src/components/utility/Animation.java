package components.utility;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Useful resource:
 *  - https://gamedev.stackexchange.com/questions/112805/putting-each-animation-on-a-thread
 *  - https://gamedev.stackexchange.com/questions/111741/calculating-delta-time
 */

public class Animation {
    private final DeltaTime DELTA_TIME;

    private ArrayList<BufferedImage> frames;
    private int index;

    public Animation(int deltaTime) {
        frames = new ArrayList<>();
        index = 0;

        this.DELTA_TIME = new DeltaTime(deltaTime);
    }
    
    public void update() {
        if (DELTA_TIME.canExecute()){
            index++;
            if (index >= frames.size()) {
                index = 0;
            }
        }
    }

    public void addFrame(BufferedImage frame) {
        frames.add(frame);
    }

    public BufferedImage getFrame() {
        return frames.get(index);
    }
}
