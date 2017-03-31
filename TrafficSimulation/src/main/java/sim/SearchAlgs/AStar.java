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
    public void getPath(){
        LinkedList<TNode> open = new LinkedList<TNode>();
        LinkedList<TNode> closed = new LinkedList<TNode>();
        TNode current = start;
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

                }


            }

        }
        //if (current.gettN().getId() == end.gettN().getId())
            //finished = true;
    }

    public TNode getStart(){
        return start;
    }

    public TNode getEnd(){
        return end;
    }

    private ArrayList<TNode> getAdjacent(TNode start){
        ArrayList<TNode> adj = new ArrayList<TNode>();
        /*for(Edge e: start.getE()){
            adj.add(e.getEnd());
        }*/
        return adj;
    }

    private double determineCost(TNode current, TNode next){
        double cost = 0.0;
        /*for(Edge e : current.getE()){
            if(e.getEnd().getId() == next.getId()){
                cost = e.getDistance();
            }
        }*/
        return cost;
    }
}