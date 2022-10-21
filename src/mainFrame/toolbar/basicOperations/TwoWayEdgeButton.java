package mainFrame.toolbar.basicOperations;

import graphView.graphPane.State;
import mainFrame.toolbar.ToolbarActionTemplate;

import java.awt.event.KeyEvent;

public class TwoWayEdgeButton extends ToolbarActionTemplate {
    protected TwoWayEdgeButton(State actionState) {
        super(actionState);
        putValue(SMALL_ICON, loadIcon("icons/twoWayArrowIcon.png"));
        putValue(MNEMONIC_KEY, KeyEvent.VK_T);
    }
}
