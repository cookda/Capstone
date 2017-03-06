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
                Constants.GREENSBORO_LAT,
                Constants.GREENSBORO_LON,
                0.025
            )
        );
        up.setCache(new CacheHandler());

        MapTest.basicTest();
        DomTest.getNodes();
        SimFrame sf = new SimFrame();
        //MapViewerTest.testJX();
    }
}
