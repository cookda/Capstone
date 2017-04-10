package sim;

import gui.MapViewer;

/**
 * Created by aron on 3/20/17.
 */
public class Simulation {

    private TimeSystem timeSystem;
    private AgentPool agentPool;
    private MapViewer viewer;
    private boolean running = false;

    public Simulation(MapViewer viewer) {
        timeSystem = TimeSystem.getInstance();
        agentPool = AgentPool.getInstance();
        this.viewer = viewer;
        System.out.println("Sim created");
    }

    public void runStep() {
        for (Agent agent : agentPool.getAgentList()) {
            if (agent.getGeoPosition().equals(agent.getTrip().getKey())) {
                agent.setGeoPosition(agent.getTrip().getValue());
            } else {
                agent.setGeoPosition(agent.getTrip().getKey());
            }
        }
        timeSystem.stepRan();
        viewer.getMapViewer().repaint();
    }

    /**
     * In this method, we will do something along the following
     * Increment time
     * Get all agents from the UserProfile or whichever class stores them
     * For each agent, retrieve a distance calculation based on their position and the direction they are traveling
     * Store this calculated distance as the new position of the agent
     * Send an update call to the renderer
     * The renderer will then draw the new position of each agent
     *
     * The running boolean will be set to start/stop/etc based on the gui
     */
    public void run() {
        while (running) {
            runStep();
        }
    }

    public void toggleRun() {
        running = !running;
        if (running) {
            new Thread(this::run).start();
        }
    }
}
