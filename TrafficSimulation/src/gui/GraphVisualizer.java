package gui;

import core.UserProfile;
import nodes.impl.TNode;
import nodes.impl.Way;
import org.jgraph.JGraph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableUndirectedGraph;
import org.jgrapht.graph.SimpleGraph;

import javax.swing.*;
import java.awt.*;

/**
 * Created by aron on 2/22/17.
 */
public class GraphVisualizer extends JFrame {

    private static final Dimension SIZE = new Dimension(500, 500);
    private final UserProfile up = UserProfile.getInstance();

    public GraphVisualizer() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ListenableGraph ug = new ListenableUndirectedGraph(DefaultEdge.class);
        JGraphModelAdapter adapter = new JGraphModelAdapter(ug);
        JGraph jgraph = new JGraph(adapter);
        add(jgraph);


        up.getWays().forEach(f -> {
            f.getRefs().forEach(n -> ug.addVertex(up.getNodeMap().get(n)));
        });

        up.getWays().forEach(f -> {
            if (f.getRefs().size() > 1) {
                for (int i = 0; i < f.getRefs().size(); i += 2) {
                    TNode n1 = node(i);
                    TNode n2 = node(i + 1);
                    System.out.println("Node 1: " + n1);
                    System.out.println("Node 2: " + n2);
                    if (n1 != null && n2 != null) ug.addEdge(n1, n2);
                }
            }
        });

        setVisible(true);
    }

    /**
     * This is lazy, remove
     * TODO
     */
    private TNode node(long id) {
        return up.getNodeMap().get(id);
    }

}
