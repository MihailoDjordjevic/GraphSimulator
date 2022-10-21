package mainFrame.menuBar.fileMenu;

import mainFrame.menuBar.fileMenu.newObjectsMenu.NewObjectsMenu;

import javax.swing.*;

public class FileMenu extends JMenu {
    public FileMenu(String s) {
        super(s);
        init();
    }

    private void init(){

        add(new NewObjectsMenu("New"));

    }
}
