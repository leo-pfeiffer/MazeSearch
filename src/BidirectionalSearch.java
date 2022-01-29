import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

// TODO TEST!!!
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

    @Override
    public void runSearch() {

        fSearch.frontier.insert(startNode);
        bSearch.frontier.insert(goalNode);

        while (!fSearch.frontier.isEmpty() || !bSearch.frontier.isEmpty()) {
            if (searchIteration()) return;
        }
        printFailure();
    }

    public boolean searchIteration() {

        fSearch.frontier.print();
        bSearch.frontier.print();
        System.out.println("--------------");
        
        Node fNode = fSearch.frontier.remove();
        Node bNode = bSearch.frontier.remove();

        explored.add(fNode);
        explored.add(bNode);

        if (terminationCheck(fNode, bNode)) return true;

        Node[] newFNodes = expand(fNode);
        Node[] newBNodes = expand(bNode);

        insertAll(newFNodes, fSearch.frontier);
        insertAll(newBNodes, bSearch.frontier);
        return false;
    }

    public boolean terminationCheck(Node fNode, Node bNode) {
        if (fSearch.goalTest(fNode)) {
            // forward search found the goal on its own
            solution = fNode;
            printSuccess();
            return true;
        } else if (bSearch.goalTest(bNode)) {
            // backward search found the goal on its own
            solution = bNode;
            printSuccess();
            return true;
        }

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
