package gui;

import nodes.impl.Way;
import org.jgraph.JGraph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableUndirectedGraph;
import org.jgrapht.graph.SimpleGraph;

import javax.swing.*;

/**
 * Created by aron on 2/22/17.
 */
public class GraphVisualizer extends JFrame {


    public GraphVisualizer() {
        ListenableGraph ug = new ListenableUndirectedGraph(DefaultEdge.class);
        JGraphModelAdapter adapter = new JGraphModelAdapter(ug);
        JGraph jgraph = new JGraph();
        add(jgraph);
        ug.addVertex("hi");
        ug.addVertex("hi2");
        ug.addEdge("hi", "hi2");
        setVisible(true);
    }

}
