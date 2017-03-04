package nodes.impl;

import java.util.ArrayList;

/**
 * Created by gigaw on 2/17/2017.
 */
public class Way {

    private String name;
    long id = 0;
    ArrayList<Long> refs = new ArrayList<>();
    ArrayList<TNode> nodes = new ArrayList<>();

    public Way(String name, long id, ArrayList<Long> refs) {
        this.name = name;
        this.id = id;
        for (int i = 0; i < refs.size(); i++) {
            this.refs.add(refs.get(i));
        }
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Long> getRefs() {
        return refs;
    }

    public void setRefs(ArrayList<Long> refs) {
        this.refs = refs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<TNode> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<TNode> nodes) {
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        return "Way: id=" + id + " - Name: " + name + " - Refs: " + refs.size();
    }
}
