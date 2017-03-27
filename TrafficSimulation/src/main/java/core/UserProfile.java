package core;

import cache.CacheHandler;
import nodes.impl.TNode;
import nodes.impl.Way;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by aron on 2/10/17.
 */
public class UserProfile implements Serializable {


    private static final long serialVersionUID = 40234L;

    private transient static UserProfile instance;
    private transient CacheHandler cache;
    private UserMap map;

    private HashMap<Long, Way> wayMap;
    private HashMap<Long, TNode> nodeMap;


    private UserProfile() {
        wayMap = new HashMap<>();
        nodeMap = new HashMap<>();
    }

    /**
     * Global access point for UserProfile singleton
     * @return instance of UserProfile
     */
    public static UserProfile getInstance() {
        if (instance == null) {
            instance = new UserProfile();
        }
        return instance;
    }

    public static void setInstance(UserProfile instance) {
        UserProfile.instance = instance;
    }

    /**
     * @return - the UserMap currently being used which contains information about the current OSM area we are operating on
     */
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
