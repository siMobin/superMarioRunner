package components.ui;

import components.utility.Sound;
import components.utility.DeltaTime;
import interfaces.Drawable;
import interfaces.SoundManager;
import main.GamePanel;

import java.awt.*;
import java.io.*;

import static main.GamePanel.gameSpeed;

public class Score implements Drawable, SoundManager {
    private static final int SCORE_DELTA_TIME = 100 / gameSpeed * 5;

    private static final DeltaTime DELTA_TIME = new DeltaTime(SCORE_DELTA_TIME);
    private static final Sound SCORE_SOUND = new Sound("/flag.wav");

    private static boolean isPlayed = false;
    private static boolean isMuted = false;

    private static int highScore = readHighScore();
    public static int score = 0;

    public Score() {
        //
    }

    /**
     * Builds a score string with leading zeros.
     *
     * @param score the score to be formatted
     * @return the formatted score string
     */
    private String scoreBuilder(int score) {
        StringBuilder ret = new StringBuilder(Integer.toString(score));
        char zero = '0';

        for (int i = 0; i < SCORE_MAX_ZEROS - Integer.toString(score).length(); i++) {
            ret.insert(0, zero);
        }

        return ret.toString();
    }

    /**
     * Toggles the mute state of the audio, stopping or playing the sound
     * accordingly.
     */
    public void toggleMic() {
        isMuted = !isMuted;
    }

    /**
     * Plays a sound if the score is a multiple of 100 and the sound has not been
     * played yet.
     *
     * @return void
     */
    private void playSound() {
        if (score > 0 && score % 100 == 0 && !isPlayed && !isMuted) {
            isPlayed = true;
            SCORE_SOUND.play();
        }
    }

    /**
     * Checks if the current score is higher than the high score.
     *
     * @return true if the current score is higher than the high score, false
     *         otherwise
     */
    private boolean isHighScore() {
        return highScore < score;
    }

    /**
     * Returns a string representation of the given score. If the score is greater
     * than the maximum high score,
     * it returns the string representation of the maximum high score. Otherwise, it
     * returns the string
     * representation of the score using the scoreBuilder method.
     *
     * @param score the score to be converted to a string
     * @return the string representation of the score
     */
    private String printScore(int score) {
        return score > SCORE_MAX_HIGH_SCORE ? Integer.toString(SCORE_MAX_HIGH_SCORE) : scoreBuilder(score);
    }

    /**
     * Checks the existence of a file.
     *
     * @return true if the file exists, false otherwise
     */
    private static boolean checkFileExistence() {
        File file = new File(SCORE_FILE_NAME);
        return file.exists();
    }

    /**
     * Reads the high score from a file if it exists, and returns it as an integer.
     *
     * @return the high score as an integer
     */
    private static int readHighScore() {
        long highScore = 0;
        if (checkFileExistence()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(SCORE_FILE_NAME));
                highScore = Long.parseLong(reader.readLine());
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Score system: High score read");
        System.out.println("High score: " + highScore);
        return (int) highScore;
    }

    /**
     * https://stackoverflow.com/questions/12350248/java-difference-between-filewriter-and-bufferedwriter
     * -------------------------------------------------------------------------------------------------------
     * BufferedWriter is more efficient if you
     * - have multiple writes between flush/close
     * - ! the writes are small compared with the buffer size.
     * -------------------------------------------------------------------------------------------------------
     * BufferedWriter is more efficient. It saves up small writes and writes in one
     * larger chunk if memory
     * serves me correctly. If you are doing lots of small writes then I would use
     * BufferedWriter. Calling
     * write calls to the OS which is slow so having as few writes as possible is
     * usually desirable.
     * -------------------------------------------------------------------------------------------------------
     */
    public void writeHighScore() {
        if (isHighScore()) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(SCORE_FILE_NAME));
                writer.write(Integer.toString(score));
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            highScore = score;
            System.out.println("Score system: New high score (" + highScore + ")");
        }
    }

    /**
     * Updates the state of the object.
     *
     * This method is called to update the state of the object. It checks if the
     * delta time is available to execute the update. If it is, it sets the isPlayed
     * flag to false, sets the isGameSpeedChanged flag to false, increments the
     * score by 1, and plays the sound.
     */
    @Override
    public void update() {
        if (DELTA_TIME.canExecute()) {
            isPlayed = false;
            GamePanel.isGameSpeedChanged = false;
            score += 1;
            playSound();
        }
    }

    /**
     * Draws the score and high score on the graphics object.
     *
     * @param g the graphics object on which to draw
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Consolas", Font.BOLD, 18));
        g.drawString(printScore(score), WINDOW_WIDTH - 100, 40);
        g.setColor(Color.GRAY);
        g.drawString("HI " + printScore(highScore), WINDOW_WIDTH - 200, 40);
    }

    /**
     * Resets the score to 0.
     *
     */
    @Override
    public void reset() {
        score = 0;
    }
}
