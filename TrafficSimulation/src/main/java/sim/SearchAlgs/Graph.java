package sim.SearchAlgs;

import core.UserProfile;
import nodes.impl.TNode;
import nodes.impl.Way;
import sim.Agent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

/**
 * Created by Dalton on 3/24/2017.
 */
public class Graph {
    private HashMap<Long, Way> wayMap;
    private HashMap<Long, TNode> nodeMap;
    private static Graph instance;
    private ArrayList<TNode> ways;
    private HashSet<TNode> graph;
    private HashSet<Edge> edges;


    public static Graph getInstance(){
        if(instance == null){
            instance = new Graph();
        }
        return instance;
    }

    private Graph() {
        UserProfile um = UserProfile.getInstance();
        wayMap = um.getWayMap();
        nodeMap = um.getNodeMap();
    }

    /**
     * Builds the graphs by adding edges to the our T nodes.
     */
    public void buildGraph(){
        wayMap.values().forEach(way -> {
            ways = way.getNodes();
            for (int i = 0; i < ways.size(); i++){
                TNode left = ways.get(i);
                TNode right = ways.get(i + 1);
                double distance = getDistance(left, right);
                Edge e = new Edge(distance, left, right);
                if (!edges.contains(e)){
                    edges.add(e);
                }
                left.addEdge(e);
                right.addEdge(e);

                if (!graph.contains(left)) {
                    graph.add(left);
                }
                if(!graph.contains(right)){
                    graph.add(right);
                }
            }
        });
    }

    public double getDistance(TNode one, TNode two){
        double latStart = one.getLat();
        double lonStart = one.getLon();
        double latEnd = two.getLat();
        double lonEnd = two.getLon();
        return Agent.haverSine(latStart, lonStart, latEnd, lonEnd);
    }

    public HashSet<TNode> getGraph(){
        return graph;
    }
}
