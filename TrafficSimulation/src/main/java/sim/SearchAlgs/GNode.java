package sim.SearchAlgs;

import nodes.impl.TNode;

import java.util.HashSet;

/**
 * Created by Dalton on 3/24/2017.
 */
public class GNode {
    private TNode tN;
    private HashSet<Edge> e;

    public GNode(TNode tN, HashSet<Edge> e){
        this.tN = tN;
        this.e = e;
    }

    public TNode gettN() {
        return tN;
    }

    public void settN(TNode tN) {
        this.tN = tN;
    }

    public HashSet<Edge> getE() {
        return e;
    }

    public void setE(HashSet<Edge> e) {this.e = e;
    }
}
