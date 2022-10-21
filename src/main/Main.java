package main;

import graphView.graphNode.NodeView;
import graphView.graphPane.GraphPane;
import mainFrame.MainFrame;
import mainFrame.tabbedPane.TabbedPane;

import java.io.PrintStream;
import java.util.zip.DeflaterOutputStream;

public class Main {

    public static NodeView source = null;
    public static NodeView destination = null;

    public static void main(String[] args) {

        MainFrame mainFrame = MainFrame.getMainFrame();
        mainFrame.setVisible(true);

//        for (int i = 1; i < 2000; i++){
//            GraphPane.addNode(TabbedPane.getInstance().getSelectedGraphPane(), 1+i*30%1200, i/40 * 30);        }
       // mainFrame.add(new JPanel().add(button), BorderLayout.EAST);


//        NodeModel node1 = new NodeModel();
//        NodeModel node2 = new NodeModel();
//        NodeModel node3 = new NodeModel();
//        NodeModel node4 = new NodeModel();
//        NodeModel node5 = new NodeModel();
//
//        ArrayList<NodeModel> nodes = new ArrayList<>();
//        Collections.addAll(nodes, node1, node2, node3, node4, node5);
//
//        EdgeModel edge12 = new EdgeModel(node1, node2, 2, 2);
//        EdgeModel edge13 = new EdgeModel(node1, node3, 2, 2);
//        EdgeModel edge24 = new EdgeModel(node2, node4, 2, 2);
//        EdgeModel edge45 = new EdgeModel(node4, node5, 2, 2);
//        EdgeModel edge35 = new EdgeModel(node3, node5, 2, 2);
//
//        ArrayList<EdgeModel> edges = new ArrayList<>();
//        Collections.addAll(edges, edge12, edge13, edge24, edge45);
//
//        node1.getFromEdges().add(edge12); node2.getToEdges().add(edge12);
//        node1.getFromEdges().add(edge13); node3.getToEdges().add(edge13);
//        node4.getFromEdges().add(edge24); node2.getToEdges().add(edge24);
//        node4.getFromEdges().add(edge45); node5.getToEdges().add(edge45);
//        node3.getFromEdges().add(edge35); node5.getToEdges().add(edge35);
//
//        PathFinder.findPath(node1, node4, nodes, edges);
    }
}
