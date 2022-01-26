public class BreadthFirstSearch extends Search {

    public BreadthFirstSearch(Map map, Coord start, Coord goal) {
        super(map, start, goal);
        this.frontier = new QueueFrontier();
    }

    @Override
    public void runSearch() {
        Node node = new Node(null, startState, 0);
        frontier.insert(node);
        while (!frontier.isEmpty()) {
            node = frontier.remove();
            explored.add(node);
            if (goalTest(node.getState())) {
                solution = node;
                return;
            }
            State[] successorStates = node.getState().getSuccessorStates();
            for (State successorState : successorStates) {
                Node newNode = new Node(node, successorState, 0);
                if (!explored.contains(newNode)) {
                    frontier.insert(newNode);
                }
            }
        }
    }
}
