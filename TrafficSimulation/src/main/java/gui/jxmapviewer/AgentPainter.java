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
        try {
            carImage = ImageIO.read(new File(Constants.IMAGE_DIR + "/agent.png"));
        } catch (Exception e) {
            System.out.println("Couldn't get the image for the car agents");
            System.out.println(e.getMessage());
        }
    }

    @Override public void paint(Graphics2D g, JXMapViewer jxMapViewer, int width, int height) {

        List<Agent> agents  = pool.getAgentList();

        g.translate(-viewer.getViewportBounds().x, -viewer.getViewportBounds().y); //Evil viewport hacks
        g = (Graphics2D) g.create();

        for (Agent agent : agents) {
            Point2D agentPixel = viewer.getTileFactory().geoToPixel(agent.getGeoPosition(), viewer.getZoom());
            int x = (int) agentPixel.getX();
            int y = (int) agentPixel.getY();
            g.drawImage(carImage, getWidth(x), getHeight(y), null);
        }
    }

    private int getWidth(int x) {
        return x - (carImage.getWidth() / 2);
    }

    private int getHeight(int y) {
        return y - (carImage.getHeight() / 2);
    }
}
