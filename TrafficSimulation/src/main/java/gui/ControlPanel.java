package gui;



import org.jxmapviewer.JXMapViewer;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

public class ControlPanel extends JPanel {

    private MapViewer mapViewer;

    /**
     * Create the panel.
     */
    public ControlPanel(MapViewer mapViewer) {
        this.mapViewer = mapViewer;

        JButton wayButton = new JButton("Enable ways");
        wayButton.addActionListener(event -> {
            if (mapViewer.isWaysEnabled()) {
                mapViewer.setWaysEnabled(false);
                wayButton.setText("Enable ways");
            } else {
                mapViewer.setWaysEnabled(true);
                wayButton.setText("Disable ways");
            }
            mapViewer.getMapViewer().repaint();
        });

        JButton nodeButton = new JButton("Enable nodes");
        nodeButton.addActionListener(event -> {
            if (mapViewer.isNodesEnabled()) {
                mapViewer.setNodesEnabled(false);
                nodeButton.setText("Enable nodes");
            } else {
                mapViewer.setNodesEnabled(true);
                nodeButton.setText("Disable nodes");
            }
            mapViewer.getMapViewer().repaint();
        });

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(154)
                                .addComponent(wayButton)
                                .addGap(18)
                                .addComponent(nodeButton)
                                .addContainerGap(195, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(wayButton)
                                        .addComponent(nodeButton))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        setLayout(groupLayout);

    }
}
