package project;

import project.graphModel.GraphModel;
import project.treeModel.ProjectNode;

public class Project {

    private GraphModel graphModel;
    private ProjectNode projectNode;

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
}
