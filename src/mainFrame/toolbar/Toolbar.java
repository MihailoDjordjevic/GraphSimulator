package mainFrame.toolbar;

import graphView.graphPane.State;
import mainFrame.toolbar.basicOperations.BasicOperationsToolbar;
import mainFrame.toolbar.basicOperations.DeleteButton;
import mainFrame.toolbar.basicOperations.NewButton;
import mainFrame.toolbar.basicOperations.SelectButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class Toolbar extends JToolBar {

    private static Toolbar toolbar = null;

    public static Toolbar getToolbar(){
        if (toolbar == null)
            toolbar = new Toolbar();
        return toolbar;
    }

    public Toolbar() {
        init();
    }

    private void init(){
        setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, 75));
        setBackground(new Color(170,170,170));
        setFloatable(false);
        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(new BasicOperationsToolbar());
    }

}
