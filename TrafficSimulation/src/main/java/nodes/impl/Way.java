package nodes.impl;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by gigaw on 2/17/2017.
 */
public class Way implements Serializable {

    private static final long serialVersionUID = 40234L;

    //Retrieved from http://wiki.openstreetmap.org/wiki/Key:highway
    private final static Color motorwayColor = new Color(0xEA90A0);
    private final static Color trunkColor = new Color(0xFBB199);
    private final static Color primaryColor = new Color(0xFED6A1);
    private final static Color secondaryColor = new Color(0xF6FABB);
    private final static Color tertiaryColor = new Color(0xFFFFFF);
    private final static Color unclassifiedColor = tertiaryColor;
    private final static Color residentialColor = tertiaryColor;
    private final static Color serviceColor = tertiaryColor;
    private final static Color noColor = new Color(0x000000); //If we ever see this, we know something is wrong.


    public enum WayType {
        MOTORWAY("motorway", 70, motorwayColor),
        TRUNK("trunk", 65, trunkColor),
        PRIMARY("primary", 55, primaryColor),
        SECONDARY("secondary", 55, secondaryColor),
        TERTIARY("tertiary", 35, tertiaryColor),
        UNCLASSIFIED("unclassified", 35, unclassifiedColor),
        RESIDENTIAL("residential", 25, residentialColor),
        SERVICE("service", 15, serviceColor),
        NONE("none", 0, noColor);

        private String name;
        private int speed;
        private Color color;

        WayType(String name, int speed, Color color) {
            this.name = name;
            this.speed = speed;
        }

        public static Stream<WayType> wayStream() {
            return Arrays.stream(WayType.values());
        }

        public String getName() {
            return name;
        }

        public int getSpeed() {
            return speed;
        }

        public Color getColor() {
            return color;
        }
    }

    private String name;
    private long id = 0;
    private WayType roadType;
    private ArrayList<Long> refs;
    private ArrayList<TNode> nodes = new ArrayList<>();

    public Way(String name, long id, ArrayList<Long> refs, WayType type) {
        this.name = name;
        this.id = id;
        roadType = type;
        this.refs = refs;
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

    public WayType getRoadType() {
        return roadType;
    }

    @Override
    public String toString() {
        return "Way: id=" + id + " - Name: " + name + " - Refs: " + refs.size();
    }
}
