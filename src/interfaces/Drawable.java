package interfaces;

import java.awt.*;

public interface Drawable extends GameSettings {
    void update();

    void draw(Graphics g);

    void reset();
}
