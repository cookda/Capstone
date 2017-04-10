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
    ArrayList<TNode> open = new ArrayList<TNode>(); //both the open and closed are used in get Path (A*)
    ArrayList<TNode> closed = new ArrayList<TNode>();

    public double searchDistance(){return 0.0;}

    public AStar(HashSet<TNode> graph, Agent agent){
        this.graph = graph;
        trip = agent.getTrip();
        findTripNodes();
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
        ArrayList<TNode> path = new ArrayList<TNode>();
        TNode current = start;
        open.add(current);
        path.add(start);
        while(!open.isEmpty()){
            TNode nextNode = determineNode(current);
            open.add(nextNode);
            path.add(nextNode);
            current = nextNode;
            if(nextNode == end){
                break;
            }
        }
        return path;
    }

    public TNode getStart(){
        return start;
    }

    public TNode getEnd(){
        return end;
    }

    private TNode determineNode(TNode current){
        ArrayList<TNode> adj = getAdjacent(current);
        TNode nextNode = null;
        double cost = Double.MAX_VALUE;
        for(int i = 0; i < adj.size(); i++) {
            double newCost = 0.0;
            newCost = Graph.getDistance(current, adj.get(i));
            newCost += Graph.getDistance(adj.get(i), end);
            if (newCost < cost) {
                cost = newCost;
                nextNode = adj.get(i);
            }
        }
        return nextNode;
    }
    private ArrayList<TNode> getAdjacent(TNode start){
        ArrayList<TNode> adj = new ArrayList<TNode>();
        for(Edge e: start.getEdges()){
            adj.add(e.getEnd());
        }
        return adj;
    }

    private double calcDistance(TNode current, TNode nextNode){
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