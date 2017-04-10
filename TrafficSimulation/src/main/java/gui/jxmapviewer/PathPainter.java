package gui.jxmapviewer;

import nodes.impl.TNode;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.painter.Painter;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by aron on 4/3/17.
 */
public class PathPainter implements Painter<JXMapViewer> {

    private JXMapViewer viewer;
    private List<TNode> path;

    public PathPainter(JXMapViewer viewer) {
        this.viewer = viewer;
        path = new ArrayList<>();
    }

    @Override
    public void paint(Graphics2D g, JXMapViewer object, int width, int height) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //Use anti-aliasing to draw the ways
        g.translate(-viewer.getViewportBounds().x, -viewer.getViewportBounds().y); //Evil viewport hacks
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

        g.setColor(Color.CYAN);
        g.setStroke(new BasicStroke(5));
        for (int i = 0; i < path.size() - 1; i ++) {
            Point2D startPoint = viewer.getTileFactory().geoToPixel(path.get(i).getGeoPosition(), viewer.getZoom());
            Point2D endPoint = viewer.getTileFactory().geoToPixel(path.get(i + 1).getGeoPosition(), viewer.getZoom());

            g.drawLine((int) startPoint.getX(), (int) startPoint.getY(), (int) endPoint.getX(), (int) endPoint.getY());
        }
    }

    public void setPath(List<TNode> path) {
        this.path = path;
    }
}
