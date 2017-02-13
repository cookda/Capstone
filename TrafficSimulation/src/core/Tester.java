package core;

import api.OSMGrabber;
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
        test.getArea(BOONE_LAT, BOONE_LONG, 0.03);

        File f = new File(UserProfile.USER_DIR + "img.png");
        BufferedImage img = (BufferedImage) test.getImage(BOONE_LAT, BOONE_LONG, 15);
        try {
            ImageIO.write(img, "png", f);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
