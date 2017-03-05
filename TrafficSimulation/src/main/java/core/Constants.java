package core;

/**
 * Created by aron on 2/16/17.
 */
public class Constants {
    public final static double BOONE_LAT = 36.2168;
    public final static double BOONE_LONG = -81.6746;
    public final static double BOONE_SMALL_LAT = 36.219945;
    public final static double BOONE_SMALL_LON = -81.68615;
    public final static double CHARLOTTE_LAT = 35.220784;
    public final static double CHARLOTTE_LON = -80.840492;

    public final static String[] BAD_WAYS = {"footway", "bridleway", "steps", "path", "pedestrian", "service", "cycleway"};


    public final static String USER_DIR = System.getProperty("user.home") + "/CapstoneData/";
    public final static String IMAGE_DIR = USER_DIR + "img/";
    public final static String DATA_DIR = USER_DIR + "data/";
    public final static String IMG_URL = "http://tyler-demo.herokuapp.com/?";
}
