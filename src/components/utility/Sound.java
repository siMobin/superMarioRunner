package components.utility;

import javax.sound.sampled.*;
import java.net.URL;

/**
 * Resources:
 *  - https://stackoverflow.com/questions/2416935/how-to-play-wav-files-with-java
 */

public class Sound {
    private final URL path;

    private Clip clip;
    private AudioInputStream ais;
    private LineListener event;

    public Sound(String path) {
        this.path = getClass().getResource(path);
        System.out.println("Sound loaded: " + getClass().getResource(path));
    }

    public void play() {
        try {
            ais = AudioSystem.getAudioInputStream(path);
            clip = AudioSystem.getClip();
            event = event -> {
                if (event.getType() == LineEvent.Type.STOP) clip.close();
            };
            clip.addLineListener(event);
            clip.open(ais);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playInLoop() {
        try {
            ais = AudioSystem.getAudioInputStream(path);
            clip = AudioSystem.getClip();
            clip.open(ais);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * It needs to be done before playing song
     *
     * It is important to remove line listener here, because
     * LineListener will try to execute code which can freeze the game
     * if someone will rapid click buttons in longer sounds
     */
    public void stop() {
        clip.removeLineListener(event);
        clip.stop();
    }

    public boolean isOpen() {
        return clip.isOpen();
    }

    public boolean isNull() {
        return clip == null;
    }
}
