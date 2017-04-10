package gui;


import core.UserProfile;
import sim.Simulation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.io.*;

public class SimFrame extends JFrame {

    private JPanel contentPane;
    private MapViewer mv;
    private final JFileChooser jfc = new JFileChooser(System.getProperty("user.home") + "/CapstoneData/profiles");


    public SimFrame(Simulation sim, MapViewer mv) {
        setTitle("Traffic routing simulator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        initializeMenu();


        JPanel controlPanel = new ControlPanel(mv);
        JPanel SimPanel = new SimControlPanel(sim);
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                                .addComponent(controlPanel, GroupLayout.DEFAULT_SIZE, 1176, Short.MAX_VALUE)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addComponent(mv.getMapViewer(), GroupLayout.DEFAULT_SIZE, 1174, Short.MAX_VALUE)
                                .addContainerGap()
                                .addComponent(SimPanel, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        ));
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addComponent(mv.getMapViewer(), GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                        .addComponent(SimPanel, GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
        );
        contentPane.setLayout(gl_contentPane);
        setVisible(true);
    }

    public MapViewer getMapViewer() {
        return mv;
    }

    private void initializeMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenu userMenu = new JMenu("User Profile");
        JMenuItem loadProfile = new JMenuItem("Load");
        loadProfile.addActionListener(event -> load());
        JMenuItem saveProfile = new JMenuItem("Save");
        saveProfile.addActionListener(event -> save());
        userMenu.add(loadProfile);
        userMenu.add(saveProfile);

        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(event -> System.exit(1));

        fileMenu.add(userMenu);
        fileMenu.add(quitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    private void load() {
        int returnVal = jfc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();
            try {
                ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(file));
                UserProfile.setInstance((UserProfile) objectIn.readObject());
                objectIn.close();
            } catch (Exception e) {
                System.out.println("Error in loading! " + e.getMessage());
            }
        }
        mv.setNodeSet();
        mv.setWayLines();
        mv.getMapViewer().repaint();
    }

    private void save() {
        int returnVal = jfc.showSaveDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();
            try {
                ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(file));
                objectOut.writeObject(UserProfile.getInstance());
                objectOut.close();
            } catch (Exception e) {
                System.out.println("Error in saving! " + e.getMessage());
            }
        }
    }
}
