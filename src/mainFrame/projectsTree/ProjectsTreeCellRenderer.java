package mainFrame.projectsTree;

import project.treeModel.*;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class ProjectsTreeCellRenderer extends DefaultTreeCellRenderer {

    public ProjectsTreeCellRenderer() {
        setBackgroundNonSelectionColor(new Color(225, 225, 255));
        setBackgroundSelectionColor(new Color(130, 130, 255));
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {


        super.getTreeCellRendererComponent(tree, value, selected,
                expanded, leaf, row,
                hasFocus);

        if (value instanceof Workspace)
            setIcon(new ImageIcon(getClass().getResource("icons/workspaceTreeIcon.png")));
        else if (value instanceof ProjectNode)
            setIcon(new ImageIcon(getClass().getResource("icons/projectTreeIcon.png")));
        else if (value instanceof GraphTreeNode)
            setIcon(new ImageIcon(getClass().getResource("icons/graphTreeIcon.png")));
        else if (value instanceof NodeTreeNode)
            setIcon(new ImageIcon(getClass().getResource("icons/nodeTreeIcon.png")));
        else if (value instanceof EdgeTreeLeaf)
            setIcon(new ImageIcon(getClass().getResource("icons/edgeTreeIcon.png")));

        setText(value.toString());

        return this;
    }
}
