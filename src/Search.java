/**
 * Abstract class for search algorithms.
 * Subclasses must implement the runSearch method.
 * */
public abstract class Search {

    protected Map map;
    protected Coord start;
    protected State startState;
    protected Coord goal;
    protected Frontier frontier;
    protected Node solution;
    protected final ExploredSet explored = new ExploredSet();

    public Search(Map map, Coord start, Coord goal) {
        this.map = map;
        this.start = start;
        this.startState = new State(start, map);
        this.goal = goal;
    }

    public abstract void runSearch();

    /**
     * Print the solution path.
     * */
    public void printSolution() {
        if (solution == null) {
            System.out.println("No solution found.");
            return;
        }
        System.out.println("Solution found:");
        Node[] tree = solution.getTree();
        for (Node node : tree) {
            System.out.print(node.getState().getCoord());
            if (node.getDepth() != solution.getDepth()) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }


    /**
     * Test if the coordinates corresponds to the goal.
     * */
    public boolean goalTest(Coord coord) {
        return coord.equals(goal);
    }

    /**
     * Test if the coordinates of a state corresponds to the goal.
     * */
    public boolean goalTest(State state) {
        return goalTest(state.getCoord());
    }

    /**
     * Test if the coordinates of a node correspond to the goal.
     * */
    public boolean goalTest(Node node) {
        return goalTest(node.getState());
    }

    public Coord getStart() {
        return start;
    }

    public Map getMap() {
        return map;
    }

    public Coord getGoal() {
        return goal;
    }
}
