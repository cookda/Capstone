package sim;

import gui.MapViewer;

/**
 * Created by aron on 3/20/17.
 */
public class Simulation {

    private TimeSystem timeSystem;
    private AgentPool agentPool;
    private MapViewer viewer;

    public Simulation(MapViewer viewer) {
        timeSystem = new TimeSystem();
        agentPool = AgentPool.getInstance();
        this.viewer = viewer;
    }

    public void runStep() {
        timeSystem.runStep();
        for (Agent agent : agentPool.getAgentList()) {
            //calculate distance within Agent and assign its distance. redraw.

            viewer.getMapViewer().repaint();
        }
    }

}
