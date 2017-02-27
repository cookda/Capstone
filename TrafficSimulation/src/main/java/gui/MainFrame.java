package gui;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by aron on 2/6/17.
 */
public class MainFrame extends JFrame {

    private MapPanel mapPanel;
    private ConfigPanel confPanel;
    private GridLayout gdLayout;
    private JMenuBar menuBar;
    private JMenu menu;

    public MainFrame() throws IOException {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 500);
        gdLayout = new GridLayout(0, 2);
        setLayout(gdLayout);
        setTitle("Traffic simulation");
        mapPanel = new MapPanel();
        mapPanel.setPreferredSize(mapPanel.getImgDims());
        confPanel = new ConfigPanel();

        menuBar = new JMenuBar();
        menu = new JMenu("File");

        menu.add(new JMenuItem("Nothing so far..."));

        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(e -> System.exit(0));
        menu.add(quitItem);

        menuBar.add(menu);
        setJMenuBar(menuBar);


        add(mapPanel);
        add(confPanel);
        pack();
        setVisible(true);
    }

    public MapPanel getMapPanel() {
        return mapPanel;
    }
}
