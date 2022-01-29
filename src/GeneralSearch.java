import java.util.ArrayList;

/**
 * Abstract class providing the behaviour of the general search algorithm.
 * Subclasses of the GeneralSearch must define a Frontier:
 *        - a StackFrontier for depth-first search
 *        - a QueueFrontier for breadth-first search
 * */
public abstract class GeneralSearch extends Search {

    public GeneralSearch(Map map, Coord start, Coord goal) {
        super(map, start, goal);
    }

    @Override
    public void runSearch() {

        // create initial node and add to frontier
        frontier.insert(startNode);

        // traverse tree until goal is found or frontier is empty
        while (!frontier.isEmpty()) {
            if (searchIteration()) return;
        }
        printFailure();
    }

    public boolean searchIteration() {
        frontier.print();

        // explore next node from the frontier
        Node node = frontier.remove();
        explored.add(node);

        // check the termination condition
        if (terminationCheck(node)) return true;

        // expand on current node
        Node[] newNodes = expand(node);
        insertAll(newNodes);
        return false;
    }

    public boolean terminationCheck(Node node) {
        // check if goal is reached
        if (goalTest(node)) {
            solution = node;
            printSuccess();
            return true;
        }
        return false;
    }

    /**
     * Performs the expand operation on the given node by creating and array of successor
     * nodes that have not been explored yet.
     * @param node Node to expand
     * @return Array of successor nodes
     * */
    public Node[] expand(Node node) {
        State[] successorStates = node.getState().getSuccessorStates();
        ArrayList<Node> newNodes = new ArrayList<>();
        for (State successorState : successorStates) {
            // create new node from successor state with current node as parent, successorState as state,
            // and cost incremented by 1
            Node newNode = new Node(node, successorState, node.getCost() + 1);
            addToSuccessors(newNode, newNodes);
        }
        Node[] newNodesArray = new Node[newNodes.size()];
        newNodes.toArray(newNodesArray);
        return newNodesArray;
    }

    protected void addToSuccessors(Node node, ArrayList<Node> newNodes) {
        if (!explored.contains(node)) {
            newNodes.add(node);
        }
    }

    /**
     * Inserts all nodes in the given array into the frontier. Since the frontier wraps a set,
     * nodes that are already in the set will not be added again.
     * @param nodes Array of nodes to insert
     * */
    public void insertAll(Node[] nodes) {
        for (Node node : nodes) {
            this.frontier.insert(node);
        }
    }

    public void insertAll(Node[] nodes, Frontier frontier) {
        for (Node node : nodes) {
            frontier.insert(node);
        }
    }
}
