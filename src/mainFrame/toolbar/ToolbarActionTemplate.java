package mainFrame.toolbar;

import graphView.graphPane.State;
import graphView.graphPane.ToolbarState;
import mainFrame.ActionTemplate;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public abstract class ToolbarActionTemplate extends ActionTemplate implements ItemListener {

    private State actionState;
    protected boolean isSelected = false;
    private static Border defaultButtonBorder;

    protected ToolbarActionTemplate(State actionState) {
        this.actionState = actionState;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setToolbarState();
    }

    protected void setToolbarState(){
        ToolbarState.toolbarstate = actionState;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == 1) ((JToggleButton) e.getSource()).setBorder(new LineBorder(Color.BLUE, 2));
        if(e.getStateChange() == 2) ((JToggleButton) e.getSource()).setBorder(defaultButtonBorder);
    }

    public static void setDefaultButtonBorder(Border border){
        defaultButtonBorder = border;
    }

}
