package graphView.graphNode;

import graphCalculations.PathFinder;
import org.w3c.dom.Node;
import project.graphModel.EdgeModel;
import project.graphModel.NodeModel;
import graphView.graphEdge.EdgeView;
import graphView.graphPane.GraphPane;
import graphView.graphPane.ToolbarState;
import main.Main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GraphNodeMouseListener implements MouseListener {

    private static NodeView sourceNode = null;

    @Override
    public void mouseClicked(MouseEvent e) {

        switch (ToolbarState.toolbarstate){

            case NEW:{
                if (e.getClickCount() == 1 && e.getButton() == 1) GraphPane.addEdge((NodeView) e.getSource());
            } break;
            case DELETE:{
                GraphPane.deleteNode(e);
            } break;
            case SELECT:{

                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        selectST((NodeView) e.getSource(), (GraphPane) ((NodeView) e.getSource()).getParent());
                    }
                });

                t.start();


                if (e.getClickCount() == 1 && e.getButton() == 1) return;
            } break;
            case TWOWAYEDGE:{
                if (e.getClickCount() == 1 && e.getButton() == 1) {
                    NodeView nodeView = GraphPane.getSourceNode();
                    GraphPane.addEdge((NodeView) e.getSource());
                    GraphPane.setSourceNode((NodeView) e.getSource());
                    if (nodeView != null) GraphPane.addEdge(nodeView);
                }
            } break;

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void selectST(NodeView node, GraphPane graphPane){

        if (Main.source == null) Main.source = node;
        else {
            Main.destination = node;

            ArrayList<NodeModel> Nodes = new ArrayList<>();
            ArrayList<EdgeModel> Edges = new ArrayList<>();

            for (NodeView gn : graphPane.getNodes())
                Nodes.add(gn.getNodeModel());

            for (EdgeView ed : graphPane.getEdges())
                Edges.add(ed.getEdgeModel());

            PathFinder.findPath(Main.source.getNodeModel(), Main.destination.getNodeModel(), Nodes, Edges);

            Main.source = null;
            Main.destination = null;
        }

    }

}

