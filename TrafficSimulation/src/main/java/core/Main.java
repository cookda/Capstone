package core;

import api.OSMGrabber;
import cache.CacheHandler;
import gui.MapViewer;
import gui.SimFrame;
import nodes.impl.TNode;
import parser.SortData;
import sim.Agent;
import sim.AgentGenerator;
import sim.AgentPool;
import sim.SearchAlgs.AStar;
import sim.Simulation;
import tests.AgentGeneratorTests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by aron on 3/20/17.
 */
public class Main {

    private static MapViewer mv;

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

        acquireMap();
        parse();
        initGUI();
        generateAgents(100, 0);
        getPaths();

    }

    /**
     * Generate some amount of agents with generator 0 or 1.
     * @param amount - Agents to generate
     * @param gen - Generator to use
     */
    private static void generateAgents(int amount, int gen) {
        AgentGenerator generator = new AgentGenerator();
        if (gen == 0) {
            generator.randomAgentGenerator(amount); //Unstable
        } else {
            generator.randomAgentGenerator1(amount);
        }
    }

    /**
     * Acquire the paths for the agents and set them in the graphical representation
     */
    private static void getPaths() {
        List<Agent> agents = AgentPool.getInstance().getAgentList();

        agents.forEach(agent -> {
            AStar algo = new AStar(agent);
            algo.findTripNodes();
            agent.setPath(Arrays.asList(algo.getPath()));
        });

        List<List<TNode>> bigOlList = new ArrayList<>();
        agents.forEach(agent -> bigOlList.add(agent.getPath()));

        mv.setPath(bigOlList);
    }

    /**
     * Initialize the graphical portions and the simulation
     */
    private static void initGUI() {
        mv = new MapViewer();
        new SimFrame(new Simulation(mv), mv);
    }

    /**
     * Acquire the map data and cache it
     */
    private static void acquireMap() {
        OSMGrabber test = new OSMGrabber();
        CacheHandler ch = UserProfile.getInstance().getCache();
        if (!ch.isCached()) {
            test.getArea();
            ch.cacheArea();
        } else {
            System.out.println("Area cached");
        }
    }

    /**
     * Parse the map data
     */
    private static void parse() {
        SortData sd = new SortData();
        sd.readNodes();
        sd.readWays();
    }


}
