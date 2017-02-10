package core;

import api.OSMGrabber;
import gui.MainFrame;
import vehicle.VehicleType;
import vehicle.Vehicle;

/**
 * Created by aron on 1/25/17.
 * Mainly used to introduce new branch
 */
public class Tester {

    public static void main(String[] args) {
        OSMGrabber test = new OSMGrabber();
        test.getArea(-81.67, -81.65, 36.19, 36.20);
    }
}
