package mainFrame.toolbar.basicOperations;

import graphView.graphPane.State;
import mainFrame.ActionTemplate;
import mainFrame.toolbar.ToolbarActionTemplate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteButton extends ToolbarActionTemplate {

    public DeleteButton(State actionState) {
        super(actionState);
        putValue(SMALL_ICON, loadIcon("icons/deleteIcon.png"));
        putValue(MNEMONIC_KEY, KeyEvent.VK_D);
    }
}
