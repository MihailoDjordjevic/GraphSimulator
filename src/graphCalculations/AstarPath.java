package graphCalculations;

import lombok.Getter;
import lombok.Setter;
import project.graphModel.EdgeModel;
import project.graphModel.NodeModel;

import java.util.ArrayList;

@Getter
@Setter
public class AstarPath {

    private NodeModel topNode;
    private ArrayList<NodeModel> nodes;
    private ArrayList<EdgeModel> edges;
    private int pathCost = 0;

    public AstarPath() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void calculatePathCost(){

        int cost = 0;

        for (EdgeModel edgeModel : edges)
            cost += edgeModel.getFlow();

        cost += topNode.getNodeNumber();

        pathCost = cost;

    }
}
