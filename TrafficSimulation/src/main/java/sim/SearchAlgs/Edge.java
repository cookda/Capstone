package sim.SearchAlgs;

import nodes.impl.TNode;

/**
 * Created by Dalton on 3/24/2017.
 */
public class Edge
{
    private double distance;
    private TNode left;
    private TNode right;

    public Edge(double distance, TNode left, TNode right){
        this.distance = distance;
        this.left = left;
        this.right = right;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public TNode getLeft() {
        return left;
    }

    public void setLeft(TNode left) {
        this.left = left;
    }

    public TNode getRight() {
        return right;
    }

    public void setRight(TNode right) {
        this.right = right;
    }
}
