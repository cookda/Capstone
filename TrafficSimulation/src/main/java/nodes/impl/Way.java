package nodes.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by gigaw on 2/17/2017.
 */
public class Way {

    public enum WayType {
        MOTORWAY("motorway", 70),
        TRUNK("trunk", 65),
        PRIMARY("primary", 55),
        SECONDARY("secondary", 55),
        TERTIARY("tertiary", 35),
        UNCLASSIFIED("unclassified", 35),
        RESIDENTIAL("residential", 25),
        SERVICE("service", 15);

        private String name;
        private int speed;

        WayType(String name, int speed) {
            this.name = name;
            this.speed = speed;
        }

        public Stream<String> wayNames() {
            return Arrays.stream(WayType.values()).map(WayType::name);
        }

        public String getName() {
            return name;
        }

        public int getSpeed() {
            return speed;
        }
    }

    private String name;
    private long id = 0;
    private WayType roadType;
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
