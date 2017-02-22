package ParseData;

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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;

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
            int id = 0;
            double lat = 0;
            double lon = 0;
            for (int i = 0; i < curNode.getLength(); i++) {
                String nodeName = curNode.item(i).getNodeName();
                if (nodeName.equals("id")) {
                    id = Integer.parseInt(curNode.item(i).getNodeValue());
                }
                if (nodeName.equals("lat")) {
                    lat = Double.parseDouble(curNode.item(i).getNodeValue());
                }
                if (nodeName.equals("lon")) {
                    lon = Double.parseDouble(curNode.item(i).getNodeValue());
                }
            }
            UserProfile.getInstance().getNodes().add(new TNode(id, lon, lat));
        }
    }

    public void readWays() {
        NodeList wayNodes = doc.getElementsByTagName("way");
        for (int i = 0; i < wayNodes.getLength(); i++) {
            int id = 0;
            ArrayList<Integer> refs = new ArrayList<>();
            Node wayNode = wayNodes.item(i);
            NamedNodeMap curNode = wayNode.getAttributes();

            for (int j = 0; j < curNode.getLength(); j++) {
                String nodeName = curNode.item(i).getNodeName();
                if (nodeName.equals("id")) {
                    id = Integer.parseInt(curNode.item(i).getNodeValue());
                }
                if (nodeName.equals("ref")) {
                    refs.add(Integer.parseInt(curNode.item(i).getNodeValue()));
                }
            }
            UserProfile.getInstance().getWayNodes().add(new Way(id, refs));
        }
    }
}