package gui;

import core.Constants;
import core.UserProfile;
import gui.jxmapviewer.*;
import javafx.util.Pair;
import nodes.impl.TNode;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.input.CenterMapListener;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.viewer.*;
import sim.Agent;
import sim.AgentPool;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

/**
 * Created by Wayne on 3/4/2017.
 */
public class MapViewer {

    // May be able to move all of these into local variables depending on whether or not we need to access them outside of this class
    private JXMapViewer mapViewer;
    private TileFactoryInfo tileFactoryInfo;
    private VirtualEarthTileFactoryInfo.MVEMode tileFactoryType = VirtualEarthTileFactoryInfo.MAP;
    private DefaultTileFactory tileFactory;
    private CompoundPainter<JXMapViewer> compoundPainter;

    private WaypointPainter<MyWaypoint> waypointPainter;
    private WayPainter wayPainter;
    private PathPainter pathPainter;

    private boolean waysEnabled;
    private boolean nodesEnabled;
    private boolean isMapView = true;

    public MapViewer() {

        mapViewer = new JXMapViewer();
        tileFactoryInfo = new VirtualEarthTileFactoryInfo(tileFactoryType);
        tileFactory = new DefaultTileFactory(tileFactoryInfo);

        mapViewer.setTileFactory(tileFactory);
        tileFactory.setThreadPoolSize(Runtime.getRuntime().availableProcessors());

        File cacheDir = new File(System.getProperty("user.home") + File.separator + ".jxmapmapviewer2");
        LocalResponseCache.installResponseCache(tileFactoryInfo.getBaseURL(), cacheDir, false);


        GeoPosition gp = new GeoPosition(UserProfile.getInstance().getMap().getLatitude(), UserProfile.getInstance().getMap().getLongitude());

        mapViewer.setZoom(5);
        mapViewer.setAddressLocation(gp);

        MouseInputListener mia = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);

        mapViewer.addMouseListener(new CenterMapListener(mapViewer));

        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCursor(mapViewer));
        mapViewer.addMouseWheelListener((event) -> mapViewer.repaint());

        mapViewer.addKeyListener(new PanKeyListener(mapViewer));


        wayPainter = new WayPainter(mapViewer);
        setWayLines();

        waypointPainter = new WaypointPainter<>();
        waypointPainter.setRenderer(new FancyWaypointRenderer());
        setNodeSet();

        AgentPainter agentPainter = new AgentPainter(mapViewer);

        InfoPainter infoPainter = new InfoPainter();

        pathPainter = new PathPainter(mapViewer);

        compoundPainter = new CompoundPainter<>();
        compoundPainter.addPainter(pathPainter);
        compoundPainter.addPainter(agentPainter);
        compoundPainter.addPainter(infoPainter);


        mapViewer.setOverlayPainter(compoundPainter);
    }

    public JXMapViewer getMapViewer() {
        return mapViewer;
    }

    public boolean isWaysEnabled() {
        return waysEnabled;
    }

    public void setWaysEnabled(boolean waysEnabled) {
        if (waysEnabled) {
            compoundPainter.addPainter(wayPainter);
        } else {
            compoundPainter.removePainter(wayPainter);
        }
        this.waysEnabled = waysEnabled;
    }

    public boolean isNodesEnabled() {
        return nodesEnabled;
    }

    public void setNodesEnabled(boolean nodesEnabled) {
        if (nodesEnabled) {
            compoundPainter.addPainter(waypointPainter);
        } else {
            compoundPainter.removePainter(waypointPainter);
        }
        this.nodesEnabled = nodesEnabled;
    }

    public void setMapView(boolean isMapView) {
        if (isMapView) {
            tileFactoryType = VirtualEarthTileFactoryInfo.MAP;
        } else {
            tileFactoryType = VirtualEarthTileFactoryInfo.SATELLITE;
        }
        this.isMapView = isMapView;
    }

    public boolean isMapView() {
        return isMapView;
    }

    /**
     * Reload the tile factory (used for swapping b/w satellite and map)
     */
    public void reloadTileFactory() {
        tileFactoryInfo = new VirtualEarthTileFactoryInfo(tileFactoryType);
        tileFactory = new DefaultTileFactory(tileFactoryInfo);
        mapViewer.setTileFactory(tileFactory);
    }

    /**
     * Sets the lines to be drawn for the ways
     */
    public void setWayLines() {
        ArrayList<Pair<ArrayList<Pair<GeoPosition, GeoPosition>>, Color>> nodePairLists = new ArrayList<>();

        UserProfile.getInstance().getWayMap().values().forEach(way -> {
            ArrayList<Pair<GeoPosition, GeoPosition>> nodePairList = new ArrayList<>();
            ArrayList<TNode> wayNodes = way.getNodes();
            for (int i = 0; i < wayNodes.size(); i++) {
                if (i < wayNodes.size() - 1) {
                    nodePairList.add(new Pair<>(
                            new GeoPosition(wayNodes.get(i).getLat(), wayNodes.get(i).getLon()),
                            new GeoPosition(wayNodes.get(i + 1).getLat(), wayNodes.get(i + 1).getLon())
                    ));
                }
            }
            nodePairLists.add(new Pair<>(nodePairList, way.getRoadType().getColor()));
        });
        wayPainter.setWayLines(nodePairLists);
    }

    /**
     * Sets the nodes we're painting to this set (used for loading)
     */
    public void setNodeSet() {
        Set<MyWaypoint> pointSet = new HashSet<MyWaypoint>();

        //Adds each way node into our Set for painting
        UserProfile.getInstance().getWayMap().values().forEach(way -> {
            ArrayList<TNode> wayNodes = way.getNodes();
            for (int i = 0; i < way.getNodes().size(); i++) {
                GeoPosition nodePos = new GeoPosition(wayNodes.get(i).getLat(), wayNodes.get(i).getLon());
                pointSet.add(new MyWaypoint("", Color.WHITE, nodePos));
            }
        });
        waypointPainter.setWaypoints(pointSet);
    }

    /**
     * Set the paths for our agents
     * @param list - Agent path list
     */
    public void setPath(List<List<TNode>> list) {
        pathPainter.setPath(list);
        mapViewer.repaint();
    }
}
