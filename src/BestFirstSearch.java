public class BestFirstSearch extends GeneralSearch {

    public BestFirstSearch(Map map, Coord start, Coord goal) {
        super(map, start, goal);
        this.frontier = new PriorityQueueFrontier();
        this.startNode = new Node(null, startState, 0);
        calculateCost(startNode);
    }

    /**
     * Overrides the method in GeneralSearch since we also need to calculate the hCost and fCost.
     * */
    @Override
    public void insertAll(Node[] nodes) {
        for (Node node : nodes) {
            calculateCost(node);
            frontier.insert(node);
        }
    }

    /**
     * Calculate the hCost and fCost of the node.
     * */
    private void calculateCost(Node node) {
        Coord coord = node.getState().getCoord();
        double hCost = coord.getManhattanDistance(goal);
        node.setHCost(hCost);
        node.setFCost(hCost);
    }
}
