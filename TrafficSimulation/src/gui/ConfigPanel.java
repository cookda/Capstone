package gui;

import core.Constants;
import core.UserMap;
import core.UserProfile;
import test.MapTest;

import javax.swing.*;
import java.awt.*;

/**
 * Created by aron on 2/17/17.
 */
public class ConfigPanel extends JPanel {


    private JTextField latField;
    private JLabel latLabel;
    private JTextField lonField;
    private JLabel lonLabel;
    private JTextField radField;
    private JLabel radLabel;
    private JButton goButton;

    private UserProfile userProfile;

    public ConfigPanel() {
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        setLayout(gbl);

        userProfile = UserProfile.getInstance();

        latField = new JTextField("" + Constants.BOONE_LAT);
        latField.setColumns(10);
        latLabel = new JLabel("Latitude: ");

        lonField = new JTextField("" + Constants.BOONE_LONG);
        lonField.setColumns(10);
        lonLabel = new JLabel("Longitude: ");

        radField = new JTextField("0.03");
        radField.setColumns(10);
        radLabel = new JLabel("Radius: ");

        goButton = new JButton("Grab");
        goButton.addActionListener(a -> {
            userProfile.setMap(
                new UserMap(
                        Double.parseDouble(latField.getText()),
                        Double.parseDouble(lonField.getText()),
                        Double.parseDouble(radField.getText()),
                        15
                ));
            System.out.println(userProfile.getMap());
            Thread t = new Thread(MapTest::basicTest);
            t.start();
            try {
                ((MainFrame) SwingUtilities.getWindowAncestor(this)).getMapPanel().refreshImage();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });


        gbc.gridx = 0;
        gbc.gridy = 0;
        add(latLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(latField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lonLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(lonField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(radLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(radField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(goButton, gbc);
        setVisible(true);
    }
}
