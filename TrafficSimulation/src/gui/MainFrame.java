package gui;


import javax.swing.*;
import java.io.IOException;

/**
 * Created by aron on 2/6/17.
 */
public class MainFrame extends JFrame {

    private MapPanel mapPanel;

    public MainFrame() throws IOException {
        setSize(800, 500);
        setVisible(true);
        setTitle("Traffic simulation");
        mapPanel = new MapPanel();
        add(mapPanel);
    }
}
