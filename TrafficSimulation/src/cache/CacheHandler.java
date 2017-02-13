package cache;

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

    private double currentLat;
    private double currentLong;
    private double currentRadius;


    public CacheHandler(double lat, double lon, double radius) {
        currentLat = lat;
        currentLong = lon;
        currentRadius = radius;
    }

    public boolean isCached() {
        File f = new File(UserProfile.USER_DIR + CACHE_NAME);
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
                if (Double.parseDouble(data[0]) == currentLat && Double.parseDouble(data[1]) == currentLong
                        && Double.parseDouble(data[2]) == currentRadius) {
                    return true;
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    public void cacheArea() {
        File f = new File(UserProfile.USER_DIR + CACHE_NAME);
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
            pw.write(buildCacheEntry());
            pw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadCache() {

    }

    /**
     * Currently naively returns a name for data/img as lat/lon
     * @return built string of cache entry
     */
    private String buildCacheEntry() {
        StringBuilder sb = new StringBuilder();
        sb.append(currentLat);
        sb.append(",");
        sb.append(currentLong);
        sb.append(",");
        sb.append(currentRadius);
        sb.append(",");
        sb.append(currentLat + "_" + currentLong + ".dat");
        sb.append(",");
        sb.append(currentLat + "_" + currentLong + ".png");
        return sb.toString();
    }

}
