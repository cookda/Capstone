package test;

import api.OSMGrabber;
import cache.CacheHandler;
import gui.MainFrame;

import java.io.IOException;

/**
 * Created by aron on 2/13/17.
 */
public class MapTest {

    public static void basicTest() {

        final double BOONE_LAT = 36.2168;
        final double BOONE_LONG = -81.6746;

        OSMGrabber test = new OSMGrabber();
        double lat = BOONE_LAT;
        double lon = BOONE_LONG;
        double rad = 0.02;
        CacheHandler ch = new CacheHandler(lat, lon, rad);
        if (!ch.isCached()) {
            test.getArea(lat, lon, rad);
            test.getImage(lat, lon, 15);
            ch.cacheArea();
        } else {
            System.out.println("Area cached");
        }
    }

    public static void guiTest() throws IOException {
        MainFrame mf = new MainFrame();
    }
}
