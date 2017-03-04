package gui;

import core.Constants;
import core.UserProfile;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Wayne on 3/4/2017.
 */
public class MapViewer {

    private JXMapViewer mapViewer;
    private TileFactoryInfo tileFactoryInfo;
    private VirtualEarthTileFactoryInfo.MVEMode tileFactoryType = VirtualEarthTileFactoryInfo.MAP;
    private DefaultTileFactory tileFactory;

    public MapViewer() {

        mapViewer = new JXMapViewer();
        tileFactoryInfo = new VirtualEarthTileFactoryInfo(tileFactoryType);
        tileFactory = new DefaultTileFactory(tileFactoryInfo);

        mapViewer.setTileFactory(tileFactory);
        tileFactory.setThreadPoolSize(4);

        File cacheDir = new File(System.getProperty("user.home") + File.separator + ".jxmapmapviewer2");
        LocalResponseCache.installResponseCache(tileFactoryInfo.getBaseURL(), cacheDir, false);


        GeoPosition gp = new GeoPosition(Constants.BOONE_LAT, Constants.BOONE_LONG);

        mapViewer.setZoom(15);
        mapViewer.setAddressLocation(gp);

        MouseInputListener mia = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);

        mapViewer.addMouseListener(new CenterMapListener(mapViewer));

        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCursor(mapViewer));

        mapViewer.addKeyListener(new PanKeyListener(mapViewer));

        Set<MyWaypoint> pointSet = new HashSet<MyWaypoint>();


        UserProfile.getInstance().getWayMap().values().forEach(way -> {
            ArrayList<TNode> wayNodes = way.getNodes();
            for (int i = 0; i < way.getNodes().size(); i++) {
                GeoPosition nodePos = new GeoPosition(wayNodes.get(i).getLat(), wayNodes.get(i).getLon());
                pointSet.add(new MyWaypoint("A", Color.WHITE, nodePos));
            }
        });

        WaypointPainter<MyWaypoint> waypointPainter = new WaypointPainter<MyWaypoint>();
        waypointPainter.setWaypoints(pointSet);
        waypointPainter.setRenderer(new FancyWaypointRenderer());

        mapViewer.setOverlayPainter(waypointPainter);

        JFrame frame = new JFrame("Viewer");
        frame.getContentPane().add(mapViewer);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
