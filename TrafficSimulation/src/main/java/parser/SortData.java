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

/**
 * Created by gigaw on 2/10/2017.
 */

public class SortData {

    private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    private Document doc;

    public SortData() {
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(Constants.DATA_DIR + UserProfile.getInstance().getMap().getDataName());
            doc.getDocumentElement().normalize();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

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

    public void readWays() {
        NodeList wayNodes = doc.getElementsByTagName("way");
        for (int i = 0; i < wayNodes.getLength(); i++) {
            long id = 0;
            ArrayList<Long> refs = new ArrayList<>();
            String name = "Unnamed";
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
                for (int j = 0; j < wayNode.getChildNodes().getLength(); j++) {
                    NamedNodeMap nodeAttr = wayNode.getChildNodes().item(j).getAttributes();
                    if (nodeAttr != null) {
                        for (int t = 0; t < nodeAttr.getLength(); t++) {
                            Node innerNode = nodeAttr.item(t);
                            String nodeName = innerNode.getNodeName();
                            if (nodeName.equals("ref")) {
                                refs.add(Long.parseLong(innerNode.getNodeValue()));
                            } else if (innerNode.getNodeValue().equals("name")) {
                                name = nodeAttr.item(t + 1).getNodeValue();
                            }
                        }
                    }
                }
                Way newWay = new Way(name, id, refs);
                UserProfile.getInstance().getWays().add(newWay);
                UserProfile.getInstance().getWayMap().put(id, newWay);
            }
        }
    }

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