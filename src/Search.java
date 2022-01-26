import java.util.HashSet;

public abstract class Search {
    protected Map map;
    protected Coord start;
    protected State startState;
    protected Coord goal;
    protected Frontier frontier;
    protected Node solution;
    protected final HashSet<Node> explored = new HashSet<>();

    public Search(Map map, Coord start, Coord goal) {
        this.map = map;
        this.start = start;
        this.startState = new State(start, map);
        this.goal = goal;
    }

    public abstract void runSearch();

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

    public boolean goalTest(State state) {
        return state.getCoord().equals(goal);
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
