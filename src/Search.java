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
    protected Node startNode;
    protected final ExploredSet explored = new ExploredSet();

    public Search(Map map, Coord start, Coord goal) {
        this.map = map;
        this.start = start;
        this.startState = new State(start, map);
        this.goal = goal;
    }

    public abstract void runSearch();

    /**
     * Test if the coordinates of a node correspond to the goal.
     * */
    public boolean goalTest(Node node) {
        State state = node.getState();
        Coord coord = state.getCoord();
        return coord.equals(goal);
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

    public Node getSolution() {
        return solution;
    }

    /**
     * Prints failure message and number of explored nodes on unsuccessful search.
     * */
    public void printFailure() {
        System.out.println("fail");
        System.out.println(explored.size());
    }

    /**
     * Prints the solution path, cost, and number of explored nodes on successful search.
     * */
    public void printSuccess() {
        String path = solutionPath();
        System.out.println(path);
        System.out.println(solution.getCost());
        System.out.println(explored.size());
    }

    /**
     * Return a string representation of the solution path.
     * */
    public String solutionPath() {
        if (solution == null) return "";
        StringBuilder sb = new StringBuilder();
        Node[] tree = solution.getTree();
        for (Node node : tree) {
            sb.append(node.getState().getCoord());
        }
        return sb.toString();
    }

    public int getExploredNodeCount() {
        return explored.size();
    }

    public Frontier getFrontier() {
        return frontier;
    }
}
