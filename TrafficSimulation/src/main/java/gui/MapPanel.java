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

    private BufferedImage map;
    private JLabel mapLbl; //temp

    public MapPanel() throws IOException {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        map = ImageIO.read(new File(Constants.IMAGE_DIR + UserProfile.getInstance().getMap().getImageName()));
        mapLbl = new JLabel();
        mapLbl.setIcon(new ImageIcon(map));
        c.fill = GridBagConstraints.HORIZONTAL;
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(mapLbl, c);
    }


    public Dimension getImgDims() {
        return new Dimension(map.getWidth() + 2, map.getHeight() + 2);
    }

    public void refreshImage() throws IOException {
        try {
            map = ImageIO.read(new File(Constants.IMAGE_DIR + UserProfile.getInstance().getMap().getImageName()));
            mapLbl.setIcon(new ImageIcon(map));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
