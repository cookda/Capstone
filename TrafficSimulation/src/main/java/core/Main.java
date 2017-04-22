package core;

import cache.CacheHandler;
import gui.MapViewer;
import gui.SimFrame;
import nodes.impl.TNode;
import sim.Agent;
import sim.AgentPool;
import sim.SearchAlgs.AStar;
//import sim.SearchAlgs.Graph;
import sim.Simulation;
import tests.AgentGeneratorTests;
import tests.DomTest;
import tests.MapTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                        0.002
                )
        );
        up.setCache(new CacheHandler());

        MapTest.basicTest();
        DomTest.getNodes();
        MapViewer mv = new MapViewer();
        Simulation simulation = new Simulation(mv);
        SimFrame sf = new SimFrame(simulation, mv);
        AgentGeneratorTests.testRandomGenerator1(5);

        List<Agent> agents = AgentPool.getInstance().getAgentList();

        //Graph graph = Graph.getInstance();
        //graph.buildGraph();
        agents.forEach(agent -> {
            AStar algo = new AStar(agent);
            algo.findTripNodes();
            agent.setPath(Arrays.asList(algo.getPath()));
        });

        List<List<TNode>> bigOlList = new ArrayList<>();
        agents.forEach(agent -> bigOlList.add(agent.getPath()));

        mv.setPath(bigOlList);
        Agent tester = AgentPool.getInstance().getAgentList().get(0);
        tester.getPath().forEach(System.out::println);
        System.out.println(tester.getTrip().getKey());
        System.out.println(tester.getTrip().getValue());

        System.out.println("Set the path");

        //new Thread(simulation::run).start();
        //MapViewerTest.testJX();
    }


}
