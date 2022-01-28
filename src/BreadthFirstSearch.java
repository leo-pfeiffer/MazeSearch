/**
 * Subclass of GeneralSearch that implements the Breadth First Search algorithm by
 * using a QueueFrontier.
 * */
public class BreadthFirstSearch extends GeneralSearch {

    public BreadthFirstSearch(Map map, Coord start, Coord goal) {
        super(map, start, goal);
        this.frontier = new QueueFrontier();
        this.startNode = new Node(null, startState, 0);
    }
}
