package components.utility;

import javax.sound.sampled.*;
import java.io.File;

public class Sound {
    private final File FILE;

    private Clip clip;
    private AudioInputStream ais;
    private LineListener event;

    public Sound(String fileName) {
        // Construct the file path relative to the project's root directory
        this.FILE = new File("lib/audio/" + fileName);
        // Print out the file path for debugging purposes
        System.out.println("Audio: " + fileName);
    }

    /**
     * Plays the audio file.
     *
     * This function initializes the audio input stream and clip, sets up a line
     * event listener to handle the clip's stop event,
     * opens the clip with the audio input stream, and starts the clip.
     *
     * @throws Exception if there is an error in initializing the audio input stream
     *                   or clip.
     */
    public void play() {
        try {
            ais = AudioSystem.getAudioInputStream(FILE);
            clip = AudioSystem.getClip();
            event = event -> {
                if (event.getType() == LineEvent.Type.STOP)
                    clip.close();
            };
            clip.addLineListener(event);
            clip.open(ais);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * A method to play the audio file in a continuous loop.
     */
    public void playInLoop() {
        try {
            ais = AudioSystem.getAudioInputStream(FILE);
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

    /**
     * Check if the object is open.
     *
     * @return true if the object is open, false otherwise
     */
    public boolean isOpen() {
        return clip.isOpen();
    }

    /**
     * Checks if the clip is null.
     *
     * @return true if the clip is null, false otherwise
     */
    public boolean isNull() {
        return clip == null;
    }
}
