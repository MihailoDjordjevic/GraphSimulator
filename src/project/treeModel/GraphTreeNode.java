package project.treeModel;

import observer.NotificationType;
import project.graphModel.GraphModel;
import project.graphModel.NodeModel;

import javax.swing.tree.MutableTreeNode;

public class GraphTreeNode extends AbstractNode {

    private String name;

    public GraphTreeNode(boolean allowsChildren, Object model) {
        super(allowsChildren, model);
        ((GraphModel) model).setGraphNode(this);
        setChildren();
        ((GraphModel) model).addSubscriber(this);
    }

    @Override
    public void setChildren() {
        GraphModel graphModel = (GraphModel) getModel();

        for (NodeModel nodeModel : graphModel.getNodes()) {
            NodeTreeNode nodeNode = new NodeTreeNode(true, nodeModel);
            nodeNode.setChildren();
            add(nodeNode);
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public void update(Object notification, NotificationType notificationType) {
        switch (notificationType){
            case ADD: add((MutableTreeNode) notification);
        }
    }
}
