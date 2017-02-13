package core;

import api.OSMGrabber;
import cache.CacheHandler;
import gui.MainFrame;
import vehicle.VehicleType;
import vehicle.Vehicle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by aron on 1/25/17.
 * Mainly used to introduce new branch
 */
public class Tester {

    private final static double BOONE_LAT = 36.2168;
    private final static double BOONE_LONG = -81.6746;

    public static void main(String[] args) {

        OSMGrabber test = new OSMGrabber();
        double lat = BOONE_LAT;
        double lon = BOONE_LONG;
        double rad = 0.03;
        CacheHandler ch = new CacheHandler(lat, lon, rad);
        if (!ch.isCached()) {
            test.getArea(lat, lon, rad);
            test.getImage(lat, lon, 15);
            ch.cacheArea();
        } else {
            System.out.println("Area cached");
        }

    }
}
