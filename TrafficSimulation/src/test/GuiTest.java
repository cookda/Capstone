package test;

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
}
