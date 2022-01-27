import java.util.ArrayList;

public abstract class GeneralSearch extends Search {

    public GeneralSearch(Map map, Coord start, Coord goal) {
        super(map, start, goal);
    }

    @Override
    public void runSearch() {

        // create initial node and add to frontier
        Node node = new Node(null, startState, 0);
        frontier.insert(node);

        // traverse tree until goal is found or frontier is empty
        while (!frontier.isEmpty()) {

            node = frontier.remove();
            explored.add(node);

            if (goalTest(node.getState())) {
                solution = node;
                return;
            }

            Node[] newNodes = expand(node);
            insertAll(newNodes);
        }
    }

    public Node[] expand(Node node) {
        State[] successorStates = node.getState().getSuccessorStates();
        ArrayList<Node> newNodes = new ArrayList<>();
        for (State successorState : successorStates) {
            Node newNode = new Node(node, successorState, 0);
            if (!explored.contains(newNode)) {
                newNodes.add(newNode);
            }
        }
        Node[] newNodesArray = new Node[newNodes.size()];
        newNodes.toArray(newNodesArray);
        return newNodesArray;
    }

    public void insertAll(Node[] nodes) {
        for (Node node : nodes) {
            frontier.insert(node);
        }
    }
}
