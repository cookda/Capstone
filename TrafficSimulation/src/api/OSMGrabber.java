package api;

import core.Constants;
import core.UserMap;
import core.UserProfile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by aron on 2/9/17.
 */
public class OSMGrabber {

    private final static String OSM_URL = "http://overpass-api.de/api/map";

    private UserProfile up;
    private UserMap um;

    public OSMGrabber() {
        up = UserProfile.getInstance();
        um = up.getMap();
    }


    public void getArea() {
        try {
            double rad = um.getRadius();
            double minLat = um.getLatitude() - rad;
            double maxLat = um.getLatitude() + rad;
            double minLong = um.getLongitude() - rad;
            double maxLong = um.getLongitude() + rad;

            URL osmUrl = new URL(OSM_URL + "?bbox=" + minLong +"," + minLat + "," + maxLong + "," + maxLat);
            HttpURLConnection con = (HttpURLConnection) osmUrl.openConnection();
            con.setRequestMethod("GET");


            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            StringBuilder resp = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                resp.append(line);
                resp.append('\n');
            }

            br.close();
            con.disconnect();
            BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(Constants.DATA_DIR + um.getDataName())));
            bwr.write(resp.toString());
            bwr.close();
            System.out.println(resp.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Now saves an image instead of returning
     */
    public void getImage() {
        try {
            URL imgUrl = new URL(Constants.IMG_URL + "lat=" + um.getLatitude() + "&lon=" + um.getLongitude() + "&zoom=" + um.getZoomLevel());
            HttpURLConnection con = (HttpURLConnection) imgUrl.openConnection();
            con.setRequestMethod("GET");

            Image returned = ImageIO.read(con.getInputStream());

            try {
                ImageIO.write((BufferedImage) returned, "png", new File(Constants.IMAGE_DIR + up.getMap().getImageName()));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
