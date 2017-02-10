package api;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by aron on 2/9/17.
 */
public class OSMGrabber {

    private final static String OSM_URL = "http://overpass-api.de/api/map";

    public void getArea(double minLong, double maxLong, double minLat, double maxLat) {
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
            BufferedWriter bwr = new BufferedWriter(new FileWriter(new File("C:/Users/gigaw/CapstoneData/mapdata.dat")));
            bwr.write(resp.toString());
            bwr.close();
            System.out.println(resp.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
