package core;

import cache.CacheHandler;
import gui.SimFrame;
import sim.Agent;
import sim.AgentPool;
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
        up.getWayMap().values().forEach(way -> {
            double x = way.getNodes().get(0).getLat();
            double y = way.getNodes().get(0).getLon();
            AgentPool.getInstance().addAgent(new Agent(x, y));
        });
        //MapViewerTest.testJX();
    }
}
