package project.treeModel;

import project.graphModel.EdgeModel;
import project.graphModel.NodeModel;

public class NodeTreeNode extends AbstractNode {

    private NodeTreeNode fromEdges;
    private NodeTreeNode toEdges;


    public NodeTreeNode(boolean allowsChildren, Object model) {
        super(allowsChildren, model);
        ((NodeModel) model).setNodeNode(this);
    }

    @Override
    public void setChildren() {

        NodeModel nodeModel = (NodeModel) getModel();

        for(EdgeModel edgeModel : nodeModel.getFromEdges()) {
            add(new EdgeTreeLeaf(false, edgeModel, true));
        }

        for(EdgeModel edgeModel : nodeModel.getToEdges())
            add(new EdgeTreeLeaf(false, edgeModel, false));
    }

    @Override
    public String toString() {
        return "Node " + ((NodeModel) getModel()).getNodeNumber();
    }
}
