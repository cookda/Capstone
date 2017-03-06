package core;

import cache.CacheHandler;
import gui.SimFrame;
import tests.DomTest;
import tests.MapTest;
import tests.MapViewerTest;

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

        MapTest.basicTest();
        DomTest.getNodes();
        SimFrame sf = new SimFrame();
        //MapViewerTest.testJX();
    }
}
