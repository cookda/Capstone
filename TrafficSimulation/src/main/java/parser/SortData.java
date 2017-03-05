package parser;

import core.Constants;
import core.UserProfile;
import nodes.impl.TNode;
import nodes.impl.Way;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

/**
 * Created by gigaw on 2/10/2017.
 */

public class SortData {


    private Document doc;

    /**
     * The constructor of this class initializes our parsing objects
     */
    public SortData() {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = builder.parse(Constants.DATA_DIR + UserProfile.getInstance().getMap().getDataName());
            doc.getDocumentElement().normalize();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * This method will read all of the XML nodes in order to parse out OSM nodes
     * All XML nodes with the tag "node" will be collected and their attributes iterated over
     * The id, latitude, and longitude are collected and a new TNode is constructed
     * This TNode is then put into our Map
     */
    public void readNodes() {
        NodeList nodes = doc.getElementsByTagName("node");
        for (int temp = 0; temp < nodes.getLength(); temp++) {
            Node nNode = nodes.item(temp);
            NamedNodeMap curNode = nNode.getAttributes();
            long id = 0;
            double lat = 0;
            double lon = 0;
            for (int i = 0; i < curNode.getLength(); i++) {
                String nodeName = curNode.item(i).getNodeName();
                if (nodeName.equals("id")) {
                    id = Long.parseLong(curNode.item(i).getNodeValue());
                }
                if (nodeName.equals("lat")) {
                    lat = Double.parseDouble(curNode.item(i).getNodeValue());
                }
                if (nodeName.equals("lon")) {
                    lon = Double.parseDouble(curNode.item(i).getNodeValue());
                }
            }
            TNode newNode = new TNode(id, lon, lat);
            UserProfile.getInstance().getNodes().add(newNode);
            UserProfile.getInstance().getNodeMap().put(id, newNode);
        }
    }

    /**
     * This method will parse through the xml nodes in the document marked with "way"
     * Those nodes are collected and put into a NodeList, then iterated over
     * In each iteration, we assign a new "refs" List in order to store the referenced nodes
     * A name is initialized to unnamed and then set to the name of the way if one is present
     * A way type is determined based off of the "highway" tag within the node in order to set speed limits for calculation during the simulation
     *
     * If the way is a road, we iterate over each of the attributes and pull out the id, name, references, and road type
     * This way is then added to the Map containing all ways
     */
    public void readWays() {
        NodeList wayNodes = doc.getElementsByTagName("way");
        for (int i = 0; i < wayNodes.getLength(); i++) {
            long id = 0;
            ArrayList<Long> refs = new ArrayList<>();
            String name = "Unnamed";
            Way.WayType type = Way.WayType.NONE;
            Node wayNode = wayNodes.item(i);
            NamedNodeMap curNode = wayNode.getAttributes();

            if (isRoad(wayNode.getChildNodes())) {
                for (int j = 0; j < curNode.getLength(); j++) {
                    Node innerNode = curNode.item(j);
                    String nodeName = innerNode.getNodeName();
                    if (nodeName.equals("id")) {
                        id = Long.parseLong(innerNode.getNodeValue());
                    }
                }


                ArrayList<TNode> nodesForWay = new ArrayList<>();
                for (int j = 0; j < wayNode.getChildNodes().getLength(); j++) {
                    NamedNodeMap nodeAttr = wayNode.getChildNodes().item(j).getAttributes();

                    if (nodeAttr != null) {
                        for (int t = 0; t < nodeAttr.getLength(); t++) {
                            Node innerNode = nodeAttr.item(t);
                            String nodeName = innerNode.getNodeName();

                            if (nodeName.equals("ref")) {
                                refs.add(Long.parseLong(innerNode.getNodeValue()));
                                nodesForWay.add(UserProfile.getInstance().getNodeMap().get(Long.parseLong(innerNode.getNodeValue())));
                            } else if (innerNode.getNodeValue().equals("name")) {
                                name = nodeAttr.item(t + 1).getNodeValue();
                            } else if (innerNode.getNodeValue().equals("highway")) {
                                String wayTypeName = nodeAttr.item(t + 1).getNodeValue();
                                Optional<Way.WayType> wayTypeOptional;
                                wayTypeOptional = Way.WayType.wayStream().filter(f -> f.getName().equals(wayTypeName)).findAny();
                                type = wayTypeOptional.orElse(Way.WayType.NONE);
                            }
                        }
                    }
                }

                Way newWay = new Way(name, id, refs, type);
                newWay.setNodes(nodesForWay);
                UserProfile.getInstance().getWays().add(newWay);
                UserProfile.getInstance().getWayMap().put(id, newWay);
            }
        }
    }

    /**
     * This method will parse a way node and determine whether or not it contains a highway attribute
     * If it does, and the value of that attribute represents a road, return true
     *
     * @param nodeList - The list of child nodes (attributes) in the way node
     * @return whether or not we have determined it is a road
     */
    private boolean isRoad(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            NamedNodeMap attr = nodeList.item(i).getAttributes();
            if (attr != null) {
                for (int j = 0; j < attr.getLength(); j++) {
                    Node innerNode = attr.item(j);
                    if (innerNode.getNodeName().equals("k") && innerNode.getNodeValue().equals("highway")
                            && !Arrays.asList(Constants.BAD_WAYS).contains(attr.item(j + 1).getNodeValue())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}