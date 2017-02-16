package core;

import cache.CacheHandler;

/**
 * Created by aron on 2/10/17.
 */
public class UserProfile {



    private static UserProfile instance;
    private UserMap map;
    private CacheHandler cache;


    protected UserProfile() {
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
