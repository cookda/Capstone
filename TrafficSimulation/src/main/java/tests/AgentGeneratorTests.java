package tests;

import sim.AgentGenerator;
import sim.AgentPool;

/**
 * Created by aron on 3/24/17.
 */
public class AgentGeneratorTests {


    public static void testRandomGenerator(int amount) {
        new AgentGenerator().randomAgentGenerator(amount);
        System.out.println("Generated " + amount + " agents");
    }

    public static void listAgents() {
        System.out.println("Agent listing");
        AgentPool.getInstance().getAgentList().forEach(agent -> {
            System.out.println("Agent: " + agent.toString());
        });
    }
}
