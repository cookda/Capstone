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
            carImage = ImageIO.read(new File(Constants.IMAGE_DIR + "/car.png"));
        } catch (Exception e) {
            System.out.println("Couldn't get the image for the car agents");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void paint(Graphics2D g, JXMapViewer jxMapViewer, int width, int height) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.translate(-viewer.getViewportBounds().x, -viewer.getViewportBounds().y); //Evil viewport hacks
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

        //Creating a new Rescale
        RescaleOp transparentFilter = new RescaleOp(
                new float[] {0.5f, 0.5f, 0.5f, 1f}, //Last number sets opacity of the car
                new float[4],
                null
        );

        List<Agent> agents  = pool.getAgentList();


        for (Agent agent : agents) {
            Point2D agentPixel = transformAgentPosition(viewer.getTileFactory().geoToPixel(agent.getGeoPosition(), viewer.getZoom()));
            g.drawImage(carImage, transparentFilter, (int) agentPixel.getX() + carImage.getWidth(), (int) agentPixel.getY());
        }
    }

    private Point2D transformAgentPosition(Point2D agentPoint) {
        return new Point2D.Double(agentPoint.getX() + carImage.getWidth() / 2, agentPoint.getY() + carImage.getHeight() / 2);
    }
}
