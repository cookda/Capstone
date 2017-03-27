package gui;

import javax.swing.*;

/**
 * Created by aron on 3/27/17.
 */
public class SimPanel extends JPanel {

    public SimPanel() {
        JButton startButton = new JButton("Start");
        JSlider speedSlider = new JSlider();

        GroupLayout layout = new GroupLayout(this);
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                    .addComponent(startButton)
                    .addGap(100)
                    .addComponent(speedSlider)
        );
        setLayout(layout);
    }
}
