package sim.SearchAlgs;


import core.UserProfile;
import javafx.util.Pair;
import nodes.impl.TNode;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.AStarAdmissibleHeuristic;
import org.jgrapht.graph.DefaultEdge;
import org.jxmapviewer.viewer.GeoPosition;
import org.jgrapht.alg.shortestpath.AStarShortestPath;
import sim.Agent;


import java.util.*;

/**
 * Created by Dalton on 3/20/2017.
 * http://mat.uab.cat/~alseda/MasterOpt/AStar-Algorithm.pdf : PsuedoCode
 */
public class AStar extends SearchAlg{
    private Graph<TNode, DefaultEdge> graph;
    private Pair<GeoPosition, GeoPosition> trip;
    private TNode start;
    private TNode end;


    public AStar(Agent agent){
        graph = GraphSim.getInstance().getGraph();
        trip = agent.getTrip();
        findTripNodes();
    }

    public void findTripNodes(){
        Set<TNode> vertices = graph.vertexSet(); //graph size is 0 not good
        for (TNode node : vertices) {
            if (node.getGeoPosition().equals(trip.getKey())) {
                start = node;
            } else if (node.getGeoPosition().equals(trip.getValue())) {
                end = node;
            }
        }
    }

    /**
     * Determines the best path to take by using the start node as the first node to visit
     * then adding that to the closed linkedlist.  We then iterate over all the nodes connected
     * to the current node and take the best one according to distance to the goal node.
     */
    public TNode[] getPath(){
        AStarShortestPath<TNode, DefaultEdge> path = new AStarShortestPath<>(graph, ((tNode, v1) -> {
                double R = 6372.8;
                double lonEnd = v1.getLon();
                double lonStart = tNode.getLon();
                double latEnd = v1.getLat();
                double latStart = tNode.getLat();
                double disLon = Math.toRadians(lonEnd - lonStart);
                double disLat = Math.toRadians(latEnd - latStart);
                latStart = Math.toRadians(latStart);
                latEnd = Math.toRadians(latEnd);
                double a = Math.pow(Math.sin(disLat/2),2) + Math.pow(Math.sin(disLon / 2),2) * Math.cos(latStart) * Math.cos(latEnd);
                double c = 2 * Math.asin(Math.sqrt(a));
                return R * c;
            })
        );

        GraphPath<TNode, DefaultEdge> answer = path.getPath(start, end);
        if (answer != null) {
            List<TNode> hopeIsNotLost = answer.getVertexList();
            return hopeIsNotLost.toArray(new TNode[hopeIsNotLost.size()]);
        }
        return new TNode[] {};
    }
}