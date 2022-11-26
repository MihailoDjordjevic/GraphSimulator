package project.treeModel;

import observer.NotificationType;
import project.graphModel.EdgeModel;

public class EdgeTreeLeaf extends AbstractNode {

    public EdgeTreeLeaf(boolean allowsChildren, Object model, boolean isSource) {
        super(allowsChildren, model);
        if (isSource) ((EdgeModel) model).setSourceEdgeLeaf(this);
        else ((EdgeModel) model).setDestinationEdgeLeaf(this);
    }

    @Override
    public String toString() {
        return "From:" + ((EdgeModel) getModel()).getSourceNode().getNodeNumber() + " to:" +
                ((EdgeModel) getModel()).getDestinationNode().getNodeNumber();
    }

    @Override
    public void update(Object notification, NotificationType notificationType) {

    }
}
