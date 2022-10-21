package project.treeModel;

import project.Project;

public class ProjectNode extends AbstractNode{

    private String name;

    public ProjectNode(boolean allowsChildren, Object model) {
        super(allowsChildren, model);
        setChildren();
    }

    @Override
    public void setChildren() {
        Project project = (Project) getModel();
        GraphTreeNode graphNode = new GraphTreeNode( true, project.getGraph());
        graphNode.setName("Graph");
        add(graphNode);
    }

    @Override
    public String toString() {
        return "Project";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
