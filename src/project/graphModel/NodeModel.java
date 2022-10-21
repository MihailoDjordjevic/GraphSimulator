package project.graphModel;

import graphView.graphNode.NodeView;
import project.treeModel.NodeTreeNode;

import java.util.ArrayList;

public class NodeModel {

    private GraphModel graphModel;
    private NodeTreeNode nodeNode;

    private NodeView nodePane;
    private int nodeNumber;
    private ArrayList<EdgeModel> fromEdges;
    private ArrayList<EdgeModel> toEdges;

    public NodeModel(GraphModel graphModel) {
        this.graphModel = graphModel;
        init();
    }

    private void init(){

        fromEdges = new ArrayList<>(5);
        toEdges = new ArrayList<>(5);
        nodeNumber = graphModel.getNodeNumberGenerator();

    }

    public int getNodeNumber() {
        return nodeNumber;
    }

    public void setNodeNumber(int nodeNumber) {
        this.nodeNumber = nodeNumber;
    }

    public ArrayList<EdgeModel> getFromEdges() {
        return fromEdges;
    }

    public void setFromEdges(ArrayList<EdgeModel> fromEdges) {
        this.fromEdges = fromEdges;
    }

    public ArrayList<EdgeModel> getToEdges() {
        return toEdges;
    }

    public void setToEdges(ArrayList<EdgeModel> toEdges) {
        this.toEdges = toEdges;
    }

    public NodeView getNodePane() {
        return nodePane;
    }

    public void setNodePane(NodeView nodePane) {
        this.nodePane = nodePane;
    }

    public NodeTreeNode getNodeNode() {
        return nodeNode;
    }

    public void setNodeNode(NodeTreeNode nodeNode) {
        this.nodeNode = nodeNode;
    }

    @Override
    public String toString() {
        return "Node " + getNodeNumber();
    }
}
