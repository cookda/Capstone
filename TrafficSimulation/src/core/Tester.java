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

    public static void main(String[] args) {
        double lat = 36.215;
        double lon = -81.685;

        OSMGrabber test = new OSMGrabber();
//        test.getArea(-81.67, -81.65, 36.19, 36.20);

        File f = new File(UserProfile.USER_DIR + "img.png");
        BufferedImage img = (BufferedImage) test.getImage(lat, lon, 14);
        try {
            ImageIO.write(img, "png", f);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
