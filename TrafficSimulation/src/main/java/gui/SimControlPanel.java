package gui;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

public class SimControlPanel extends JPanel {

    /**
     * Create the panel.
     */
    public SimControlPanel() {

        JButton btnStart = new JButton("Start");

        JSlider speedSlider = new JSlider();

        JLabel lblSimulationSpeed = new JLabel("Simulation Speed: ");
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(35)
                                .addComponent(btnStart, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                                .addGap(35))
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(speedSlider, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(lblSimulationSpeed, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(19)))
                                .addContainerGap())
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnStart)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(lblSimulationSpeed)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(speedSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(333, Short.MAX_VALUE))
        );
        setLayout(groupLayout);

    }
}

