package mainFrame.menuBar.fileMenu.newObjectsMenu.newNode;

import main.Main;
import mainFrame.MainFrame;
import mainFrame.ActionTemplate;
import mainFrame.projectsTree.ProjectsTree;
import mainFrame.tabbedPane.TabbedPane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;

public class NewNode extends ActionTemplate {

    public NewNode() {
        putValue(SMALL_ICON, loadIcon("icons/newNodeIcon.png"));
        putValue(NAME, "New Node");
        putValue(SMALL_ICON, loadIcon("newObjectsMenu/icons/newIcon.png"));
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        openDialog();

        ProjectsTree.getInstance().updateHeight(true, 1);

        SwingUtilities.updateComponentTreeUI(ProjectsTree.getInstance());
        SwingUtilities.updateComponentTreeUI(TabbedPane.getInstance());
    }

    private void openDialog() {

        if (NewNodeDialog.isInstanceNull()){
            NewNodeDialog.getInstance();
        } else {
            NewNodeDialog.setToNull();
            NewNodeDialog.getInstance();
        }

    }

}
