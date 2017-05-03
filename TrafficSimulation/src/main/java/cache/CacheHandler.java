package cache;

import core.Constants;
import core.UserMap;
import core.UserProfile;

import java.io.*;

/**
 * Created by aron on 2/13/17.
 *
 * Runs off of a file that has cache listings in form (lat, lon, radius, dataFile, imageFile)
 * Data files are in ProjectDir/data, images in ProjectDir/img
 * Values are comma-separated
 *
 */
public class CacheHandler {

    private static final String CACHE_NAME = "cache.dat";

    private UserMap um;

    /**
     * Goes through the cache and checks each line to see if it matches what the entry would be
     * @return true or false based on the above condition
     */
    public boolean isCached() {
        um = UserProfile.getInstance().getMap();
        File f = new File(Constants.USER_DIR + CACHE_NAME);
        if (!f.exists()) {
            try {
                if (!f.createNewFile()) {
                    return false;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (Double.parseDouble(data[0]) == um.getLatitude() && Double.parseDouble(data[1]) == um.getLongitude()
                        && Double.parseDouble(data[2]) == um.getRadius()) {
                    System.out.println(Double.parseDouble(data[0]));
                    System.out.println(um.getLatitude());
                    return true;
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    /**
     * Caches the area we are currently using
     */
    public void cacheArea() {
        File f = new File(Constants.USER_DIR + CACHE_NAME);
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f, true)));
            pw.append(buildCacheEntry());
            pw.append('\n');
            pw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns a name for data/img as lat/lon
     * @return built string of cache entry
     */
    private String buildCacheEntry() {
        return um.getLatitude() + "," + um.getLongitude() + "," + um.getRadius() + "," + um.getDataName() + "," + um.getImageName();
    }

}
