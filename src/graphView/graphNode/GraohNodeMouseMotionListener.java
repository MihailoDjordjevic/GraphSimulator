package graphView.graphNode;

import graphView.graphEdge.EdgeView;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class GraohNodeMouseMotionListener implements MouseMotionListener {
    @Override
    public void mouseDragged(MouseEvent e) {

        if (e.getSource() instanceof JLabel){
            JLabel graphNode = (JLabel) e.getSource();

            int newX = e.getX() + graphNode.getX() - 20;
            int newY = e.getY() + graphNode.getY() - 6;

            newX = Math.max(newX, 3);  //to prevent from going outside of graphPane
            newX = Math.min(newX, graphNode.getParent().getSize().width - 30);
            newY = Math.max(newY, 3);
            newY = Math.min(newY, graphNode.getParent().getSize().height - 30);

            graphNode.setLocation(newX, newY);
            SwingUtilities.updateComponentTreeUI(graphNode.getParent());
            return;
        }

        NodeView nodeView = (NodeView) e.getSource();

        int newX = e.getX() + nodeView.getX() - 15;
        int newY = e.getY() + nodeView.getY() - 15;

        newX = Math.max(newX, 3);  //to prevent from going outside of graphPane
        newX = Math.min(newX, nodeView.getParent().getSize().width - 30);
        newY = Math.max(newY, 3);
        newY = Math.min(newY, nodeView.getParent().getSize().height - 30);

        nodeView.setLocation(newX, newY);
        nodeView.setX(newX);
        nodeView.setY(newY);

        for (EdgeView fromEdgeView : nodeView.getFromEdges()){

            fromEdgeView.setX1(newX + 15);
            fromEdgeView.setY1(newY + 15);
            fromEdgeView.updateSizeAndLocation();
            if(fromEdgeView.isHasPair()) {
                fromEdgeView.setX2(fromEdgeView.getDestinationNode().getX() + 15);
                fromEdgeView.setY2(fromEdgeView.getDestinationNode().getY() + 15);
                fromEdgeView.setHasPair(true);
            }

        }

        for (EdgeView toEdgeView : nodeView.getToEdges()){

            toEdgeView.setX2(newX + 15);
            toEdgeView.setY2(newY + 15);
            toEdgeView.updateSizeAndLocation();
            if(toEdgeView.isHasPair()) {
                toEdgeView.setX1(toEdgeView.getSourceNode().getX() + 15);
                toEdgeView.setY1(toEdgeView.getSourceNode().getY() + 15);
                toEdgeView.setHasPair(true);
            }
          //  toEdge.repaint();

        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
