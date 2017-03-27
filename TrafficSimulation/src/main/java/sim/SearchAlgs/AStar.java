package sim.SearchAlgs;


import core.UserProfile;
import javafx.util.Pair;
import nodes.impl.TNode;
import org.jxmapviewer.viewer.GeoPosition;
import sim.Agent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;

/**
 * Created by Dalton on 3/20/2017.
 * http://mat.uab.cat/~alseda/MasterOpt/AStar-Algorithm.pdf : PsuedoCode
 */
public class AStar extends SearchAlg{
    HashSet<GNode> graph;
    ArrayList<GNode> path;
    Pair<GeoPosition, GeoPosition> trip;
    GNode start;
    GNode end;

    public double searchDistance(){return 0.0;}

    public AStar(HashSet<GNode> graph, Agent agent){
        this.graph = graph;
        trip = agent.getTrip();
    }

    public void findTripNodes(){
        GeoPosition startNode =  trip.getKey();
        GeoPosition endNode = trip.getValue();
        double latStart = startNode.getLatitude();
        double lonStart = startNode.getLongitude();
        double latEnd = endNode.getLatitude();
        double lonEnd = endNode.getLongitude();
        Optional<GNode> nodeOptional = graph.stream().filter(node ->
            node.gettN().getLat() == latStart && node.gettN().getLon() == lonStart
        ).findFirst();
        Optional<GNode> nodeOptional2 = graph.stream().filter(node ->
            node.gettN().getLat() == latEnd && node.gettN().getLon() == lonEnd
        ).findFirst();
        start = nodeOptional.orElse(null);
        end = nodeOptional2.orElse(null);
    }

    public void getPath(){
        LinkedList<GNode> open = new LinkedList<GNode>();
        LinkedList<GNode> closed = new LinkedList<GNode>();
        GNode current;
        boolean finished = false;
        Edge bestEdge;
        double distance = 0;
        open.add(start);
        while(!finished){
            for(Edge e : start.getE()){
                TNode endNode = e.getEnd();
                distance = Agent.haverSine(endNode.getLat(), endNode.getLon(), end.gettN().getLat(), end.gettN().getLon());

            }
        }

    }

    public GNode getStart(){
        return start;
    }

    public GNode getEnd(){
        return end;
    }


}
