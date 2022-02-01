import java.util.ArrayList;
import java.util.Iterator;

public class AStarSearch extends InformedSearch {

    public AStarSearch(Map map, Coord start, Coord goal) {
        super(map, start, goal);
        this.frontier = new PriorityQueueFrontier();
        this.startNode = new Node(null, startState, 0);
        calculateCost(startNode);
    }

    /**
     * Calculate the hCost and fCost of the node.
     * */
    protected void calculateCost(Node node) {
        Coord coord = node.getState().getCoord();
        double hCost = coord.getManhattanDistance(goal);
        double fCost = hCost + node.getCost();
        node.setHCost(hCost);
        node.setFCost(fCost);
    }

    /**
     * In A* search, we need to check if node is contained with higher cost.
     * */
    @Override
    protected void addToSuccessors(Node node, ArrayList<Node> newNodes) {
        if (!explored.contains(node) && !frontier.contains(node)) {
            newNodes.add(node);
        } else {
            // need a new frontier to replace the old one with
            PriorityQueueFrontier newFrontier = new PriorityQueueFrontier();
            Node foundNode;

            // iterate over all nodes in the frontier
            Iterator<Node> it = frontier.iterator();
            while (it.hasNext()) {
                foundNode = it.next();

                if (foundNode.equals(node) && foundNode.getCost() > node.getCost()) {
                    // found the same node but with a higher cost
                    newFrontier.insert(node);
                }
                else {
                    // not the same node, just add it to the new frontier
                    newFrontier.insert(foundNode);
                }
            }
            // replace the old frontier with the new one
            this.frontier = newFrontier;
        }
    }
}
