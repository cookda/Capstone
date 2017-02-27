package core;

import cache.CacheHandler;
import nodes.impl.TNode;
import nodes.impl.Way;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by aron on 2/10/17.
 */
public class UserProfile {



    private static UserProfile instance;
    private UserMap map;
    private CacheHandler cache;
    private ArrayList<TNode> nodes;
    private ArrayList<Way> ways;

    private HashMap<Long, Way> wayMap;
    private HashMap<Long, TNode> nodeMap;


    public ArrayList<Way> getWays(){
        return ways;
    }

    public ArrayList<TNode> getNodes() {
        return nodes;
    }

    private UserProfile() {
        nodes = new ArrayList<>();
        ways = new ArrayList<>();
        wayMap = new HashMap<>();
        nodeMap = new HashMap<>();
    }

    public static UserProfile getInstance() {
        if (instance == null) {
            instance = new UserProfile();
        }
        return instance;
    }

    public UserMap getMap() {
        return map;
    }

    public void setMap(UserMap map) {
        this.map = map;
    }

    public CacheHandler getCache() {
        return cache;
    }

    public void setCache(CacheHandler ch) {
        cache = ch;
    }

    public HashMap<Long, Way> getWayMap() {
        return wayMap;
    }

    public HashMap<Long, TNode> getNodeMap() {
        return nodeMap;
    }
}
