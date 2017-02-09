package gui;


import javax.swing.*;

/**
 * Created by aron on 2/6/17.
 */
public class MainFrame extends JFrame {

    private MapPanel mapPanel;

    public MainFrame() {
        setSize(800, 500);
        setVisible(true);
        setTitle("Traffic simulation");
        add(mapPanel);
    }
}
