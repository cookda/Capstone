package core;

import cache.CacheHandler;
import gui.SimFrame;
import sim.Simulation;
import tests.AgentGeneratorTests;
import tests.DomTest;
import tests.MapTest;

/**
 * Created by aron on 3/20/17.
 */
public class Main {


    public static void main(String[] args) {
        UserProfile up = UserProfile.getInstance();
        up.setMap(
                new UserMap(
                        Constants.BOONE_SMALL_LAT,
                        Constants.BOONE_SMALL_LON,
                        0.010
                )
        );
        up.setCache(new CacheHandler());

        MapTest.basicTest();
        DomTest.getNodes();
        SimFrame sf = new SimFrame();
        AgentGeneratorTests.testRandomGenerator(400);
        Simulation simulation = new Simulation(sf.getMapViewer());
        simulation.runStep();
        //MapViewerTest.testJX();
    }


}
