package core;

import cache.CacheHandler;
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
                Constants.BOONE_LAT,
                Constants.BOONE_LONG,
                0.03,
                15
            )
        );

        MapTest.basicTest();
    }
}
