package sim;

/**
 * Created by Wayne on 3/4/2017.
 */
public class TimeSystem {

    private double time;
    private double timeSpeed = 1;
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
            Thread.sleep((long) (1000 / timeSpeed));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void reset() {
        time = 0;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getTimeSpeed() {
        return timeSpeed;
    }

    public void setTimeSpeed(double timeSpeed) {
        this.timeSpeed = timeSpeed;
    }
}
