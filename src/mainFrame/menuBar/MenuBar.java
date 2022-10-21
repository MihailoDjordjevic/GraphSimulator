package mainFrame.menuBar;

import mainFrame.menuBar.fileMenu.FileMenu;

import javax.swing.*;
import java.awt.*;

public class MenuBar extends JMenuBar {

    private static MenuBar menuBar = null;

    private JMenu newMenu;

    public static MenuBar getMenuBar(){
        if (menuBar == null) {
            menuBar = new MenuBar();
        }
        return menuBar;
    }

    public MenuBar() {
        init();
    }

    private void init(){
        setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, 30));

        FileMenu fileMenu = new FileMenu("File");
        add(fileMenu);
    }

}
