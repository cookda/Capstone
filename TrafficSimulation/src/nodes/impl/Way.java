package nodes.impl;

import java.util.ArrayList;

/**
 * Created by gigaw on 2/17/2017.
 */
public class Way {
    long id = 0;
    ArrayList<Integer> refs = new ArrayList<>();

    public Way(long id, ArrayList<Integer> refs) {
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

    public ArrayList<Integer> getRefs() {
        return refs;
    }

    public void setRefs(ArrayList<Integer> refs) {
        this.refs = refs;
    }

    @Override
    public String toString() {
        return "Way: id=" + id;
    }
}
