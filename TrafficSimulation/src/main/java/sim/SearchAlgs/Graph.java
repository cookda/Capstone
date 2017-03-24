package sim.SearchAlgs;

import core.UserProfile;
import nodes.impl.TNode;
import nodes.impl.Way;
import sim.Agent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Dalton on 3/24/2017.
 */
public class Graph {
    private HashMap<Long, Way> wayMap;
    private HashMap<Long, TNode> nodeMap;
    private static Graph instance;
    private ArrayList<TNode> ways;
    private HashSet<GNode> graph;


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

    public void buildGraph(){
        wayMap.values().forEach(way -> {
            ways = way.getNodes();
            for(int i = 0; i < ways.size(); i++){
                TNode left = ways.get(i);
                TNode right = ways.get(i + 1);
                double distance = getDistance(left, right);
                Edge e = new Edge(distance, left, right);
                GNode g = new GNode(left, e);
                if(!graph.contains(g)){
                    graph.add(g);
                }
                else{
                    //need to finish loop for edges
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


}
