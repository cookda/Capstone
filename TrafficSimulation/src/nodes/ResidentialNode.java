package nodes;

import nodes.impl.TrafficNode;
import roads.impl.Connection;

/**
 * Created by aron on 1/25/17.
 */
public class ResidentialNode extends TrafficNode {

    public ResidentialNode(float x, float y) {
        super(x, y);
    }

    public ResidentialNode(float x, float y, Connection... connections) {
        super(x, y, connections);
    }
}
