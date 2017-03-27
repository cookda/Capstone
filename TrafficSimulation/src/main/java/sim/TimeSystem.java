package sim;

/**
 * Created by Wayne on 3/4/2017.
 */
public class TimeSystem {

    private long time;
    private double timeSpeed = 1; //This can be set to 0.75, 1.25, 1.5, etc to provide a faster/slower simulation.
    private final static TimeSystem instance = new TimeSystem();

    private TimeSystem() {
        time = 0;
    }


    public static TimeSystem getInstance() {
        return instance;
    }

    public void stepRan() {
        time += timeSpeed;
        try {
            Thread.sleep((long) (100/timeSpeed));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
