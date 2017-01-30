package vehicle;

import vehicle.VehicleType;

/**
 * Created by gigaw on 1/30/2017.
 */
public class Vehicle {

    private VehicleType type;

    public Vehicle(VehicleType type) {
        this.type = type;
    }

    public void setType(VehicleType v) {
        type = v;
    }

    public VehicleType getType() {
        return type;
    }

}
