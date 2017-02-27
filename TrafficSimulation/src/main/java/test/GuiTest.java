package test;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import core.Constants;
import gui.MainFrame;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.input.CenterMapListener;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.viewer.*;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by aron on 2/17/17.
 */
public class GuiTest {

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

        JFrame frame = new JFrame("Viewer");
        frame.getContentPane().add(viewer);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}
