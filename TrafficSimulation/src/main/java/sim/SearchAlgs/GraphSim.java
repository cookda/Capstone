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
    private HashMap<Long, TNode> nodeMap;
    private static GraphSim instance;
    private ArrayList<TNode> ways;
    private Graph<TNode, DefaultEdge> graph;
    private HashSet<Edge> edges;
    private HashSet<Edge> edges2;

    public static GraphSim getInstance(){
        if(instance == null){
            instance = new GraphSim();
        }
        return instance;
    }

    private GraphSim
            () {
        UserProfile um = UserProfile.getInstance();
        wayMap = um.getWayMap();
        nodeMap = um.getNodeMap();
        edges = new HashSet<>();
        edges2 = new HashSet<>();
        graph = new SimpleGraph<TNode, DefaultEdge>(DefaultEdge.class);
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
                //double distance = getDistance(left, right);
                //Edge e = new Edge(distance, left, right);
                //Edge e2 = new Edge(distance, right, left);
                /*if (!edges.contains(e)){
                    edges.add(e);
                }*/
                graph.addVertex(left);
                graph.addVertex(right);
                graph.addEdge(left, right);
                //left.addEdge(e);
                //right.addEdge(e2);

                /*if (!graph.contains(left)) {
                    graph.add(left);
                }
                if(!graph.contains(right)){
                    graph.add(right);
                }*/
            }
        });
    }

    public static double getDistance(TNode one, TNode two){
        double latStart = one.getLat();
        double lonStart = one.getLon();
        double latEnd = two.getLat();
        double lonEnd = two.getLon();
        return Agent.haverSine(latStart, lonStart, latEnd, lonEnd);
    }

    public Graph<TNode, DefaultEdge> getGraph(){
        return graph;
    }
}
