package mainFrame.tabbedPane;

import graphView.graphPane.GraphPane;

import javax.swing.*;

public class TabbedPane extends JTabbedPane {

    private static TabbedPane tabbedPane;

    public static TabbedPane getInstance(){

        if (tabbedPane == null)
            tabbedPane = new TabbedPane();

        return tabbedPane;
    }

    public TabbedPane() {
        init();
    }

    private void init(){
        this.setModel(new DefaultSingleSelectionModel());
    }

    public GraphPane getSelectedGraphPane(){
        return (GraphPane) ((JScrollPane) TabbedPane.getInstance().getSelectedComponent()).getViewport().getComponent(0);
    }
}
