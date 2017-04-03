package sim.SearchAlgs;


import core.UserProfile;
import javafx.util.Pair;
import nodes.impl.TNode;
import org.jxmapviewer.viewer.GeoPosition;
import sim.Agent;

import java.util.*;

/**
 * Created by Dalton on 3/20/2017.
 * http://mat.uab.cat/~alseda/MasterOpt/AStar-Algorithm.pdf : PsuedoCode
 */
public class AStar extends SearchAlg{
    HashSet<TNode> graph;
    ArrayList<GNode> path;
    Pair<GeoPosition, GeoPosition> trip;
    TNode start;
    TNode end;

    public double searchDistance(){return 0.0;}

    public AStar(HashSet<TNode> graph, Agent agent){
        this.graph = graph;
        trip = agent.getTrip();
    }

    public void findTripNodes(){
        GeoPosition startNode = trip.getKey();
        GeoPosition endNode = trip.getValue();
        double latStart = startNode.getLatitude();
        double lonStart = startNode.getLongitude();
        double latEnd = endNode.getLatitude();
        double lonEnd = endNode.getLongitude();
        Optional<TNode> nodeOptional = graph.stream().filter(node ->
            node.getLat() == latStart && node.getLon() == lonStart
        ).findFirst();
        Optional<TNode> nodeOptional2 = graph.stream().filter(node ->
            node.getLat() == latEnd && node.getLon() == lonEnd
        ).findFirst();
        start = nodeOptional.orElse(null);
        end = nodeOptional2.orElse(null);
    }

    /**
     * Determines the best path to take by using the start node as the first node to visit
     * then adding that to the closed linkedlist.  We then iterate over all the nodes connected
     * to the current node and take the best one according to distance to the goal node.
     */
    public ArrayList<TNode> getPath(){
        LinkedList<TNode> open = new LinkedList<TNode>();
        LinkedList<TNode> closed = new LinkedList<TNode>();
        ArrayList<TNode> path = new ArrayList<TNode>();
        TNode current = start;
        TNode bestNode = start;
        boolean finished = false;
        double bestDistance = Double.POSITIVE_INFINITY;
        open.add(start);
        ArrayList<TNode> adjacent = getAdjacent(start);
        while (!finished){
            for (int i = 0; i < adjacent.size(); i++) {
                double cost = determineCost(current, adjacent.get(i));
                cost += Agent.haverSine(current.getLat(), current.getLon(), end.getLat(), end.getLon());
                if (cost < bestDistance) {
                    bestDistance = cost;
                    bestNode = adjacent.get(i);
                }
            }
            current = bestNode;
            path.add(bestNode);
            if (current.getLat() == end.getLat() && current.getLon() == end.getLon()){
                finished = true;
            }
        }
    }

    public TNode getStart(){
        return start;
    }

    public TNode getEnd(){
        return end;
    }

    private ArrayList<TNode> getAdjacent(TNode start){
        ArrayList<TNode> adj = new ArrayList<TNode>();
        for(Edge e: start.getEdges()){
            adj.add(e.getEnd());
        }
        return adj;
    }

    private double determineCost(TNode current, TNode nextNode){
        for(Edge e: current.getEdges()){
            double latCur = e.getEnd().getLat();
            double lonCur = e.getEnd().getLon();
            double nextNodeLat = nextNode.getLat();
            double nextNodeLon = nextNode.getLon();
            if(latCur == nextNodeLat && lonCur == nextNodeLon){
                return e.getDistance();
            }
        }
        return Double.MAX_VALUE;
    }
}