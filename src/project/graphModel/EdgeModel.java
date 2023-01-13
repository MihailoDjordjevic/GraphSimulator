package project.graphModel;

import graphView.graphEdge.EdgeView;
import observer.IPublisher;
import observer.ISubscriber;
import observer.NotificationType;
import project.treeModel.EdgeTreeLeaf;

import java.io.IOException;
import java.util.ArrayList;

public class EdgeModel implements IPublisher {

    private ArrayList<ISubscriber> subscribers;

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
        subscribers = new ArrayList<>();
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

    @Override
    public void addSubscriber(ISubscriber sub) {
        subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Object notification, NotificationType notificationType) {
        for(ISubscriber subscriber : subscribers)
            subscriber.update(notification, notificationType);
    }
}
