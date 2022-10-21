package graphCalculations;

import project.graphModel.EdgeModel;
import project.graphModel.NodeModel;

import java.util.ArrayList;
import java.util.LinkedList;

public class PathFinder {

    private static NodeModel destinationNode;

    public static void findPath(NodeModel sourceNode, NodeModel destinationNode, ArrayList<NodeModel> nodes, ArrayList<EdgeModel> edges){

        ArrayList<NodeModel> visitedNodes = new ArrayList<>();
        LinkedList<NodeModel> currPath = new LinkedList<>();
        LinkedList<NodeModel> solution = new LinkedList<>();

        PathFinder.destinationNode = destinationNode;

        dfsPath(sourceNode, currPath, visitedNodes, solution);

        for(NodeModel nm : solution)
            System.out.print(nm.getNodeNumber() + "->");
        if (solution.isEmpty()) System.out.println("No way bro");
    }

    private static void dfsPath(NodeModel currNode, LinkedList<NodeModel> currPath, ArrayList<NodeModel> visitedNodes, LinkedList<NodeModel> solution){

        if (!solution.isEmpty()) return;

        if (currNode.equals(destinationNode)){
            currPath.add(currNode);
            solution.addAll(currPath);
            return;
        }

        if (visitedNodes.contains(currNode))
            return;
        else {
            visitedNodes.add(currNode);
            currPath.add(currNode);
        }

        for (EdgeModel e : currNode.getFromEdges()){
            dfsPath(e.getDestinationNode(), currPath, visitedNodes, solution);
        }

        currPath.removeLast();
    }
}
