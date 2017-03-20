package sim;

/**
 * Created by Wayne on 3/4/2017.
 */
public class TimeSystem {

    private long time;
    private double timeSpeed = 1; //This can be set to 0.75, 1.25, 1.5, etc to provide a faster/slower simulation.
    public boolean running;

    public TimeSystem() {
        time = 0;
        running = true;
        run();
    }

    /**
     * In this method, we will do something along the following
     * Increment time
     * Get all agents from the UserProfile or whichever class stores them
     * For each agent, retrieve a distance calculation based on their position and the direction they are traveling
     * Store this calculated distance as the new position of the agent
     * Send an update call to the renderer
     * The renderer will then draw the new position of each agent
     *
     * The running boolean will be set to start/stop/etc based on the gui
     */
    public void run() {
        while (running) {
            time += timeSpeed;
        }
    }


    public void runStep() {
        time += timeSpeed;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getTimeSpeed() {
        return timeSpeed;
    }

    public void setTimeSpeed(double timeSpeed) {
        this.timeSpeed = timeSpeed;
    }
}
