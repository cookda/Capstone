package vehicle;

/**
 * Created by aron on 1/30/17.
 */
public enum VehicleType {

    CAR("Car", 2), TRUCK("Truck", 5);

    private String name;
    private int wheelCount;

    VehicleType(String name, int wheelCount) {
        this.name = name;
        this.wheelCount = wheelCount;
    }

    public int getWheelCount() {
        return wheelCount;
    }

    public String getName() {
        return name;
    }
}
