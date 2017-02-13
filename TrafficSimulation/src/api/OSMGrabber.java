package api;

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

    public void getArea(double lat, double lon, double radius) {
        getArea(lat - radius, lat + radius, lon - radius, lon + radius);
    }

    public void getArea(double minLat, double maxLat, double minLong, double maxLong) {
        try {
            URL osmUrl = new URL(OSM_URL+"?bbox="+minLong+","+minLat+","+maxLong+","+maxLat);
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
            //System.getProperty("string") takes a String that Java has defined. Googling Java System Properties will show them all and what they refer to
            //user.home refers to /home/user on Linux and C:\Users\Username on Windows
            double rad = maxLat - minLat;
            double cntrLat = minLat + rad;
            double cntrLon = minLong + rad;
            BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(UserProfile.DATA_DIR + cntrLat + "_" + cntrLon + ".dat")));
            bwr.write(resp.toString());
            bwr.close();
            System.out.println(resp.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Now saves an image instead of returning
     * @param latitude
     * @param longitude
     * @param zoomLevel
     */
    public void getImage(double latitude, double longitude, int zoomLevel) {
        try {
            URL imgUrl = new URL(UserProfile.IMG_URL + "lat=" + latitude + "&lon=" + longitude + "&zoom=" + zoomLevel);
            HttpURLConnection con = (HttpURLConnection) imgUrl.openConnection();
            con.setRequestMethod("GET");

            Image returned = ImageIO.read(con.getInputStream());

            try {
                ImageIO.write((BufferedImage) returned, "png", new File(UserProfile.IMAGE_DIR + latitude + "_" + longitude + "_" + zoomLevel + ".png"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
