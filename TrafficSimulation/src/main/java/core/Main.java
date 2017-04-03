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
                        0.020
                )
        );
        up.setCache(new CacheHandler());

        MapTest.basicTest();
        DomTest.getNodes();
        SimFrame sf = new SimFrame();
        AgentGeneratorTests.testRandomGenerator(50);
        Simulation simulation = new Simulation(sf.getMapViewer());
        List<Agent> agents = AgentPool.getInstance().getAgentList();
        Agent agentPath = agents.get(0);
        Graph graph = Graph.getInstance();
        graph.buildGraph();
        AStar algo = new AStar(graph.getGraph(), agents.get(0));
        sf.getMapViewer().setPath(algo.getPath());
        //new Thread(simulation::run).start();
        //MapViewerTest.testJX();
    }


}
