package graphView.graphNode;

import project.graphModel.NodeModel;
import graphView.graphEdge.EdgeView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class NodeView extends JPanel {

    private JLabel nodeNumberLabel;
    private int x;
    private int y;
    private NodeModel nodeModel;

    private ArrayList<EdgeView> fromEdgeViews;
    private ArrayList<EdgeView> toEdgeViews;

    public NodeView(NodeModel nodeModel, int x, int y) {
        this.nodeModel = nodeModel;
        this.x = x;
        this.y = y;

        init();
    }

    private void init(){

        setBounds(x, y, 30, 30);
        setBackground(Color.PINK);
        setOpaque(true);

        nodeNumberLabel = new JLabel(String.valueOf(nodeModel.getNodeNumber()));
        nodeNumberLabel.setSize(25,25);
        add(nodeNumberLabel);

        fromEdgeViews = new ArrayList<>();
        toEdgeViews = new ArrayList<>();

        addMouseListener(new GraphNodeMouseListener());
        addMouseMotionListener(new GraohNodeMouseMotionListener());
    }

    public NodeModel getNodeModel() {
        return nodeModel;
    }

    public JLabel getNodeNumberLabel() {
        return nodeNumberLabel;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public ArrayList<EdgeView> getFromEdges() {
        return fromEdgeViews;
    }

    public ArrayList<EdgeView> getToEdges() {
        return toEdgeViews;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
