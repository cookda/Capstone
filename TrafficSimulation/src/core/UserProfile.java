package core;

import nodes.impl.Node;

import java.util.List;

/**
 * Created by aron on 2/10/17.
 */
public class UserProfile {

    private List<Node> buildings;
    private List<Node> roads;
    private List<Node> intersections;

    public final static String USER_DIR = System.getProperty("user.home") + "/CapstoneData/";
    public final static String IMAGE_DIR = USER_DIR + "img/";
    public final static String DATA_DIR = USER_DIR + "data/";
    public final static String IMG_URL = "http://tyler-demo.herokuapp.com/?";
}
