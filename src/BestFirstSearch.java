public class BestFirstSearch extends InformedSearch {

    public BestFirstSearch(Map map, Coord start, Coord goal) {
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
        node.setHCost(hCost);
        node.setFCost(hCost);
    }
}
