package core;

import cache.CacheHandler;

import java.util.ArrayList;

/**
 * Created by aron on 2/10/17.
 */
public class UserProfile {



    private static UserProfile instance;
    private UserMap map;
    private CacheHandler cache;
    private ArrayList nodes;
    private ArrayList wayNodes;


    public ArrayList getWayNodes(){
        return wayNodes;
    }

    public ArrayList getNodes() {
        return nodes;
    }

    protected UserProfile() {
        nodes = new ArrayList<>();
        wayNodes = new ArrayList<>();
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
}
