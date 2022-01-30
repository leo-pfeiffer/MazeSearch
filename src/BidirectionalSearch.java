import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BidirectionalSearch extends GeneralSearch {

    private final Search fSearch;
    private final Search bSearch;
    private final Node goalNode;

    public BidirectionalSearch(Map map, Coord start, Coord goal) {
        super(map, start, goal);
        goalNode = new Node(null, new State(goal, map), 0);
        startNode = new Node(null, startState, 0);
        fSearch = new BreadthFirstSearch(map, start, goal);
        bSearch = new BreadthFirstSearch(map, goal, start);
    }

    public BidirectionalSearch(Map map, Coord start, Coord goal, Search secondarySearch) {
        super(map, start, goal);
        goalNode = new Node(null, new State(goal, map), 0);
        startNode = new Node(null, startState, 0);
        fSearch = new BreadthFirstSearch(map, start, goal);
        bSearch = secondarySearch;
    }

    /**
     * Check if a node is invalid as a start node, i.e. is not on the map or is an island.
     * @param node the node to check
     * @return true if the node is invalid, false otherwise
     * */
    private boolean isInvalidStartNode(Node node) {
        Coord coord = node.getState().getCoord();
        return !map.isOnMap(coord) || map.isIsland(coord);
    }

    @Override
    public void runSearch() {

        if (isInvalidStartNode(startNode) || isInvalidStartNode(goalNode)) {
            printFailure();
            return;
        }

        fSearch.frontier.insert(startNode);
        bSearch.frontier.insert(goalNode);

        while (!fSearch.frontier.isEmpty() && !bSearch.frontier.isEmpty()) {
            if (searchIteration()) return;
        }
        printFailure();
    }

    @Override
    public boolean searchIteration() {

        fSearch.frontier.print();
        bSearch.frontier.print();
        System.out.println("--------------");

        if (frontierIntersectionCheck()) return true;
        
        Node fNode = fSearch.frontier.remove();
        Node bNode = bSearch.frontier.remove();

        fSearch.explored.add(fNode);
        bSearch.explored.add(bNode);

        if (terminationCheck(fNode, bNode)) return true;

        Node[] newFNodes = expand(fNode);
        Node[] newBNodes = expand(bNode);

        insertAll(newFNodes, fSearch.frontier, fSearch.explored);
        insertAll(newBNodes, bSearch.frontier, bSearch.explored);
        return false;
    }

    /**
     * Check if the goal has been reached on either the forward or backward search.
     * @param fNode the node on the forward search
     * @param bNode the node on the backward search
     * @return true if the goal has been reached, false otherwise
     * */
    public boolean terminationCheck(Node fNode, Node bNode) {
        if (fSearch.goalTest(fNode)) {
            // forward search found the goal on its own
            solution = fNode;
            printSuccess();
            return true;
        } else if (bSearch.goalTest(bNode)) {
            // backward search found the goal on its own
            // need to reverse the path first...
            solution = constructPath(startNode, bNode);
            printSuccess();
            return true;
        }
        return false;
    }

    /**
     * Test if the frontiers of the forward and backward searches intersect.
     * @return true if the frontiers intersect, false otherwise
     * */
    public boolean frontierIntersectionCheck() {
        // check for intersection of search frontier
        Set<Node> frontierIntersection = new HashSet<>(fSearch.frontier.toSet());
        frontierIntersection.retainAll(bSearch.frontier.toSet());
        if (frontierIntersection.size() > 0) {
            // frontiers of both searches intersect
            Iterator<Node> frontierIntersectionIterator = frontierIntersection.iterator();
            Node intersectionNode = frontierIntersectionIterator.next();

            Node fIntersectionNode = fSearch.frontier.getNode(intersectionNode);
            Node bIntersectionNode = bSearch.frontier.getNode(intersectionNode);

            solution = constructPath(fIntersectionNode, bIntersectionNode);
            printSuccess();
            return true;
        }
        return false;
    }

    /**
     * Construct the full path from start to goal by following the parent links
     * from the forward and backward searches.
     * @param fNode: the node from the forward search
     * @param bNode: the node from the backward search
     * @return the full path from start to goal
     * */
    public Node constructPath(Node fNode, Node bNode) {
        bNode = bNode.getParent();
        // traverse the path backwards adding to fNode's path
        while (bNode.getParent() != null) {
            double newCost = fNode.getCost() + bNode.getCost() - bNode.getParent().getCost();
            fNode = new Node(fNode, bNode.getState(), newCost);
            bNode = bNode.getParent();
        }
        // make one more step to get to the end
        fNode = new Node(fNode, bNode.getState(), fNode.getCost() + 1);
        return fNode;
    }
}
