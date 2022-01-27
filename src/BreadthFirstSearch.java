public class BreadthFirstSearch extends GeneralSearch {

    public BreadthFirstSearch(Map map, Coord start, Coord goal) {
        super(map, start, goal);
        this.frontier = new QueueFrontier();
    }
}
