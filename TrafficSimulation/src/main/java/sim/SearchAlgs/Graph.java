package sim.SearchAlgs;

import core.UserProfile;
import nodes.impl.TNode;
import nodes.impl.Way;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Dalton on 3/24/2017.
 */
public class Graph {
    private HashMap<Long, Way> wayMap;
    private HashMap<Long, TNode> nodeMap;
    private static Graph instance;
    private ArrayList<TNode> ways;


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

            }
        });
    }


}
