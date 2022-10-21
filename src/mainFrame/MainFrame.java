package mainFrame;

import project.graphModel.GraphModel;
import graphView.graphPane.GraphPane;
import mainFrame.menuBar.MenuBar;
import mainFrame.projectsTree.ProjectsTree;
import mainFrame.tabbedPane.TabbedPane;
import mainFrame.toolbar.Toolbar;
import project.Project;
import project.treeModel.ProjectNode;
import project.treeModel.Workspace;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JScrollPane graphPaneScrollPane;
    private ProjectsTree projectsTree;
    private JScrollPane projectsTreeScrollPane;
    private Toolbar toolbar;
    private TabbedPane tabbedPane;
    JSplitPane jSplitPane;

    private static MainFrame mainFrame;

    public static MainFrame getMainFrame(){

        if(mainFrame == null) {
            mainFrame = new MainFrame("Graph Calculator");
        }
        return mainFrame;

    }

    public MainFrame(String title) {
        super(title);
        init();
    }

    private void init(){

        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setLocation(0, 0);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        toolbar = Toolbar.getToolbar();
        add(toolbar, BorderLayout.NORTH);

        setJMenuBar(MenuBar.getMenuBar());

        GraphPane graphPane = new GraphPane();
        graphPane.setGraph(new GraphModel());

        graphPaneScrollPane = new JScrollPane(graphPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        graphPaneScrollPane.setName("Starting pane");

        tabbedPane = TabbedPane.getInstance();
        tabbedPane.add(graphPaneScrollPane);

        Project project = new Project();
        project.setGraph(graphPane.getGraph());
        graphPane.getGraph().setProject(project);
        project.setProjectNode(new ProjectNode(true, project));

       // Workspace.getInstance().add(project.getProjectNode());

        projectsTree = ProjectsTree.getInstance();
        projectsTreeScrollPane = new JScrollPane(projectsTree,  JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, projectsTreeScrollPane, tabbedPane);
        jSplitPane.setDividerLocation(160);
        jSplitPane.setDividerSize(3);
        add(jSplitPane, BorderLayout.CENTER);
    }

}

//    Project project = new Project();
//        project.setGraph(graphPane.getGraph());
//
//                graphPane.getGraph().getNodes().add(new NodeModel());
//                graphPane.getGraph().getNodes().add(new NodeModel());
//                graphPane.getGraph().getNodes().add(new NodeModel());
//                graphPane.getGraph().getEdges().add(new EdgeModel(graphPane.getGraph().getNodes().get(0), graphPane.getGraph().getNodes().get(1), 12, 34));
//                graphPane.getGraph().getEdges().add(new EdgeModel(graphPane.getGraph().getNodes().get(2), graphPane.getGraph().getNodes().get(1), 12, 34));
//                graphPane.getGraph().getEdges().add(new EdgeModel(graphPane.getGraph().getNodes().get(0), graphPane.getGraph().getNodes().get(2), 12, 34));
//
//                graphPane.getGraph().getNodes().get(0).getFromEdges().add(graphPane.getGraph().getEdges().get(0));
//                graphPane.getGraph().getNodes().get(1).getToEdges().add(graphPane.getGraph().getEdges().get(0));
