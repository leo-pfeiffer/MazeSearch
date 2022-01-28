/**
 * Subclass of GeneralSearch that implements the Depth First Search algorithm by
 * using a StackFrontier.
 * */
public class DepthFirstSearch extends GeneralSearch {

    public DepthFirstSearch(Map map, Coord start, Coord goal) {
        super(map, start, goal);
        this.frontier = new StackFrontier();
        this.startNode = new Node(null, startState, 0);
    }
}
