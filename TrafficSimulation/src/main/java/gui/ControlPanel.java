package gui;



import org.jxmapviewer.JXMapViewer;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

public class ControlPanel extends JPanel {


    /**
     * Create the panel.
     */
    public ControlPanel(MapViewer mapViewer) {

        JButton wayButton = new JButton("Show ways");
        wayButton.addActionListener(event -> {
            if (mapViewer.isWaysEnabled()) {
                mapViewer.setWaysEnabled(false);
                wayButton.setText("Show ways");
            } else {
                mapViewer.setWaysEnabled(true);
                wayButton.setText("Hide ways");
            }
            mapViewer.getMapViewer().repaint();
        });

        JButton nodeButton = new JButton("Show nodes");
        nodeButton.addActionListener(event -> {
            if (mapViewer.isNodesEnabled()) {
                mapViewer.setNodesEnabled(false);
                nodeButton.setText("Show nodes");
            } else {
                mapViewer.setNodesEnabled(true);
                nodeButton.setText("Hide nodes");
            }
            mapViewer.getMapViewer().repaint();
        });

        JButton viewSwitcher = new JButton("Switch to Satellite");
        viewSwitcher.addActionListener(event -> {
            if (mapViewer.isMapView()) {
                mapViewer.setMapView(false);
                viewSwitcher.setText("Switch to Map");
            } else {
                mapViewer.setMapView(true);
                viewSwitcher.setText("Switch to Satellite");
            }
            mapViewer.reloadTileFactory();
            mapViewer.getMapViewer().repaint();
        });

        JButton btnShowAgents = new JButton("Show agents");

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(36)
                                .addComponent(btnShowAgents)
                                .addGap(29)
                                .addComponent(wayButton)
                                .addGap(18)
                                .addComponent(nodeButton)
                                .addGap(18)
                                .addComponent(viewSwitcher)
                                .addContainerGap(72, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(wayButton)
                                        .addComponent(nodeButton)
                                        .addComponent(viewSwitcher)
                                        .addComponent(btnShowAgents))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        setLayout(groupLayout);
    }
}