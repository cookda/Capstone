package gui.jxmapviewer;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.painter.Painter;
import sim.AgentPool;
import sim.TimeSystem;

import java.awt.*;
import java.text.DecimalFormat;

/**
 * Created by aron on 3/27/17.
 */
public class InfoPainter implements Painter<JXMapViewer> {

    private TimeSystem timeSystem = TimeSystem.getInstance();
    private AgentPool agentPool = AgentPool.getInstance();
    private final int xAlign = 15;
    private final int yStep = 30;
    private final int yBase = 20;
    private final Color infoBoxColor = new Color(96, 114, 120, 230);
    private final Font infoFont = new Font(Font.SANS_SERIF, Font.BOLD, 18);
    private final DecimalFormat format = new DecimalFormat("#.##");

    @Override
    public void paint(Graphics2D g, JXMapViewer object, int width, int height) {
        g.setFont(infoFont);
        g.setColor(infoBoxColor);
        g.fillRect(10, 0, 180, 100);
        g.setColor(Color.WHITE);
        g.drawString("Time: " + format.format(timeSystem.getTime()), xAlign, yBase);
        g.drawString("Speed: " + timeSystem.getTimeSpeed(), xAlign, yBase + yStep);
        g.drawString("Agents: " + agentPool.getAgentList().size(), xAlign, yBase + yStep * 2);
    }
}
