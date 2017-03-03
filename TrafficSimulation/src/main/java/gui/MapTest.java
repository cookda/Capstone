package gui;

import api.OSMGrabber;
import cache.CacheHandler;
import core.UserMap;
import core.UserProfile;
import gui.MainFrame;

import java.io.IOException;

/**
 * Created by aron on 2/13/17.
 */
public class MapTest {

    public static void basicTest() {

        OSMGrabber test = new OSMGrabber();
        CacheHandler ch = UserProfile.getInstance().getCache();
        if (!ch.isCached()) {
            test.getArea();
            test.getImage();
            ch.cacheArea();
        } else {
            System.out.println("Area cached");
        }
    }

    public static void guiTest() throws IOException {
        MainFrame mf = new MainFrame();
    }
}
