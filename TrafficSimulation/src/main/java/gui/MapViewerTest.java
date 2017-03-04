package gui;

import api.OSMGrabber;
import cache.CacheHandler;
import core.Constants;
import core.UserProfile;
import gui.MainFrame;
import gui.jxmapviewer.FancyWaypointRenderer;
import gui.jxmapviewer.MyWaypoint;
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
import java.util.HashSet;
import java.util.Set;

import parser.SortData;
import core.UserProfile;

/**
 * Created by aron on 2/17/17.
 */
public class MapViewerTest {

    public static void testGUI() {
        try {
            MainFrame mf = new MainFrame();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void testJX() {
        JXMapViewer viewer = new JXMapViewer();
        TileFactoryInfo info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP);
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);

        viewer.setTileFactory(tileFactory);
        tileFactory.setThreadPoolSize(4);

        File cacheDir = new File(System.getProperty("user.home") + File.separator + ".jxmapviewer2");
        LocalResponseCache.installResponseCache(info.getBaseURL(), cacheDir, false);


        GeoPosition gp = new GeoPosition(Constants.BOONE_LAT, Constants.BOONE_LONG);

        viewer.setZoom(7);
        viewer.setAddressLocation(gp);

        MouseInputListener mia = new PanMouseInputListener(viewer);
        viewer.addMouseListener(mia);
        viewer.addMouseMotionListener(mia);

        viewer.addMouseListener(new CenterMapListener(viewer));

        viewer.addMouseWheelListener(new ZoomMouseWheelListenerCursor(viewer));

        viewer.addKeyListener(new PanKeyListener(viewer));

        Set<MyWaypoint> pointSet = new HashSet<MyWaypoint>();

        UserProfile.getInstance().getWayMap().values().forEach(f -> {
            UserProfile.getInstance().getNodeMap().forEach((id, node) -> {
                f.getRefs().forEach(ref -> {
                    if (ref.equals(id)) {
                        pointSet.add(new MyWaypoint("A", Color.WHITE, new GeoPosition(node.getLat(), node.getLon())));
                    }
                });
            });
            //pointSet.add(new MyWaypoint("A", Color.WHITE, new GeoPosition(f.getLat(), f.getLon())));
        });

        WaypointPainter<MyWaypoint> waypointPainter = new WaypointPainter<MyWaypoint>();
        waypointPainter.setWaypoints(pointSet);
        waypointPainter.setRenderer(new FancyWaypointRenderer());

        viewer.setOverlayPainter(waypointPainter);

        JFrame frame = new JFrame("Viewer");
        frame.getContentPane().add(viewer);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public static void printNodes(){
        SortData sd = new SortData();
        sd.readNodes();
        sd.readWays();
        UserProfile.getInstance().getNodes().forEach(System.out::println);
        UserProfile.getInstance().getWays().forEach(System.out::println);
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

    public static void guiTest() throws IOException {
        MainFrame mf = new MainFrame();
    }

}
