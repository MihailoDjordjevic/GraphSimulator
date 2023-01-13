package project.graphModel;

import observer.IPublisher;
import observer.ISubscriber;
import observer.NotificationType;
import project.Project;
import project.treeModel.GraphTreeNode;

import java.io.IOException;
import java.util.ArrayList;

public class GraphModel implements IPublisher {

    private ArrayList<ISubscriber> subscribers;

    private int nodeNumberGenerator = 0;

    private ArrayList<NodeModel> Nodes;
    private ArrayList<EdgeModel> Edges;
    private ArrayList<NodeModel> MinimumCutS;
    private ArrayList<NodeModel> MinimumCutV;

    private Project project;
    private GraphTreeNode graphNode;

    private int maxFlow;

    public GraphModel() {
        init();
    }

    private void init(){
        Nodes = new ArrayList<>(10);
        Edges = new ArrayList<>(20);
        subscribers = new ArrayList<>();
    }

    public ArrayList<NodeModel> getNodes() {
        return Nodes;
    }

    public ArrayList<EdgeModel> getEdges() {
        return Edges;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getNodeNumberGenerator() {
        return ++nodeNumberGenerator;
    }

    public void setNodeNumberGenerator(int nodeNumberGenerator) {
        this.nodeNumberGenerator = nodeNumberGenerator;
    }

    public GraphTreeNode getGraphNode() {
        return graphNode;
    }

    public void setGraphNode(GraphTreeNode graphNode) {
        this.graphNode = graphNode;
    }

    public void addNode(){

        NodeModel nodeModel = new NodeModel(this);
        notifySubscribers(nodeModel, NotificationType.ADD);

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
