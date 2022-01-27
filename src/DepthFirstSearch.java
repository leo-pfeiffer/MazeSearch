public class DepthFirstSearch extends GeneralSearch {

    public DepthFirstSearch(Map map, Coord start, Coord goal) {
        super(map, start, goal);
        this.frontier = new StackFrontier();
    }
}
