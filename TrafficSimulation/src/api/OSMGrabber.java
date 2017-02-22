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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


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

            Path dataPath = Paths.get(Constants.DATA_DIR);
            if (!Files.exists(dataPath)) {
                Files.createDirectory(dataPath);
            }

            BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(dataPath + "/" + um.getDataName())));
            bwr.write(resp.toString());
            bwr.close();
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

            Path imgPath = Paths.get(Constants.IMAGE_DIR);
            if (!Files.exists(imgPath)) {
                Files.createDirectory(imgPath);
            }

            try {
                ImageIO.write((BufferedImage) returned, "png", new File(imgPath + "/" + up.getMap().getImageName()));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
