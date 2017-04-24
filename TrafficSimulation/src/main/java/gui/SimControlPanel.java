package gui;

import sim.Simulation;
import sim.TimeSystem;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

public class SimControlPanel extends JPanel {

    private Simulation sim;
    private TimeSystem timeSystem;

    /**
     * Create the panel.
     */
    public SimControlPanel(Simulation sim) {
        this.sim = sim;
        timeSystem = TimeSystem.getInstance();

        JButton btnStart = new JButton("Start");
        btnStart.addActionListener(e -> {
            if (btnStart.getText().equals("Start")) {
                btnStart.setText("Stop");
            } else {
                btnStart.setText("Start");
            }
            this.sim.toggleRun();
        });

        JButton runStepButton = new JButton("Run step");
        runStepButton.addActionListener(e -> sim.runStep());

        JLabel lblSimulationSpeed = new JLabel("Simulation Speed: " + timeSystem.getTimeSpeed());
        JSlider speedSlider = new JSlider();
        speedSlider.setMinimum(1);
        speedSlider.setMaximum(10000);
        speedSlider.setValue(1000);
        speedSlider.addChangeListener(change -> {
            timeSystem.setTimeSpeed((double) speedSlider.getValue() / 1000);
            lblSimulationSpeed.setText("Simulation Speed: " + timeSystem.getTimeSpeed());
        });

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(35)
                                .addComponent(btnStart, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                                .addGap(35))
                                .addComponent(runStepButton, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
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
                                .addComponent(runStepButton)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(lblSimulationSpeed)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(speedSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(333, Short.MAX_VALUE))
        );
        setLayout(groupLayout);

    }
}

