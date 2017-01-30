package core;

import vehicle.VehicleType;
import vehicle.Vehicle;

/**
 * Created by aron on 1/25/17.
 * Mainly used to introduce new branch
 */
public class Tester {

    public static void main(String[] args) {
        Vehicle v = new Vehicle(VehicleType.CAR);

        System.out.println(v.getType().getName());
        v.setType(VehicleType.TRUCK);
        System.out.println(v.getType().getName());
    }
}
