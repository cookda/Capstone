package sim.SearchAlgs;

import nodes.impl.TNode;

/**
 * Created by Dalton on 3/24/2017.
 */
public class Edge
{
    private double distance;
    private TNode start;
    private TNode end;

    public Edge(double distance, TNode start, TNode end){
        this.distance = distance;
        this.start = start;
        this.end = end;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public TNode getStart() {
        return start;
    }

    public void setStart(TNode start) {
        this.start = start;
    }

    public TNode getEnd() {
        return end;
    }

    public void setEnd(TNode end) {
        this.end = end;
    }
}
