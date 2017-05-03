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
        agentPool.getAgentList().forEach(agent -> {
            if (!agent.isDone() && agent.getPath().size() > 0) {
                agent.advancePosition();
            }
        });
        timeSystem.stepRan();
        viewer.getMapViewer().repaint();
    }

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
