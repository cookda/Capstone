package test;

import gui.GraphVisualizer;
import gui.MainFrame;

/**
 * Created by aron on 2/17/17.
 */
public class GuiTest {

    public static void testGUI() {
        try {
            MainFrame mf = new MainFrame();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void visTest() {
        GraphVisualizer gv = new GraphVisualizer();
    }
}
