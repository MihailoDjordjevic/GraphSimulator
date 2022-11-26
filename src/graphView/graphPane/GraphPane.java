package graphView.graphPane;

import observer.ISubscriber;
import observer.NotificationType;
import project.graphModel.EdgeModel;
import project.graphModel.GraphModel;
import project.graphModel.NodeModel;
import graphView.graphEdge.EdgeView;
import graphView.graphNode.NodeView;
import mainFrame.projectsTree.ProjectsTree;
import project.Project;
import project.treeModel.EdgeTreeLeaf;
import project.treeModel.GraphTreeNode;
import project.treeModel.NodeTreeNode;
import project.treeModel.ProjectNode;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GraphPane extends JPanel implements ISubscriber {

    private static NodeView sourceNode = null;

    private GraphModel graphModel;
    private ArrayList<NodeView> nodes;
    private ArrayList<EdgeView> edgeViews;

    public GraphPane() {
        init();
    }

    private void init(){

        setLayout(null);
        setPreferredSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 250));
        setAutoscrolls(true);

        addMouseListener(new GraphPaneMouseListener());

        nodes = new ArrayList<>();
        edgeViews = new ArrayList<>();
    }

    public ArrayList<NodeView> getNodes() {
        return nodes;
    }

    public ArrayList<EdgeView> getEdges() {
        return edgeViews;
    }

    public GraphModel getGraph() {
        return graphModel;
    }

    public void setGraph(GraphModel graphModel) {
        this.graphModel = graphModel;
    }

    public static void addEdge(NodeView currNode){

        GraphPane graphPane = (GraphPane) currNode.getParent();

        if (currNode.equals(sourceNode)) {
            cancelSelection();
            return;
        }

        if (setSelection(currNode)) return;
        else {          //if there's already set source node

            if (!checkIfDuplicateEdge(sourceNode.getNodeModel(), currNode.getNodeModel())) {
                cancelSelection();
                return;
            }

            EdgeModel edgeModel = new EdgeModel(sourceNode.getNodeModel(), currNode.getNodeModel(), 23, 12);
            sourceNode.getNodeModel().getFromEdges().add(edgeModel);    //set edge model
            currNode.getNodeModel().getToEdges().add(edgeModel);

            //initiate new edge pane
            EdgeView edgeView = new EdgeView(edgeModel, sourceNode.getX() + 15, sourceNode.getY() + 15, currNode.getX() + 15, currNode.getY() + 15);
            checkIfHasPair(sourceNode.getNodeModel(), currNode.getNodeModel(), edgeView);  //change paint settings to paint a pair of edges

            sourceNode.getFromEdges().add(edgeView);
            currNode.getToEdges().add(edgeView);

            edgeView.setSourceNode(sourceNode);
            edgeView.setDestinationNode(currNode);

            graphPane.add(edgeView);
            graphPane.getEdges().add(edgeView);
            graphPane.getGraph().getEdges().add(edgeView.getEdgeModel());

            //add to projects tree
            GraphModel graphModel1 = graphPane.getGraph();
            Project project = graphModel1.getProject();
            ProjectNode projectNode = project.getProjectNode();

            GraphTreeNode graphNode = (GraphTreeNode) projectNode.getChildren().get(0);

            NodeTreeNode nodeNode = edgeModel.getSourceNode().getNodeNode();
            nodeNode.add(new EdgeTreeLeaf(false, edgeModel, true));
            nodeNode = edgeModel.getDestinationNode().getNodeNode();
            nodeNode.add(new EdgeTreeLeaf(false, edgeModel, false));

            ProjectsTree.getInstance().updateHeight(true, 2);

            cancelSelection();

            SwingUtilities.updateComponentTreeUI(graphPane);
            SwingUtilities.updateComponentTreeUI(ProjectsTree.getInstance());

            sourceNode = null;
        }
    }

    public static NodeView addNode(GraphPane currentGraphPane, int x, int y){

        NodeView newNode = new NodeView(new NodeModel(currentGraphPane.getGraph()), x, y);  newNode.getNodeModel().setNodePane(newNode);
        currentGraphPane.add(newNode);
        currentGraphPane.getNodes().add(newNode);
        currentGraphPane.getGraph().getNodes().add(newNode.getNodeModel());

        ProjectNode projectNode = currentGraphPane.getGraph().getProject().getProjectNode();

        NodeTreeNode nodeNode = new NodeTreeNode(true, newNode.getNodeModel());
        ((GraphTreeNode) projectNode.getChildren().get(0)).add(nodeNode);

        ProjectsTree.getInstance().updateHeight(true, 1);

        SwingUtilities.updateComponentTreeUI(currentGraphPane);
        SwingUtilities.updateComponentTreeUI(ProjectsTree.getInstance());

        return newNode;
    }

    public static void deleteNode(MouseEvent e){

        NodeView nodeView = (NodeView) e.getSource();
        GraphPane graphPane = (GraphPane) nodeView.getParent();

        if (nodeView.equals(sourceNode)) sourceNode = null;

        for (EdgeView ed : nodeView.getFromEdges()){
            ed.getDestinationNode().getToEdges().remove(ed);
            deleteEdge(ed, true);
        }

        for (EdgeView ed : nodeView.getToEdges()){
            ed.getSourceNode().getFromEdges().remove(ed);
            deleteEdge(ed, true);
        }

        //remove from graph pane
        ((GraphPane) nodeView.getParent()).getNodes().remove(nodeView);
        nodeView.getParent().remove(nodeView);
        nodeView.getToEdges().clear();
        nodeView.getFromEdges().clear();

        //remove from projects tree
        NodeTreeNode nodeNode = nodeView.getNodeModel().getNodeNode();
        GraphTreeNode graphNode = (GraphTreeNode) nodeNode.getParent();
        graphNode.getChildren().remove(nodeNode);

        //remove from model
        graphPane.getGraph().getNodes().remove(nodeView.getNodeModel());

        ProjectsTree.getInstance().updateHeight(false, 1);
        SwingUtilities.updateComponentTreeUI(graphPane);
        SwingUtilities.updateComponentTreeUI(ProjectsTree.getInstance());

    }

    public static void deleteEdge(Component e, boolean nodeFlag){  //flag is to prevent concurrent editing if this method is invoked from deleteNode

       // JLabel source = (JLabel) e.getSource();
        EdgeView edgeView = (EdgeView) e;// source.getParent();
        GraphPane graphPane = (GraphPane) edgeView.getParent();

        EdgeModel em = edgeView.getEdgeModel();
        em.getSourceNode().getFromEdges().remove(em);
        em.getDestinationNode().getToEdges().remove(em);

        if (!nodeFlag) {
            edgeView.getSourceNode().getFromEdges().remove(edgeView);
            edgeView.getDestinationNode().getToEdges().remove(edgeView);
        }

        graphPane.getEdges().remove(edgeView);
        graphPane.remove(edgeView);

        if(edgeView.isHasPair()) {
            edgeView.getPair().setHasPair(false);
            edgeView.getPair().setPair(null);
        }

        //delete from projects tree
        EdgeTreeLeaf edgeLeaf = edgeView.getEdgeModel().getDestinationEdgeLeaf();
        NodeTreeNode nodeNode = (NodeTreeNode) edgeLeaf.getParent();
        nodeNode.remove(edgeLeaf);

        edgeLeaf = edgeView.getEdgeModel().getSourceEdgeLeaf();
        nodeNode = (NodeTreeNode) edgeLeaf.getParent();
        nodeNode.remove(edgeLeaf);

        ProjectsTree.getInstance().updateHeight(false, 2);
        SwingUtilities.updateComponentTreeUI(ProjectsTree.getInstance());
        SwingUtilities.updateComponentTreeUI(graphPane);

    }

    private static void checkIfHasPair(NodeModel source, NodeModel destination, EdgeView newEdgeView){

        for (EdgeModel edgeModel : source.getToEdges()){
            if (edgeModel.getSourceNode().equals(destination)) {

                edgeModel.getEdgePane().setHasPair(true);
                edgeModel.getEdgePane().setPair(newEdgeView);
                newEdgeView.setHasPair(true);
                newEdgeView.setPair(edgeModel.getEdgePane());

                edgeModel.getEdgePane().updateSizeAndLocation();
                newEdgeView.updateSizeAndLocation();

                return;
            }
        }

    }

    private static boolean checkIfDuplicateEdge(NodeModel source, NodeModel destination){
        for (EdgeModel edgeModel : source.getFromEdges()){
            if (edgeModel.getDestinationNode().equals(destination)) return false;
        }
        return true;
    }

    public static void cancelSelection(){
        if (sourceNode != null){
            sourceNode.setBorder(null);
            sourceNode = null;
        }
    }

    public static boolean setSelection(NodeView nodeView){
        if (sourceNode == null) {
            sourceNode = nodeView;
            sourceNode.setBorder(new LineBorder(Color.green, 3));
            return true;
        } else if (!sourceNode.getParent().equals(nodeView.getParent())){  //in case selection of node is made in a different pane from the first one
            cancelSelection();
            sourceNode = nodeView;
            sourceNode.setBorder(new LineBorder(Color.green, 3));
            return true;
        } else return false;
    }

    public static NodeView getSourceNode() {
        return sourceNode;
    }

    public static void setSourceNode(NodeView sourceNode) {
        GraphPane.sourceNode = sourceNode;
    }

    @Override
    public void update(Object notification, NotificationType notificationType) {

    }
}
