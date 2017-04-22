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
    Graph<TNode, DefaultEdge> graph;
    ArrayList<GNode> path;
    Pair<GeoPosition, GeoPosition> trip;
    TNode start;
    TNode end;
    ArrayList<TNode> open = new ArrayList<TNode>(); //both the open and closed are used in get Path (A*)
    ArrayList<TNode> closed = new ArrayList<TNode>();
    private List<TNode> adj;
    private TNode nextNode;

    public double searchDistance(){return 0.0;}

    public AStar(Agent agent){
        graph = GraphSim.getInstance().getGraph();
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
        Set<TNode> vertices = graph.vertexSet(); //graph size is 0 not good
        System.out.println("Size of graph " + vertices.size());
        TNode[] nodes = vertices.toArray(new TNode[vertices.size()]);
        for(TNode vert : nodes){
            System.out.println(vert.getId());
            if(vert.getLat() == latStart && vert.getLon() == lonStart)
                System.out.println("Found Start");
                start = vert;
            if(vert.getLon() == latEnd && vert.getLon() == lonEnd)
                System.out.println("Found End");
                end = vert;
        }
        /*Optional<TNode> nodeOptional = graph.stream().filter(node ->
            node.getLat() == latStart && node.getLon() == lonStart
        ).findFirst();
        Optional<TNode> nodeOptional2 = graph.stream().filter(node ->
            node.getLat() == latEnd && node.getLon() == lonEnd
        ).findFirst();
        start = nodeOptional.orElse(null);
        end = nodeOptional2.orElse(null);*/
    }

    /**
     * Determines the best path to take by using the start node as the first node to visit
     * then adding that to the closed linkedlist.  We then iterate over all the nodes connected
     * to the current node and take the best one according to distance to the goal node.
     */
    public TNode[] getPath(){
        AStarAdmissibleHeuristic<TNode> heuristic = new AStarAdmissibleHeuristic<TNode>() {
            @Override
            public double getCostEstimate(TNode tNode, TNode v1) {
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
            }
        };



        AStarShortestPath path = new AStarShortestPath(graph, heuristic);
        System.out.println(start.getId());
        System.out.println(end.getId());
        for(TNode node : graph.vertexSet()){
            System.out.println(node.getId());
        }
        GraphPath<TNode, DefaultEdge> answer = path.getPath(start, end);
        List<TNode> hopeIsNotLost = answer.getVertexList();
        TNode[] pathAnswer = (TNode[])hopeIsNotLost.toArray();
        return pathAnswer;
        /*TNode current = start;
        open.add(current);
        path.add(start);
        while(!open.isEmpty()){
            TNode nextNode = determineNode(current);

            path.add(nextNode);
            //System.out.print(nextNode.getLat() + " " + nextNode.getLon() + "\n");
            System.out.println("This is the the end goal:" + end.getLat() + " " + end.getLon() + "\n");
            current = nextNode;
            System.out.println(current.getLat() + " " + current.getLon());
            if(current.equals(end)){
                break;
            }
        }
        return path;*/
    }

    public TNode getStart(){
        return start;
    }

    public TNode getEnd(){
        return end;
    }

    /*private TNode determineNode(TNode current){
        adj = getAdjacent(current);
        nextNode = null;
        double cost = Double.MAX_VALUE;
        for (int i = 0; i < adj.size(); i++) {
            double newCost = 0.0;
            newCost = Graph.getDistance(current, adj.get(i));
            newCost += Graph.getDistance(adj.get(i), end);
            if (newCost < cost && !closed.contains(adj.get(i))) {
                cost = newCost;
                nextNode = adj.get(i);
                open.remove(adj.get(i));
                closed.add(adj.get(i));
            } else if (i == adj.size() - 1 && closed.contains(adj.get(i))) {
                for (int j = 0 ; j < open.size(); j++){
                    if(open.get(i) != null){
                        current = open.get(i);
                        break;
                    }
                }
                break;

            } else {
                open.remove(adj.get(i));
                closed.add(adj.get(i));
            }
        }
        return nextNode;
    }
    private ArrayList<TNode> getAdjacent(TNode start){
        ArrayList<TNode> adj = new ArrayList<TNode>();
        for(Edge e: start.getEdges()){
            adj.add(e.getEnd());
            open.add(e.getEnd());  //recently added
        }
        return adj;
    }*/
}