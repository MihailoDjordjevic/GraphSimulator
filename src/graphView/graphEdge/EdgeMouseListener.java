package graphView.graphEdge;

import graphView.graphPane.GraphPane;
import graphView.graphPane.ToolbarState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EdgeMouseListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

        Component sourceEdgeView = ((JTextField) e.getSource()).getParent();

        switch (ToolbarState.toolbarstate){

            case SELECT:{
                if (e.getClickCount() == 2 && e.getButton() == 1) processDoubleClick(e);
                if (e.getClickCount() == 1 && e.getButton() == 1) processOneClick(e);
            } break;
            case DELETE:{
                GraphPane.deleteEdge(sourceEdgeView, false);
            } break;
            default: break;
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void setJDialog(JDialog jDialog, EdgeView source, JLabel srcLabel){

        jDialog.setSize(200, 100);
        jDialog.setLocation(450, 450);

        JLabel label = new JLabel();
        label.setText("From " + source.getEdgeModel().getSourceNode().getNodeNumber() +
                " to " + source.getEdgeModel().getDestinationNode().getNodeNumber() + "\n" +
                "flow: " + source.getEdgeModel().getFlow() + " capacity: " + source.getEdgeModel().getCapacity());

        jDialog.add(label);
        jDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jDialog.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                srcLabel.setBorder(null);
                ((JDialog) e.getSource()).dispose();
            }
        });
        jDialog.setVisible(true);

    }

    private void processOneClick(MouseEvent e){

        JLabel srcLabel = (JLabel) e.getSource();
        srcLabel.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
        EdgeView source = (EdgeView) srcLabel.getParent();

    }

    private void processDoubleClick(MouseEvent e){

        JLabel srcLabel = (JLabel) e.getSource();
        srcLabel.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
        EdgeView source = (EdgeView) srcLabel.getParent();

        JDialog jDialog = new JDialog();
        setJDialog(jDialog, source, srcLabel);

    }

}
