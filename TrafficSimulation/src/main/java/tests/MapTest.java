package tests;

import api.OSMGrabber;
import cache.CacheHandler;
import core.UserProfile;


/**
 * Created by aron on 2/13/17.
 */
public class MapTest {

    public static void basicTest() {

        OSMGrabber test = new OSMGrabber();
        CacheHandler ch = UserProfile.getInstance().getCache();
        if (!ch.isCached()) {
            test.getArea();
            ch.cacheArea();
        } else {
            System.out.println("Area cached");
        }
    }
}
