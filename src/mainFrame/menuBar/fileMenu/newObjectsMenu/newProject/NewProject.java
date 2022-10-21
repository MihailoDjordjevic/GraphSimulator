package mainFrame.menuBar.fileMenu.newObjectsMenu.newProject;

import project.graphModel.GraphModel;
import graphView.graphPane.GraphPane;
import mainFrame.ActionTemplate;
import mainFrame.projectsTree.ProjectsTree;
import mainFrame.tabbedPane.TabbedPane;
import project.Project;
import project.treeModel.ProjectNode;
import project.treeModel.Workspace;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewProject extends ActionTemplate {

    public NewProject() {
        putValue(SMALL_ICON, loadIcon("icons/newProjectIcon.png"));
        putValue(NAME, "New Project");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) { 
        Project newProject = new Project();

        GraphModel graphModel = new GraphModel();
        GraphPane graphPane = new GraphPane();

        graphPane.setGraph(graphModel);
        newProject.setGraph(graphModel);
        graphModel.setProject(newProject);

        JScrollPane graphPaneScrollPane =  new JScrollPane(graphPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        graphPaneScrollPane.setName("Frfr");

        ProjectNode projectNode = new ProjectNode(true, newProject);
        newProject.setProjectNode(projectNode);

        Workspace.getInstance().add(projectNode);
        TabbedPane.getInstance().add(graphPaneScrollPane);

        ProjectsTree.getInstance().updateHeight(true, 1);

        SwingUtilities.updateComponentTreeUI(ProjectsTree.getInstance());
        SwingUtilities.updateComponentTreeUI(TabbedPane.getInstance());
    }

}
