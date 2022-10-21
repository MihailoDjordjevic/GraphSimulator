package mainFrame.toolbar.basicOperations;

import graphView.graphPane.State;
import javafx.scene.input.KeyCode;
import mainFrame.ActionTemplate;
import mainFrame.toolbar.ToolbarActionTemplate;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class NewButton extends ToolbarActionTemplate {
    public NewButton(State actionState) {
        super(actionState);
        putValue(SMALL_ICON, loadIcon("icons/newIcon.png"));
        putValue(MNEMONIC_KEY, KeyEvent.VK_N);
    }
}
