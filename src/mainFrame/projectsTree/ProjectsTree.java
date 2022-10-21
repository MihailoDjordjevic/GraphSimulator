package mainFrame.projectsTree;

import project.treeModel.ProjectNode;
import project.treeModel.Workspace;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import java.awt.*;

public class ProjectsTree extends JTree {

    private static ProjectsTree projectsTree;

    public static ProjectsTree getInstance() {
        if (projectsTree == null)
            projectsTree = new ProjectsTree(Workspace.getInstance());
        return projectsTree;
    }

    public ProjectsTree(TreeNode root) {
        super(root);
        
        setModel(new DefaultTreeModel(root));

        setPreferredSize(new Dimension(195, 300));
        setBackground(new Color(225, 225, 255));
        setCellRenderer(new ProjectsTreeCellRenderer());
    }

    public void updateHeight(boolean signumIndicator, int size){
        int dx = -25;
        if (signumIndicator) dx *= -1;
        setPreferredSize(new Dimension(ProjectsTree.getInstance().getWidth(), ProjectsTree.getInstance().getPreferredSize().height + dx*size));
    }

}
