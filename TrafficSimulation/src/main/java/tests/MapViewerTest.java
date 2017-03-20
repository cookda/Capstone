package tests;

import api.OSMGrabber;
import cache.CacheHandler;
import core.Constants;
import core.UserProfile;
import gui.MapViewer;
import gui.jxmapviewer.FancyWaypointRenderer;
import gui.jxmapviewer.MyWaypoint;
import nodes.impl.TNode;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.input.CenterMapListener;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.viewer.*;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import parser.SortData;
import core.UserProfile;

/**
 * Created by aron on 2/17/17.
 */
public class MapViewerTest {


    public static void testJX() {
        new MapViewer();
    }

    public static void printNodes(){
        SortData sd = new SortData();
        sd.readNodes();
        sd.readWays();
        //UserProfile.getInstance().getNodes().forEach(System.out::println);
        //UserProfile.getInstance().getWays().forEach(System.out::println);
    }

    public static void getNodes() {
        SortData sd = new SortData();
        sd.readNodes();
        sd.readWays();
    }

    public static void basicTest() {

        OSMGrabber test = new OSMGrabber();
        CacheHandler ch = UserProfile.getInstance().getCache();
        if (!ch.isCached()) {
            test.getArea();
            test.getImage();
            ch.cacheArea();
        } else {
            System.out.println("Area cached");
        }
    }

}
