package gui.jxmapviewer;

import core.Constants;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.painter.Painter;
import sim.Agent;
import sim.AgentPool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.net.URL;
import java.util.List;


/**
 * Created by Wayne on 3/5/2017.
 */
public class AgentPainter implements Painter<JXMapViewer> {

    private JXMapViewer viewer;
    private AgentPool pool;
    private BufferedImage carImage;

    public AgentPainter(JXMapViewer viewer) {
        this.viewer = viewer;
        pool = AgentPool.getInstance();
        ClassLoader cl = getClass().getClassLoader();
        URL resourceURL = cl.getResource("java/gui/jxmapviewer/agent.png");
        String resourcePath = resourceURL == null ? "Null" : resourceURL.getPath();
        //System.out.println("Resource folder: " + resourcePath);
        try {
            carImage = ImageIO.read(new File(resourcePath));
        } catch (Exception e) {
            System.out.println("Couldn't get the image for the car agents");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void paint(Graphics2D g, JXMapViewer jxMapViewer, int width, int height) {

        List<Agent> agents  = pool.getAgentList();

        g.translate(-viewer.getViewportBounds().x, -viewer.getViewportBounds().y);
        g = (Graphics2D) g.create();
        Image scaledImage =
                 carImage.getScaledInstance(getScaled(carImage.getWidth(), viewer.getZoom()), getScaled(carImage.getHeight(), viewer.getZoom()), 0);

        for (Agent agent : agents) {
            Point2D agentPixel = viewer.getTileFactory().geoToPixel(agent.getGeoPosition(), viewer.getZoom());
            int x = (int) agentPixel.getX();
            int y = (int) agentPixel.getY();
            g.drawImage(scaledImage, getWidth(x), getHeight(y), null);
        }
    }

    private int getWidth(int x) {
        return x - (carImage.getWidth() / 2);
    }

    private int getHeight(int y) {
        return y - (carImage.getHeight() / 2);
    }

    private int getScaled(int val, int zoom) {
        return val; /// (zoom + 1);
    }
}
