package mainFrame.toolbar.basicOperations;

import graphView.graphPane.State;
import mainFrame.ActionTemplate;
import mainFrame.toolbar.ToolbarActionTemplate;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class BasicOperationsToolbar extends JToolBar {

    private JToggleButton deleteButton;
    private JToggleButton newButton;
    private JToggleButton selectButton;
    private JToggleButton twoWayEdgeButton;

    public BasicOperationsToolbar() {
        init();
    }

    private void init(){

        setPreferredSize(new Dimension(300, 31));
        setBackground(new Color(170,170,170));
        setBorder(new LineBorder(Color.BLACK,1));

        initButtons();

        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
        flowLayout.setVgap(2);
        flowLayout.setHgap(2);
        setLayout(flowLayout);


    }

    private void initButtons(){

        deleteButton = new JToggleButton(new DeleteButton(State.DELETE));
        deleteButton.setSize(new Dimension(16, 16));
        deleteButton.setMargin(new Insets(0,0,0,0));
        add(deleteButton);

        newButton = new JToggleButton(new NewButton(State.NEW));
        newButton.setSize(new Dimension(16, 16));
        newButton.setMargin(new Insets(0,0,0,0));
        add(newButton);

        selectButton = new JToggleButton(new SelectButton(State.SELECT));
        selectButton.setSize(new Dimension(16, 16));
        selectButton.setMargin(new Insets(0,0,0,0));
        add(selectButton);

        twoWayEdgeButton = new JToggleButton(new TwoWayEdgeButton(State.TWOWAYEDGE));
        twoWayEdgeButton.setSize(new Dimension(16, 16));
        twoWayEdgeButton.setMargin(new Insets(0,0,0,0));
        add(twoWayEdgeButton);

        selectButton.addItemListener((ItemListener) selectButton.getAction());
        newButton.addItemListener((ItemListener) newButton.getAction());
        deleteButton.addItemListener((ItemListener) deleteButton.getAction());
        twoWayEdgeButton.addItemListener((ItemListener) twoWayEdgeButton.getAction());

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(selectButton);
        buttonGroup.add(newButton);
        buttonGroup.add(deleteButton);
        buttonGroup.add(twoWayEdgeButton);

        ToolbarActionTemplate.setDefaultButtonBorder(selectButton.getBorder());
    }
}
