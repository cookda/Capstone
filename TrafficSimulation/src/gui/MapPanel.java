package gui;

import core.Constants;
import core.UserProfile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by aron on 2/6/17.
 */
public class MapPanel extends JPanel {

    private Image map;
    private JLabel mapLbl; //temp

    public MapPanel() throws IOException {
        map = ImageIO.read(new File(Constants.IMAGE_DIR + "36.2168_-81.6746_15.png"));
        mapLbl = new JLabel();
        mapLbl.setIcon(new ImageIcon(map));
        add(mapLbl);
    }

}
