package nodes.impl;

import roads.TwoLaneRoad;
import roads.impl.Connection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by aron on 1/25/17.
 */
public abstract class TrafficNode {
    private List<Connection> connections;
    private float x;
    private float y;


    public TrafficNode(float x, float y) {
        this(x, y, new TwoLaneRoad());
    }

    public TrafficNode(float x, float y, Connection... connections) {
        setX(x);
        setY(y);
        setConnections(Arrays.asList(connections));
    }


    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
