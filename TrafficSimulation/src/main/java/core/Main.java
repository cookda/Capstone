package core;

import cache.CacheHandler;
import gui.SimFrame;
import nodes.impl.TNode;
import sim.Agent;
import sim.AgentPool;
import sim.SearchAlgs.AStar;
import sim.SearchAlgs.Graph;
import sim.Simulation;
import tests.AgentGeneratorTests;
import tests.DomTest;
import tests.MapTest;

import java.util.ArrayList;
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
                        0.0002
                )
        );
        up.setCache(new CacheHandler());

        MapTest.basicTest();
        DomTest.getNodes();
        SimFrame sf = new SimFrame();
       // AgentGeneratorTests.testRandomGenerator(1);
        List<TNode> startList = new ArrayList<>();
        List<TNode> endList = new ArrayList<>();
        up.getWayMap().values().forEach(way -> {
            startList.add(way.getNodes().get(0));
            endList.add(way.getNodes().get(way.getNodes().size() - 1));
        });
        TNode start = startList.get(0);
        TNode end = endList.get(0);
        Agent user = new Agent(start.getGeoPosition(), end.getGeoPosition());
        Simulation simulation = new Simulation(sf.getMapViewer());
        List<Agent> agents = AgentPool.getInstance().getAgentList();
        agents.add(user);
        Agent agentPath = agents.get(0);
        Graph graph = Graph.getInstance();
        graph.buildGraph();
        AStar algo = new AStar(graph.getGraph(), agents.get(0));
        algo.findTripNodes();
        List<TNode> path = algo.getPath();
        path.forEach(System.out::println);
        if (path.size() == 0) {
            System.out.println("Empty path");
        }
        sf.getMapViewer().setPath(path);
        //new Thread(simulation::run).start();
        //MapViewerTest.testJX();
    }


}
