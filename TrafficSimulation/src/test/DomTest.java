package test;

import ParseData.SortData;
import core.UserProfile;

/**
 * Created by gigaw on 2/16/2017.
 */
public class DomTest {
    public static void printNodes(){
        SortData sd = new SortData();
        sd.readData();
        UserProfile.getInstance().getNodes().forEach(System.out::println);
    }
}
