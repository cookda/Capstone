package core;

import cache.CacheHandler;
import test.DomTest;
import test.GuiTest;
import test.MapTest;

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
                0.005
            )
        );
        up.setCache(new CacheHandler());

        MapTest.basicTest();
        GuiTest.testGUI();
        //DomTest.printNodes();
    }
}
