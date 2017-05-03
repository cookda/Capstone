package sim.SearchAlgs;

import core.UserProfile;
import nodes.impl.TNode;
import nodes.impl.Way;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.AStarAdmissibleHeuristic;
import sim.Agent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import org.jgrapht.graph.*;

/**
 * Created by Dalton on 3/24/2017.
 */
public class GraphSim {
    private HashMap<Long, Way> wayMap;
    private static GraphSim instance;
    private ArrayList<TNode> ways;
    private Graph<TNode, DefaultEdge> graph;

    public static GraphSim getInstance() {
        if (instance == null) {
            instance = new GraphSim();
        }
        return instance;
    }

    private GraphSim() {
        UserProfile um = UserProfile.getInstance();
        wayMap = um.getWayMap();
        graph = new SimpleGraph<>(DefaultEdge.class);
        buildGraph();
    }

    /**
     * Builds the graphs by adding edges to the our T nodes.
     */
    public void buildGraph(){
        wayMap.values().forEach(way -> {
            ways = way.getNodes();
            for (int i = 0; i < ways.size() - 1; i++){
                TNode left = ways.get(i);
                TNode right = ways.get(i + 1);
                graph.addVertex(left);
                graph.addVertex(right);
                graph.addEdge(left, right);
            }
        });
    }


    public Graph<TNode, DefaultEdge> getGraph(){
        return graph;
    }
}
