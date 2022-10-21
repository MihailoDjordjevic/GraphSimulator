package mainFrame;

import graphView.graphPane.State;
import graphView.graphPane.ToolbarState;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public abstract class ActionTemplate extends AbstractAction {

    protected Icon loadIcon(String fileName){
        URL imageURL = this.getClass().getResource(fileName);

        Icon icon = null;

        if (imageURL != null)
            icon = new ImageIcon(imageURL);

        return icon;
    }

}
