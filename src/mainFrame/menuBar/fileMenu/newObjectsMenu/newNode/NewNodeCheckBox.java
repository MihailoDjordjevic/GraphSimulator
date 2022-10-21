package mainFrame.menuBar.fileMenu.newObjectsMenu.newNode;

import graphView.graphNode.NodeView;
import project.graphModel.NodeModel;

import javax.swing.*;

public class NewNodeCheckBox extends JCheckBox{

    private NodeModel nodeModel;
    private NodeView nodeView;

    public NewNodeCheckBox(NodeView nodeView) {
        super(nodeView.getNodeModel().toString());
        this.nodeView = nodeView;
        this.nodeModel = nodeView.getNodeModel();
        setName(nodeModel.toString());
    }

    @Override
    public String toString() {
        return nodeModel.toString();
    }

    public NodeModel getNodeModel() {
        return nodeModel;
    }

    public void setNodeModel(NodeModel nodeModel) {
        this.nodeModel = nodeModel;
    }

    public NodeView getNodeView() {
        return nodeView;
    }

    public void setNodeView(NodeView nodeView) {
        this.nodeView = nodeView;
    }
}
