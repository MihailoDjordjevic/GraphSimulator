package project.treeModel;

import jdk.nashorn.api.tree.Tree;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Vector;

public abstract class AbstractNode extends DefaultMutableTreeNode {

    private Object model;

    public AbstractNode(boolean allowsChildren, Object model) {
        setAllowsChildren(allowsChildren);
        this.model = model;
    }

    public void setChildren(){
    }

    public Object getModel() {
        return model;
    }

    public Vector<TreeNode> getChildren(){
        return children;
    }
}
