package core;

import cache.CacheHandler;
import gui.DomTest;
import gui.MapViewerTest;

/**
 * Created by aron on 1/25/17.
 * Mainly used to introduce new branch
 */
public class Tester {


    public static void main(String[] args) {
        UserProfile up = UserProfile.getInstance();
        up.setMap(
            new UserMap(
                Constants.BOONE_SMALL_LAT,
                Constants.BOONE_SMALL_LON,
                0.002
            )
        );
        up.setCache(new CacheHandler());

        DomTest.getNodes();
        MapViewerTest.testJX();
    }
}
