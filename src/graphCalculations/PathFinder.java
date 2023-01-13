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
        //bfsPath(currPath, visitedNodes);

        AstarPath startPath = new AstarPath();
        startPath.setTopNode(sourceNode);
        startPath.getNodes().add(sourceNode);

        ArrayList<AstarPath> paths = new ArrayList<>();
        paths.add(startPath);

        visitedNodes.add(sourceNode);

        aStarPath(paths, visitedNodes, solution);

        for(NodeModel nm : solution)
            System.out.print(nm.getNodeNumber() + "->");
        if (solution.isEmpty()) System.out.println("No way bro");

        for (NodeModel nodeModel: visitedNodes){
            nodeModel.getNodePane().setBorder(null);
            SwingUtilities.updateComponentTreeUI(nodeModel.getNodePane());
        }
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

    private static void aStarPath(ArrayList<AstarPath> paths, ArrayList<NodeModel> visitedNodes, LinkedList<NodeModel> solution){

        if (paths.isEmpty()) return;

        if (!solution.isEmpty()) return;

        if (paths.get(0).getTopNode() == PathFinder.destinationNode) {
            System.out.println("found!");
            solution.addAll(paths.get(0).getNodes());
            return;
        }

        System.out.println("exploring " + paths.get(0).getTopNode());

        doSleep();
        paths.get(0).getTopNode().getNodePane().setBorder(new LineBorder(Color.RED, 3));

        for (EdgeModel edgeModel : paths.get(0).getTopNode().getFromEdges()){

            doSleep();

            if (!visitedNodes.contains(edgeModel.getDestinationNode())){

                AstarPath newPath = new AstarPath();

                ArrayList<NodeModel> nodes = new ArrayList<>(paths.get(0).getNodes());
                nodes.add(edgeModel.getDestinationNode());

                ArrayList<EdgeModel> edges = new ArrayList<>(paths.get(0).getEdges());
                edges.add(edgeModel);

                newPath.setNodes(nodes);
                newPath.setEdges(edges);
                newPath.setTopNode(edgeModel.getDestinationNode());

                newPath.calculatePathCost();
                paths.add(newPath);

                edgeModel.getDestinationNode().getNodePane().setBorder(new LineBorder(Color.GREEN, 3));

            }
        }

        visitedNodes.add(paths.get(0).getTopNode());

        paths.remove(0);
        paths.sort(Comparator.comparingInt(AstarPath::getPathCost));

        aStarPath(paths, visitedNodes, solution);

    }

    public static void doSleep(){

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
