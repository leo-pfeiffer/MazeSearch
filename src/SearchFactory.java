public class SearchFactory {

    public Search makeSearch(String algo, Map map, Coord start, Coord goal) throws InvalidSearch {

        switch (algo) {
            case "BFS": // run BFS
                return new BreadthFirstSearch(map, start, goal);
            case "DFS": // run DFS
                return new DepthFirstSearch(map, start, goal);
            case "BestF": // run BestF
                return new BestFirstSearch(map, start, goal);
            case "AStar": // run AStar
                return new AStarSearch(map, start, goal);
            case "BDS": // run Bidirectional Search
                return new BidirectionalSearch(map, start, goal);
            default:
                throw new InvalidSearch(algo);
        }
    }

    static class InvalidSearch extends Exception {
        public InvalidSearch(String errorMessage) {
            super(errorMessage);
        }
    }
}
