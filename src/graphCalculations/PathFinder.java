package graphCalculations;

import mainFrame.MainFrame;
import project.graphModel.EdgeModel;
import project.graphModel.NodeModel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.*;

public class PathFinder {

    private static NodeModel destinationNode;

    public static void findPath(NodeModel sourceNode, NodeModel destinationNode, ArrayList<NodeModel> nodes, ArrayList<EdgeModel> edges){

        ArrayList<NodeModel> visitedNodes = new ArrayList<>();
        LinkedList<NodeModel> currPath = new LinkedList<>();  currPath.add(sourceNode);
        LinkedList<NodeModel> solution = new LinkedList<>();

        PathFinder.destinationNode = destinationNode;
        //dfsPath(sourceNode, currPath, visitedNodes, solution);
        bfsPath(currPath, visitedNodes);

        for(NodeModel nm : solution)
            System.out.print(nm.getNodeNumber() + "->");
        if (solution.isEmpty()) System.out.println("No way bro");
    }

    private static void dfsPath(NodeModel currNode, LinkedList<NodeModel> currPath, ArrayList<NodeModel> visitedNodes, LinkedList<NodeModel> solution){

        if (!solution.isEmpty()) return;

        currNode.getNodePane().setBorder(new LineBorder(Color.green, 3));
        SwingUtilities.updateComponentTreeUI(currNode.getNodePane());
        System.out.println(currNode.getNodeNumber() + " exploring");

        try {
            Thread.sleep(950);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (currNode.equals(destinationNode)){
            currPath.add(currNode);
            solution.addAll(currPath);

            currNode.getNodePane().setBorder(null);
            SwingUtilities.updateComponentTreeUI(currNode.getNodePane());

            return;
        }

        if (visitedNodes.contains(currNode)) {
            if (!currPath.contains(currNode))
                currNode.getNodePane().setBorder(null);
            return;
        }
        else {
            visitedNodes.add(currNode);
            currPath.add(currNode);
        }

        for (EdgeModel e : currNode.getFromEdges()){
            dfsPath(e.getDestinationNode(), currPath, visitedNodes, solution);
        }

        currNode.getNodePane().setBorder(null);
        SwingUtilities.updateComponentTreeUI(currNode.getNodePane());

        currPath.removeLast();

        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void bfsPath(LinkedList<NodeModel> stack, ArrayList<NodeModel> visitedNodes){

        if (stack.isEmpty()) return;

        Random r = new Random();
        Color c = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));

        for (NodeModel nodeModel : stack){

            System.out.println(nodeModel.getNodeNumber() + " exploring");

            visitedNodes.add(nodeModel);
            nodeModel.getNodePane().setBorder(new LineBorder(c, 3));
            SwingUtilities.updateComponentTreeUI(nodeModel.getNodePane());

        }

        LinkedList<NodeModel> newStack = new LinkedList<>();

        for (NodeModel nodeModel : stack)
            for (EdgeModel edgeModel : nodeModel.getFromEdges())
                if (!visitedNodes.contains(edgeModel.getDestinationNode())) {
                    newStack.add(edgeModel.getDestinationNode());
                    visitedNodes.add(edgeModel.getDestinationNode());
                }

        try {
            Thread.sleep(1050);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        bfsPath(newStack, visitedNodes);


        for (NodeModel nodeModel : stack){

            nodeModel.getNodePane().setBorder(null);
            SwingUtilities.updateComponentTreeUI(nodeModel.getNodePane());

        }

    }
}
