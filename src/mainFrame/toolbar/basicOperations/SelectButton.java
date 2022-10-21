package mainFrame.toolbar.basicOperations;

import graphView.graphPane.State;
import mainFrame.ActionTemplate;
import mainFrame.toolbar.ToolbarActionTemplate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class SelectButton extends ToolbarActionTemplate {
    public SelectButton(State actionState) {
        super(actionState);
        putValue(SMALL_ICON, loadIcon("icons/selectIcon.png"));
        putValue(MNEMONIC_KEY, KeyEvent.VK_S);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
    }
}
