package gui.jxmapviewer;

import javafx.util.Pair;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.GeoPosition;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Created by Wayne on 3/4/2017.
 * Adapted from https://github.com/flashpixx/MecSim/blob/master/src/main/java/de/tu_clausthal/in/mec/ui/COSMViewer.java
 */
public class WayPainter implements Painter<JXMapViewer> {

    private ArrayList<Pair<ArrayList<Pair<GeoPosition, GeoPosition>>, Color>> wayLines;
    private JXMapViewer viewer;

    public WayPainter(JXMapViewer viewer) {
        this.viewer = viewer;
        wayLines = new ArrayList<>();
    }

    /**
     * Paints each way (road) onto the viewer.
     * @param graphics2D - Graphics object (from viewer)
     * @param jxMapViewer - Viewer itself
     * @param width - Width of viewport
     * @param height - Height of viewport
     */
    @Override
    public void paint(Graphics2D graphics2D, JXMapViewer jxMapViewer, int width, int height) {

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //Use anti-aliasing to draw the ways
        graphics2D.translate(-viewer.getViewportBounds().x, -viewer.getViewportBounds().y);
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

        final Stroke basicStroke = new BasicStroke();
        for (Pair<ArrayList<Pair<GeoPosition, GeoPosition>>, Color> wayPair : wayLines) {
            for (Pair<GeoPosition, GeoPosition> pairList : wayPair.getKey()){
                graphics2D.setColor(Color.RED);
                graphics2D.setStroke(basicStroke);

                Point2D startPoint = viewer.getTileFactory().geoToPixel(pairList.getKey(), viewer.getZoom());
                Point2D endPoint = viewer.getTileFactory().geoToPixel(pairList.getValue(), viewer.getZoom());

                graphics2D.drawLine((int) startPoint.getX(), (int) startPoint.getY(), (int) endPoint.getX(), (int) endPoint.getY());
            }
        }
    }

    public void setWayLines(ArrayList<Pair<ArrayList<Pair<GeoPosition, GeoPosition>>, Color>> wayLines) {
        this.wayLines = wayLines;
    }

}
