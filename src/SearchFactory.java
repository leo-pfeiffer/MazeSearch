public class SearchFactory {

    public static Search makeSearch(String algo, Map map, Coord start, Coord goal) throws InvalidSearch {

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

    public static Search makeSearch(String algo, String algo2, Map map, Coord start, Coord goal) throws InvalidSearch {

        if (!algo.equals("BDS")) throw new InvalidSearch(algo + " cannot have secondary algorithm.");
        if (algo2.equals("BDS")) throw new InvalidSearch("Cannot use BDS with BDS");

        Search secondary = makeSearch(algo2, map, goal, start);
        return new BidirectionalSearch(map, start, goal, secondary);
    }

    static class InvalidSearch extends Exception {
        public InvalidSearch(String errorMessage) {
            super(errorMessage);
        }
    }
}
