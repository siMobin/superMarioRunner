package components.utility;

public class DeltaTime {
    private final int DELTA_TIME;
    private long lastTime;

    public DeltaTime(int deltaTime) {
        this.DELTA_TIME = deltaTime;
    }

    /**
     * Checks if the delta time is available to execute the update. If it is, it
     * returns true, otherwise it returns false.
     *
     * @return
     */
    public boolean canExecute() {
        if (System.currentTimeMillis() - lastTime > DELTA_TIME) {
            lastTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }
}
