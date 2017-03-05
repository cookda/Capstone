package sim;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wayne on 3/5/2017.
 */
public class AgentPool {

    //Eagerly instantiate the singleton
    private static final AgentPool instance = new AgentPool();

    private List<Agent> agentList;

    private AgentPool() {
        agentList = new ArrayList<>();
    }

    public static AgentPool getInstance() {
        return instance;
    }

    public List<Agent> getAgentList() {
        return agentList;
    }

    public void addAgent(Agent agent) {
        agentList.add(agent);
    }
}
