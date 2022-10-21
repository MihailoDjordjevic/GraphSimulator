package mainFrame.menuBar.fileMenu.newObjectsMenu;

import mainFrame.menuBar.fileMenu.newObjectsMenu.newNode.NewNode;
import mainFrame.menuBar.fileMenu.newObjectsMenu.newProject.NewProject;

import javax.swing.*;
import java.awt.*;

public class NewObjectsMenu extends JMenu {

    public NewObjectsMenu(String s) {
        super(s);
        init();
    }

    private void init(){

        add(new NewProject());
        this.getMenuComponents()[0].setFont(new Font("new", Font.PLAIN, 11));
        add(new NewNode());
        this.getMenuComponents()[1].setFont(new Font("new", Font.PLAIN, 11));
    }
}
