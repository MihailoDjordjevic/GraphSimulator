package project;

import observer.IPublisher;
import observer.ISubscriber;
import observer.NotificationType;
import project.graphModel.GraphModel;
import project.treeModel.ProjectNode;

import java.io.IOException;
import java.util.ArrayList;

public class Project implements IPublisher {

    private ArrayList<ISubscriber> subscribers;

    private GraphModel graphModel;
    private ProjectNode projectNode;

    public Project() {
        subscribers = new ArrayList<>();
    }

    public ProjectNode getProjectNode() {
        return projectNode;
    }

    public void setProjectNode(ProjectNode projectNode) {
        this.projectNode = projectNode;
    }

    public GraphModel getGraph() {
        return graphModel;
    }

    public void setGraph(GraphModel graphModel) {
        this.graphModel = graphModel;
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
    public void notifySubscribers(Object notification, NotificationType notificationType) throws IOException {
        for(ISubscriber subscriber : subscribers)
            subscriber.update(notification, notificationType);
    }
}
