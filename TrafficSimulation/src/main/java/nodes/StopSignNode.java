package nodes;

import nodes.impl.TrafficNode;
import roads.impl.Connection;

import java.util.Arrays;

/**
 * Created by aron on 1/25/17.
 */
public class StopSignNode extends TrafficNode {


    public StopSignNode(float x, float y) {
        super(x, y);
    }

    public StopSignNode(float x, float y, Connection... connections) {
        super(x, y, connections);
    }
}
