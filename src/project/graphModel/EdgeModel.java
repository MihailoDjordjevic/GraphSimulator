package project.graphModel;

import graphView.graphEdge.EdgeView;
import project.treeModel.EdgeTreeLeaf;

public class EdgeModel {

    private EdgeView edgeViewPane;
    private EdgeTreeLeaf sourceEdgeLeaf;
    private EdgeTreeLeaf DestinationEdgeLeaf;

    private NodeModel sourceNode;
    private NodeModel destinationNode;
    private int capacity;
    private int flow;

    public EdgeModel(NodeModel sourceNode, NodeModel destinationNode, int capacity, int flow) {
        this.sourceNode = sourceNode;
        this.destinationNode = destinationNode;
        this.capacity = capacity;
        this.flow = flow;
    }

    public NodeModel getSourceNode() {
        return sourceNode;
    }

    public void setSourceNode(NodeModel sourceNode) {
        this.sourceNode = sourceNode;
    }

    public NodeModel getDestinationNode() {
        return destinationNode;
    }

    public void setDestinationNode(NodeModel destinationNode) {
        this.destinationNode = destinationNode;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    public EdgeView getEdgePane() {
        return edgeViewPane;
    }

    public void setEdgePane(EdgeView edgeViewPane) {
        this.edgeViewPane = edgeViewPane;
    }

    public EdgeTreeLeaf getSourceEdgeLeaf() {
        return sourceEdgeLeaf;
    }

    public void setSourceEdgeLeaf(EdgeTreeLeaf sourceEdgeLeaf) {
        this.sourceEdgeLeaf = sourceEdgeLeaf;
    }

    public EdgeTreeLeaf getDestinationEdgeLeaf() {
        return DestinationEdgeLeaf;
    }

    public void setDestinationEdgeLeaf(EdgeTreeLeaf destinationEdgeLeaf) {
        DestinationEdgeLeaf = destinationEdgeLeaf;
    }
}
